package org.com.reservation.application.controller.dto.output.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumSeatRow;
import org.com.reservation.domain.interfaces.utils.DateUtils;
import org.com.reservation.infra.utils.DateUtilsImpl;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MakeReservationOutput {
    private Long reservationId;
    private SessionOutput session;
    private List<ReservationRoomSeatOutput> roomSeats;

    private static final DateUtils dateUtils = new DateUtilsImpl();

    public static MakeReservationOutput toOutput(Reservation reservation, Session session, List<ReservationRoomSeat> reservationRoomSeats) {
        return MakeReservationOutput.builder()
            .reservationId(reservation.getId())
            .session(SessionOutput.builder()
                .sessionId(session.getId())
                .start(dateUtils.formatDateTime(session.getStart()))
                .end(dateUtils.formatDateTime(session.getEnd()))
                .movieOutput(MovieOutput.builder().movieId(session.getMovie().getId()).title(session.getMovie().getTitle()).build())
                .build()
            ).roomSeats(reservationRoomSeats.stream().map(ReservationRoomSeatOutput::toOutput).toList())
            .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class SessionOutput {
        private Long sessionId;
        private String start;
        private String end;
        private MovieOutput movieOutput;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class MovieOutput {
        private Long movieId;
        private String title;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class ReservationRoomSeatOutput {
        private Long roomId;
        private Long seatId;
        private EnumSeatRow row;
        private Integer number;
        private Boolean accessibility;

        public static ReservationRoomSeatOutput toOutput(ReservationRoomSeat reservationRoomSeat) {
            Room room = reservationRoomSeat.getRoomSeat().getRoom();
            Seat seat = reservationRoomSeat.getRoomSeat().getSeat();

            return ReservationRoomSeatOutput.builder()
                .roomId(room.getId())
                .seatId(seat.getId())
                .row(seat.getRow())
                .number(seat.getNumber())
                .accessibility(seat.getAccessibility())
                .build();
        }
    }
}
