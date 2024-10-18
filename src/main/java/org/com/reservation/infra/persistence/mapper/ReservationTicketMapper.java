package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.MovieTicket;
import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.ReservationTicket;
import org.com.reservation.infra.persistence.entity.ReservationTicketEntity;
import org.springframework.beans.BeanUtils;

public class ReservationTicketMapper {
    public static ReservationTicketEntity toReservationTicketEntity(ReservationTicket reservationTicket) {
        if (reservationTicket == null) return null;

        ReservationTicketEntity reservationTicketEntity = new ReservationTicketEntity();
        copyProperties(reservationTicket, reservationTicketEntity);

        if (reservationTicket.getReservation() != null) {
            reservationTicketEntity.setReservation(ReservationMapper.toReservationEntity(reservationTicket.getReservation()));
        }

        if (reservationTicket.getMovieTicket() != null) {
            reservationTicketEntity.setMovieTicket(MovieTicketMapper.toMovieTicketEntity(reservationTicket.getMovieTicket()));
        }

        return reservationTicketEntity;
    }

    public static ReservationTicket toReservationTicket(ReservationTicketEntity reservationTicketEntity) {
        if (reservationTicketEntity == null) return null;

        ReservationTicket reservationTicket = new ReservationTicket();
        copyProperties(reservationTicketEntity, reservationTicket);

        if (reservationTicketEntity.getReservation() != null) {
            Reservation reservation = new Reservation();
            reservationTicket.setReservation(reservation);
            copyProperties(reservationTicketEntity.getReservation(), reservationTicket.getReservation());
        }

        if (reservationTicketEntity.getMovieTicket() != null) {
            MovieTicket movieTicket = new MovieTicket();
            reservationTicket.setMovieTicket(movieTicket);
            copyProperties(reservationTicketEntity.getMovieTicket(), reservationTicket.getMovieTicket());
        }

        return reservationTicket;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
