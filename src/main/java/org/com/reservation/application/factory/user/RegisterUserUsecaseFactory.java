package org.com.reservation.application.factory.user;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.dataprovider.RoleDataProvider;
import org.com.reservation.domain.dataprovider.UserDataProvider;
import org.com.reservation.domain.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.interfaces.EmailUtils;
import org.com.reservation.domain.interfaces.PasswordUtils;
import org.com.reservation.domain.usecase.user.registerUser.RegisterUserUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class RegisterUserUsecaseFactory {
    private final UserDataProvider userDataProvider;
    private final RoleDataProvider roleDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;

    private final PasswordUtils passwordUtils;
    private final EmailUtils emailUtils;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RegisterUserUsecase create() {
        return RegisterUserUsecase.builder()
            .userDataProvider(userDataProvider)
            .emailUtils(emailUtils)
            .passwordUtils(passwordUtils)
            .roleDataProvider(roleDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .build();
    }
}
