package org.com.reservation.application.factory.reservation;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.ReservationDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.reservation.getAllReservationsFromSession.GetAllReservationsFromSessionUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class GetAllReservationsFromSessionFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final ReservationDataProvider reservationDataProvider;

    @Bean("getAllReservationsFromSessionUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public GetAllReservationsFromSessionUsecase create() {
        return GetAllReservationsFromSessionUsecase.builder()
            .userDataProvider(userDataProvider)
            .reservationDataProvider(reservationDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .sessionDataProvider(sessionDataProvider)
            .build();
    }
}
