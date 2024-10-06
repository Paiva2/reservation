package org.com.reservation.domain.usecase.user.userAuthentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.utils.AuthenticationUtils;
import org.com.reservation.domain.interfaces.utils.PasswordUtils;
import org.com.reservation.domain.usecase.user.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.ErrorSigningAuthenticationException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.InvalidCredentialsException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.UserDisabledException;

@AllArgsConstructor
@Builder
public class UserAuthenticationUsecase {
    private final UserDataProvider userDataProvider;

    private final PasswordUtils passwordUtils;
    private final AuthenticationUtils authenticationUtils;

    public String execute(User user) {
        User userFound = findUser(user.getEmail());
        checkUserDisabled(user);
        checkPasswords(userFound.getPassword(), user.getPassword());

        String authToken;

        try {
            authToken = authenticationUtils.sign(userFound);
        } catch (Exception e) {
            throw new ErrorSigningAuthenticationException(e.getMessage());
        }

        return authToken;
    }

    private User findUser(String email) {
        return userDataProvider.findByUserEmail(email).orElseThrow(UserNotFoundException::new);
    }

    private void checkPasswords(String hashedPassword, String rawPassword) {
        boolean passwordMatches = passwordUtils.matches(rawPassword, hashedPassword);

        if (!passwordMatches) {
            throw new InvalidCredentialsException();
        }
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }
}
