package org.com.reservation.application.factory.user;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.utils.AuthenticationUtils;
import org.com.reservation.domain.interfaces.utils.PasswordUtils;
import org.com.reservation.domain.usecase.user.userAuthentication.UserAuthenticationUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class UserAuthenticationFactory {
    private final UserDataProvider userDataProvider;

    private final PasswordUtils passwordUtils;
    private final AuthenticationUtils authenticationUtils;

    @Bean("userAuthenticationUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserAuthenticationUsecase create() {
        return UserAuthenticationUsecase.builder()
            .userDataProvider(userDataProvider)
            .authenticationUtils(authenticationUtils)
            .passwordUtils(passwordUtils)
            .build();
    }
}
