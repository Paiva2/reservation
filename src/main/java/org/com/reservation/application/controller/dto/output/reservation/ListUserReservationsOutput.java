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

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListUserReservationsOutput {
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Boolean isLast;
    private Long totalElements;
    private List<ReservationOutput> items;

    private static final DateUtils dateUtils = new DateUtilsImpl();

    public static ReservationOutput toReservationOutput(Reservation reservation) {
        Session session = reservation.getSession();
        Movie movie = session.getMovie();
        List<ReservationTicket> reservationTickets = reservation.getReservationTickets();
        List<ReservationRoomSeat> reservationRoomSeats = reservation.getReservationRoomSeats();

        return ReservationOutput.builder()
            .id(reservation.getId())
            .createdAt(dateUtils.formatDateTime(reservation.getCreatedAt()))
            .session(SessionOutput.builder()
                .id(session.getId())
                .start(dateUtils.formatDateTime(session.getStart()))
                .end(dateUtils.formatDateTime(session.getEnd()))
                .active(session.isActive())
                .movie(MovieOutput.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .durationSeconds(movie.getDurationSeconds())
                    .build()
                ).build()
            ).reservationTickets(reservationTickets.stream().map(ListUserReservationsOutput::toReservationTicketOutput).toList())
            .roomSeats(reservationRoomSeats.stream().map(ListUserReservationsOutput::toReservationRoomSeatOutput).toList())
            .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class ReservationOutput {
        private Long id;
        private String createdAt;
        private SessionOutput session;
        private List<ReservationTicketOutput> reservationTickets;
        private List<ReservationRoomSeatOutput> roomSeats;
    }

    private static ReservationTicketOutput toReservationTicketOutput(ReservationTicket reservationTicket) {
        return ReservationTicketOutput.builder()
            .id(reservationTicket.getId())
            .pricePaid(reservationTicket.getPricePaid())
            .type(reservationTicket.getType())
            .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class SessionOutput {
        private Long id;
        private String start;
        private String end;
        private boolean active;
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
    private static class ReservationRoomSeatOutput {
        private Long roomId;
        private String roomNumber;
        private Long seatId;
        private EnumSeatRow seatRow;
        private Integer seatNumber;
        private Boolean seatAccessibility;
    }

    private static ReservationRoomSeatOutput toReservationRoomSeatOutput(ReservationRoomSeat reservationRoomSeat) {
        Room room = reservationRoomSeat.getRoomSeat().getRoom();
        Seat seat = reservationRoomSeat.getRoomSeat().getSeat();

        return ReservationRoomSeatOutput.builder()
            .roomId(room.getId())
            .roomNumber(room.getNumber())
            .seatId(seat.getId())
            .seatRow(seat.getRow())
            .seatNumber(seat.getNumber())
            .seatAccessibility(seat.getAccessibility())
            .build();
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
}
