package org.com.reservation.domain.usecase.reservation.makeReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.input.reservation.MakeReservationInput;
import org.com.reservation.application.controller.dto.output.reservation.MakeReservationOutput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumTicketType;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.*;
import org.com.reservation.domain.usecase.reservation.makeReservation.exception.RoomSeatNotFoundException;
import org.com.reservation.domain.usecase.reservation.makeReservation.exception.SeatAlreadyReservedException;
import org.com.reservation.domain.usecase.reservation.makeReservation.exception.SessionAlreadyStartedException;

import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor
@Builder
public class MakeReservationUsecase {
    private final UserDataProvider userDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final RoomsSeatsDataProvider roomsSeatsDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final ReservationTicketDataProvider reservationTicketDataProvider;

    public MakeReservationOutput execute(Long userId, Long sessionId, Long roomId, MakeReservationInput input) {
        convertInputTicketType(input.getReservations());

        User user = findUser(userId);
        checkUserDisabled(user);

        Session session = findSession(sessionId);
        checkSessionActive(session);

        Movie movie = session.getMovie();
        MovieTicket movieTicket = movie.getMovieTicket();

        RoomSession roomSession = findRoomSession(session.getId(), roomId);
        Room room = roomSession.getRoom();

        List<RoomSeat> roomSeats = findRoomSeats(room.getId(), input.getReservations());
        List<Seat> seats = roomSeats.stream().map(RoomSeat::getSeat).toList();
        checkSeatAlreadyReserved(session.getId(), room.getId(), seats);

        Reservation reservationFilled = fillReservation(user, session);
        Reservation reservation = persistReservation(reservationFilled);

        List<ReservationTicket> reservationTickets = fillReservationTickets(input, movieTicket, reservation);
        List<ReservationRoomSeat> reservationRoomSeatsFilled = fillReservationRoomSeats(reservation, roomSeats);

        List<ReservationRoomSeat> reservationRoomSeats = persistReservationRoomSeats(reservationRoomSeatsFilled);
        persistReservationTickets(reservationTickets);

        return mountOutput(reservation, session, reservationRoomSeats);
    }

    private void convertInputTicketType(List<MakeReservationInput.ReservationInput> reservationInputs) {
        if (reservationInputs == null || reservationInputs.isEmpty()) {
            throw new InvalidPropertyException("reservations", Arrays.toString(EnumTicketType.values()));
        }

        for (MakeReservationInput.ReservationInput reservationInput : reservationInputs) {
            try {
                reservationInput.setTicketType(EnumTicketType.valueOf(reservationInput.getTicketType().toUpperCase(Locale.ROOT)).name());
            } catch (IllegalArgumentException exception) {
                int idxError = reservationInputs.indexOf(reservationInput);
                throw new InvalidPropertyException("reservations[" + idxError + "].ticketType", Arrays.toString(EnumTicketType.values()));
            }
        }
    }

    private User findUser(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private Session findSession(Long sessionId) {
        return sessionDataProvider.findActiveById(sessionId).orElseThrow(SessionNotFoundException::new);
    }

    private void checkSessionActive(Session session) {
        if (!session.isActive()) {
            throw new SessionNotActiveException(session.getId().toString());
        }

        if (session.getStart().before(new Date())) {
            throw new SessionAlreadyStartedException();
        }
    }

    private RoomSession findRoomSession(Long sessionId, Long roomId) {
        return roomSessionDataProvider.findBySessionAndRoom(sessionId, roomId).orElseThrow(RoomSessionNotFoundException::new);
    }

    private List<RoomSeat> findRoomSeats(Long roomId, List<MakeReservationInput.ReservationInput> reservationsInput) {
        List<Long> seatIds = reservationsInput.stream().map(MakeReservationInput.ReservationInput::getSeatId).toList();
        List<RoomSeat> roomSeats = roomsSeatsDataProvider.findManyByIdAndRoom(roomId, seatIds);

        if (roomSeats.size() == reservationsInput.size()) return roomSeats;

        List<Long> seatsNotFound = new ArrayList<>();
        List<Long> seatsFound = roomSeats.stream().map(RoomSeat::getSeat).map(Seat::getId).toList();

        for (MakeReservationInput.ReservationInput reservationInput : reservationsInput) {
            if (!seatsFound.contains(reservationInput.getSeatId())) {
                seatsNotFound.add(reservationInput.getSeatId());
            }
        }

        if (!seatsNotFound.isEmpty()) {
            throw new RoomSeatNotFoundException(seatsNotFound.toString(), roomId.toString());
        }

        return roomSeats;
    }

    private void checkSeatAlreadyReserved(Long sessionId, Long roomId, List<Seat> seats) {
        List<Long> seatsIds = seats.stream().map(Seat::getId).toList();
        List<ReservationRoomSeat> reservationRoomSeat = reservationRoomSeatDataProvider.findManyBySessionRoomSeat(sessionId, roomId, seatsIds);

        if (!reservationRoomSeat.isEmpty()) {
            List<Long> seatsReserved = reservationRoomSeat.stream().map(ReservationRoomSeat::getRoomSeat).map(RoomSeat::getSeat).map(Seat::getId).toList();
            throw new SeatAlreadyReservedException(seatsReserved, roomId.toString());
        }
    }

    private BigDecimal findPricePaid(EnumTicketType type, MovieTicket movieTicket) {
        BigDecimal pricePaid = new BigDecimal("0");
        if (movieTicket.getIsFree()) return pricePaid;

        switch (type) {
            case NORMAL -> pricePaid = movieTicket.getNormalPrice();
            case SPECIAL -> pricePaid = movieTicket.getSpecialPrice();
            case STUDENT -> pricePaid = movieTicket.getStudentPrice();
            default -> throw new InvalidPropertyException("ticketType", Arrays.toString(EnumTicketType.values()));
        }

        return pricePaid;
    }

    private Reservation fillReservation(User user, Session session) {
        return Reservation.builder()
            .user(user)
            .session(session)
            .build();
    }

    private Reservation persistReservation(Reservation reservation) {
        return reservationDataProvider.persist(reservation);
    }

    private List<ReservationTicket> fillReservationTickets(MakeReservationInput input, MovieTicket movieTicket, Reservation reservation) {
        List<ReservationTicket> reservationTickets = new ArrayList<>();

        for (MakeReservationInput.ReservationInput reservationInput : input.getReservations()) {
            EnumTicketType ticketType = EnumTicketType.valueOf(reservationInput.getTicketType());
            BigDecimal pricePaid = findPricePaid(ticketType, movieTicket);

            ReservationTicket reservationMovieTicket = ReservationTicket.builder()
                .pricePaid(pricePaid)
                .type(ticketType)
                .reservation(reservation)
                .movieTicket(movieTicket)
                .build();

            reservationTickets.add(reservationMovieTicket);
        }

        return reservationTickets;
    }

    private List<ReservationRoomSeat> fillReservationRoomSeats(Reservation reservation, List<RoomSeat> roomSeats) {
        List<ReservationRoomSeat> reservationRoomSeats = new ArrayList<>();

        for (RoomSeat roomSeat : roomSeats) {
            ReservationRoomSeat reservationRoomSeat = ReservationRoomSeat.builder().reservation(reservation).roomSeat(roomSeat).build();
            ReservationRoomSeat.KeyId keyId = new ReservationRoomSeat.KeyId();
            keyId.setReservationId(reservation.getId());
            keyId.setRoomSeatIds(roomSeat.getId());
            reservationRoomSeat.setId(keyId);

            reservationRoomSeats.add(reservationRoomSeat);
        }

        return reservationRoomSeats;
    }

    private List<ReservationRoomSeat> persistReservationRoomSeats(List<ReservationRoomSeat> reservationRoomSeats) {
        return reservationRoomSeatDataProvider.persistAll(reservationRoomSeats);
    }

    private void persistReservationTickets(List<ReservationTicket> reservationTickets) {
        reservationTicketDataProvider.persistAll(reservationTickets);
    }

    private MakeReservationOutput mountOutput(Reservation reservation, Session session, List<ReservationRoomSeat> reservationRoomSeats) {
        return MakeReservationOutput.toOutput(reservation, session, reservationRoomSeats);
    }
}
