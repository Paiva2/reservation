package org.com.reservation.application.factory.session;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.com.reservation.domain.usecase.session.listUpcomingSessions.ListUpComingSessionsUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class ListUpComingSessionFactory {
    private final SessionDataProvider sessionDataProvider;

    @Bean("listUpComingSessionsUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ListUpComingSessionsUsecase create() {
        return ListUpComingSessionsUsecase.builder()
            .sessionDataProvider(sessionDataProvider)
            .build();
    }
}
