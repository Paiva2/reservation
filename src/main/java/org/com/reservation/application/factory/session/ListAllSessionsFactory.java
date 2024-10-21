package org.com.reservation.application.factory.session;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.session.listAllSessions.ListAllSessionsUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class ListAllSessionsFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final SessionDataProvider sessionDataProvider;

    @Bean("listAllSessionsUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ListAllSessionsUsecase create() {
        return ListAllSessionsUsecase.builder()
            .sessionDataProvider(sessionDataProvider)
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .build();
    }
}
