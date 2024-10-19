package org.com.reservation.domain.usecase.session.getAvailableRoomSeatsOnSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.roomSeat.GetAvailableRoomSeatsOnSessionOutput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.RoomNotFoundException;
import org.com.reservation.domain.usecase.common.exception.SessionNotActiveException;
import org.com.reservation.domain.usecase.common.exception.SessionNotFoundException;
import org.com.reservation.domain.usecase.common.exception.RoomSessionNotFoundException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@AllArgsConstructor
@Builder
public class GetAvailableRoomSeatsOnSessionUsecase {
    private final SessionDataProvider sessionDataProvider;
    private final RoomDataProvider roomDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;
    private final RoomsSeatsDataProvider roomsSeatsDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;

    public GetAvailableRoomSeatsOnSessionOutput execute(Long sessionId, Long roomId) {
        Session session = findSession(sessionId);
        checkSessionActive(session);

        Room room = findRoom(roomId);

        checkRoomSessionExists(session.getId(), room.getId());

        List<RoomSeat> roomSeats = findRoomSeats(room.getId());

        List<Reservation> sessionReservations = findSessionReservations(session.getId());
        List<Long> seatsIdsReserved = new ArrayList<>();

        for (Reservation reservation : sessionReservations) {
            List<ReservationRoomSeat> reservationRoomSeats = findReservationSeats(reservation.getId(), roomId);

            List<Long> seatsReserved = reservationRoomSeats.stream().map(ReservationRoomSeat::getRoomSeat).map(roomSeat -> roomSeat.getSeat().getId()).toList();
            seatsIdsReserved.addAll(seatsReserved);
        }

        LinkedHashMap<Long, Seat> seatsAvailabilityMapping = new LinkedHashMap<>();
        fillReservedSeats(roomSeats, seatsAvailabilityMapping, seatsIdsReserved);

        return mountOutput(session.getId(), room.getId(), roomSeats.size(), seatsAvailabilityMapping);
    }

    private Session findSession(Long sessionId) {
        return sessionDataProvider.findActiveById(sessionId).orElseThrow(SessionNotFoundException::new);
    }

    private void checkSessionActive(Session session) {
        if (!session.isActive()) {
            throw new SessionNotActiveException(session.getId().toString());
        }
    }

    private Room findRoom(Long roomId) {
        return roomDataProvider.findById(roomId).orElseThrow(RoomNotFoundException::new);
    }

    private void checkRoomSessionExists(Long sessionId, Long roomId) {
        roomSessionDataProvider.findBySessionAndRoom(sessionId, roomId).orElseThrow(RoomSessionNotFoundException::new);
    }

    private List<RoomSeat> findRoomSeats(Long roomId) {
        return roomsSeatsDataProvider.findAllByRoomSorted(roomId);
    }

    private List<Reservation> findSessionReservations(Long sessionId) {
        return reservationDataProvider.findAllActiveBySessionId(sessionId);
    }

    private List<ReservationRoomSeat> findReservationSeats(Long reservationId, Long roomId) {
        return reservationRoomSeatDataProvider.findByReservationAndRoom(reservationId, roomId);
    }

    private void fillReservedSeats(List<RoomSeat> roomSeats, LinkedHashMap<Long, Seat> seatsAvailabilityMapping, List<Long> seatsIdsReserved) {
        for (RoomSeat roomSeat : roomSeats) {
            Seat seat = roomSeat.getSeat();
            boolean isSeatAvailable = !seatsIdsReserved.contains(seat.getId());
            seat.setAvailable(isSeatAvailable);

            seatsAvailabilityMapping.put(seat.getId(), seat);
        }
    }

    private GetAvailableRoomSeatsOnSessionOutput mountOutput(Long sessionId, Long roomId, Integer totalSeats, LinkedHashMap<Long, Seat> seatsAvailabilityMapping) {
        List<GetAvailableRoomSeatsOnSessionOutput.SessionRoomSeatOutput> sessionRoomSeatOutputs = new ArrayList<>();

        for (Long key : seatsAvailabilityMapping.keySet()) {
            Seat seat = seatsAvailabilityMapping.get(key);

            sessionRoomSeatOutputs.add(GetAvailableRoomSeatsOnSessionOutput.SessionRoomSeatOutput.builder()
                .seatId(seat.getId())
                .row(seat.getRow().name())
                .number(seat.getNumber())
                .seatAvailable(seat.getAvailable())
                .accessibility(seat.getAccessibility())
                .build()
            );
        }

        return GetAvailableRoomSeatsOnSessionOutput.builder()
            .sessionId(sessionId)
            .roomId(roomId)
            .totalSeats(totalSeats)
            .seats(sessionRoomSeatOutputs)
            .build();
    }
}
