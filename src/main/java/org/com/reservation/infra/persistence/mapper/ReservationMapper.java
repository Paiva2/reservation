package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.entity.User;
import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.com.reservation.infra.persistence.entity.SessionEntity;
import org.com.reservation.infra.persistence.entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

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
        }

        if (reservationEntity.getReservationMovieTickets() != null) {
            reservation.setReservationMovieTickets(new ArrayList<>());
            copyProperties(reservationEntity.getReservationMovieTickets(), reservation.getReservationMovieTickets());
        }

        if (reservationEntity.getReservationRoomSeats() != null) {
            reservation.setReservationRoomSeats(new ArrayList<>());
            copyProperties(reservationEntity.getReservationRoomSeats(), reservation.getReservationRoomSeats());
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

        if (reservation.getReservationMovieTickets() != null) {
            reservationEntity.setReservationMovieTickets(new ArrayList<>());
            copyProperties(reservation.getReservationMovieTickets(), reservationEntity.getReservationMovieTickets());
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
