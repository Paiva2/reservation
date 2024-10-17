package org.com.reservation.application.factory.reservation;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.reservation.makeReservation.MakeReservationUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class MakeReservationFactory {
    private final UserDataProvider userDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final RoomsSeatsDataProvider roomsSeatsDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final ReservationTicketDataProvider reservationTicketDataProvider;

    @Bean("makeReservationUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MakeReservationUsecase create() {
        return MakeReservationUsecase.builder()
            .userDataProvider(userDataProvider)
            .sessionDataProvider(sessionDataProvider)
            .roomsSeatsDataProvider(roomsSeatsDataProvider)
            .roomSessionDataProvider(roomSessionDataProvider)
            .reservationRoomSeatDataProvider(reservationRoomSeatDataProvider)
            .reservationDataProvider(reservationDataProvider)
            .reservationTicketDataProvider(reservationTicketDataProvider)
            .build();
    }
}
