package org.com.reservation.application.factory.reservation;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.reservation.cancelUpcomingReservation.CancelUpcomingReservationUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class CancelUpcomingReservationFactory {
    private final UserDataProvider userDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final ReservationTicketDataProvider reservationTicketDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;

    @Bean("cancelUpcomingReservationUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CancelUpcomingReservationUsecase create() {
        return CancelUpcomingReservationUsecase.builder()
            .userDataProvider(userDataProvider)
            .reservationDataProvider(reservationDataProvider)
            .sessionDataProvider(sessionDataProvider)
            .reservationTicketDataProvider(reservationTicketDataProvider)
            .reservationRoomSeatDataProvider(reservationRoomSeatDataProvider)
            .build();
    }
}
