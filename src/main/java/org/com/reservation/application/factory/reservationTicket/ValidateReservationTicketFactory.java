package org.com.reservation.application.factory.reservationTicket;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.reservationTicket.validateReservationTicket.ValidateReservationTicketUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class ValidateReservationTicketFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final ReservationTicketDataProvider reservationTicketDataProvider;
    private final SessionDataProvider sessionDataProvider;

    @Bean("validateReservationTicketUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ValidateReservationTicketUsecase create() {
        return ValidateReservationTicketUsecase.builder()
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .reservationTicketDataProvider(reservationTicketDataProvider)
            .sessionDataProvider(sessionDataProvider)
            .build();
    }
}
