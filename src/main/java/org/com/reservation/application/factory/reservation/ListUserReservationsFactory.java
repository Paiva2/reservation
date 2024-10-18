package org.com.reservation.application.factory.reservation;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.ReservationDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.usecase.reservation.listUserReservations.ListUserReservationsUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class ListUserReservationsFactory {
    private final UserDataProvider userDataProvider;
    private final ReservationDataProvider reservationDataProvider;

    @Bean("listUserReservationsUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ListUserReservationsUsecase create() {
        return ListUserReservationsUsecase.builder()
            .userDataProvider(userDataProvider)
            .reservationDataProvider(reservationDataProvider)
            .build();
    }
}
