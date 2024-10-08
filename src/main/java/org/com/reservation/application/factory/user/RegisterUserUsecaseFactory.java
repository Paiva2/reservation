package org.com.reservation.application.factory.user;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.RoleDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.interfaces.utils.EmailUtils;
import org.com.reservation.domain.interfaces.utils.PasswordUtils;
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

    @Bean("registerUserUsecase")
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
