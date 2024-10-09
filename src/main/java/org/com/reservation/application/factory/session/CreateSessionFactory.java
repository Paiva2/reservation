package org.com.reservation.application.factory.session;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.session.createSession.CreateSessionUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class CreateSessionFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;

    private final SessionDataProvider sessionDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final RoomDataProvider roomDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;

    @Bean("createSessionUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CreateSessionUsecase create() {
        return CreateSessionUsecase.builder()
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .sessionDataProvider(sessionDataProvider)
            .roomDataProvider(roomDataProvider)
            .roomSessionDataProvider(roomSessionDataProvider)
            .movieDataProvider(movieDataProvider)
            .build();
    }
}
