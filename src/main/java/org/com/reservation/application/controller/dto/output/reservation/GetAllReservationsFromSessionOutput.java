package org.com.reservation.application.controller.dto.output.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumSeatRow;
import org.com.reservation.domain.enumeration.EnumTicketType;
import org.com.reservation.domain.interfaces.utils.DateUtils;
import org.com.reservation.infra.utils.DateUtilsImpl;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllReservationsFromSessionOutput {
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Boolean isLast;
    private Long totalElements;
    private SessionOutput session;
    private List<ReservationOutput> reservations;

    private static final DateUtils dateUtils = new DateUtilsImpl();

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class SessionOutput {
        private Long id;
        private String start;
        private String end;
        private boolean active;
        private String revenue;
        private MovieOutput movie;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class MovieOutput {
        private Long id;
        private String title;
        private Long durationSeconds;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class ReservationOutput {
        private Long id;
        private String createdAt;
        private UserOutput user;
        private List<ReservationTicketOutput> reservationTickets;
        private List<ReservationRoomSeatOutput> roomSeats;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class UserOutput {
        private Long id;
        private String name;
        private String email;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class ReservationTicketOutput {
        private Long id;
        private BigDecimal pricePaid;
        private EnumTicketType type;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class ReservationRoomSeatOutput {
        private Long roomId;
        private String roomNumber;
        private Long seatId;
        private EnumSeatRow seatRow;
        private Integer seatNumber;
        private Boolean seatAccessibility;
    }

    public static GetAllReservationsFromSessionOutput toOutput(Page<Reservation> sessionReservations, Session session, BigDecimal sessionRevenue) {
        Movie movie = session.getMovie();

        return GetAllReservationsFromSessionOutput.builder()
            .page(sessionReservations.getNumber() + 1)
            .size(sessionReservations.getSize())
            .totalPages(sessionReservations.getTotalPages())
            .isLast(sessionReservations.isLast())
            .totalElements(sessionReservations.getTotalElements())
            .session(SessionOutput.builder()
                .id(session.getId())
                .start(dateUtils.formatDateTime(session.getStart()))
                .end(dateUtils.formatDateTime(session.getEnd()))
                .active(session.isActive())
                .revenue(sessionRevenue.toString())
                .movie(MovieOutput.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .durationSeconds(movie.getDurationSeconds())
                    .build()
                ).build()
            ).reservations(sessionReservations.stream().map(GetAllReservationsFromSessionOutput::toReservationOutput).toList())
            .build();
    }

    private static ReservationOutput toReservationOutput(Reservation reservation) {
        List<ReservationTicket> reservationTickets = reservation.getReservationTickets();
        List<ReservationRoomSeat> reservationRoomSeats = reservation.getReservationRoomSeats();
        User user = reservation.getUser();

        return ReservationOutput.builder()
            .id(reservation.getId())
            .createdAt(dateUtils.formatDateTime(reservation.getCreatedAt()))
            .user(UserOutput.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getFirstName() + " " + user.getLastName())
                .build()
            ).reservationTickets(reservationTickets.stream().map(GetAllReservationsFromSessionOutput::toReservationTicketOutput).toList())
            .roomSeats(reservationRoomSeats.stream().map(GetAllReservationsFromSessionOutput::toReservationRoomSeatOutput).toList())
            .build();
    }

    private static ReservationTicketOutput toReservationTicketOutput(ReservationTicket reservationTicket) {
        return ReservationTicketOutput.builder()
            .id(reservationTicket.getId())
            .pricePaid(reservationTicket.getPricePaid())
            .type(reservationTicket.getType())
            .build();
    }

    private static ReservationRoomSeatOutput toReservationRoomSeatOutput(ReservationRoomSeat reservationRoomSeat) {
        return ReservationRoomSeatOutput.builder()
            .roomId(reservationRoomSeat.getRoomSeat().getRoom().getId())
            .roomNumber(reservationRoomSeat.getRoomSeat().getRoom().getNumber())
            .seatId(reservationRoomSeat.getRoomSeat().getSeat().getId())
            .seatRow(reservationRoomSeat.getRoomSeat().getSeat().getRow())
            .seatNumber(reservationRoomSeat.getRoomSeat().getSeat().getNumber())
            .seatAccessibility(reservationRoomSeat.getRoomSeat().getSeat().getAccessibility())
            .build();
    }
}
