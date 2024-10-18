package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.*;
import org.com.reservation.infra.persistence.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {
    public static Reservation toReservation(ReservationEntity reservationEntity) {
        if (reservationEntity == null) return null;

        Reservation reservation = new Reservation();
        copyProperties(reservationEntity, reservation);

        if (reservationEntity.getUser() != null) {
            reservation.setUser(new User());
            copyProperties(reservationEntity.getUser(), reservation.getUser());
        }

        if (reservationEntity.getSession() != null) {
            reservation.setSession(new Session());
            copyProperties(reservationEntity.getSession(), reservation.getSession());

            if (reservationEntity.getSession().getMovie() != null) {
                Movie movie = new Movie();
                reservation.getSession().setMovie(movie);
                copyProperties(reservationEntity.getSession().getMovie(), reservation.getSession().getMovie());
            }
        }

        if (reservationEntity.getReservationTickets() != null) {
            List<ReservationTicket> reservationTickets = new ArrayList<>();

            for (ReservationTicketEntity reservationTicketEntity : reservationEntity.getReservationTickets()) {
                reservationTickets.add(ReservationTicketMapper.toReservationTicket(reservationTicketEntity));
            }

            reservation.setReservationTickets(reservationTickets);
        }

        if (reservationEntity.getReservationRoomSeats() != null) {
            List<ReservationRoomSeat> reservationRoomSeats = new ArrayList<>();

            for (ReservationRoomSeatEntity reservationRoomSeatEntity : reservationEntity.getReservationRoomSeats()) {
                reservationRoomSeats.add(ReservationRoomSeatMapper.toReservationRoomSeat(reservationRoomSeatEntity));
            }

            reservation.setReservationRoomSeats(reservationRoomSeats);
        }

        return reservation;
    }

    public static ReservationEntity toReservationEntity(Reservation reservation) {
        if (reservation == null) return null;

        ReservationEntity reservationEntity = new ReservationEntity();
        copyProperties(reservation, reservationEntity);

        if (reservation.getUser() != null) {
            reservationEntity.setUser(new UserEntity());
            copyProperties(reservation.getUser(), reservationEntity.getUser());
        }

        if (reservation.getSession() != null) {
            reservationEntity.setSession(new SessionEntity());
            copyProperties(reservation.getSession(), reservationEntity.getSession());
        }

        if (reservation.getReservationTickets() != null) {
            reservationEntity.setReservationTickets(new ArrayList<>());
            copyProperties(reservation.getReservationTickets(), reservationEntity.getReservationTickets());
        }

        if (reservation.getReservationRoomSeats() != null) {
            reservationEntity.setReservationRoomSeats(new ArrayList<>());
            copyProperties(reservation.getReservationRoomSeats(), reservationEntity.getReservationRoomSeats());
        }

        return reservationEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
