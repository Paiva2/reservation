package org.com.reservation.application.factory.session;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.reservation.cancelUpcomingReservation.CancelUpcomingReservationUsecase;
import org.com.reservation.domain.usecase.reservation.cancelUpcomingReservationAdmin.CancelUpcomingReservationAdminUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class CancelUpcomingReservationAdminFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final CancelUpcomingReservationUsecase cancelUpcomingReservationUsecase;

    @Bean("cancelUpcomingReservationAdminUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CancelUpcomingReservationAdminUsecase create() {
        return CancelUpcomingReservationAdminUsecase.builder()
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .cancelUpcomingReservationUsecase(cancelUpcomingReservationUsecase)
            .build();
    }
}
