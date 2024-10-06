package org.com.reservation.domain.usecase.user.registerUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.domain.interfaces.dataprovider.RoleDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.entity.Role;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.utils.EmailUtils;
import org.com.reservation.domain.interfaces.utils.PasswordUtils;
import org.com.reservation.domain.usecase.user.common.exception.InvalidEmailException;
import org.com.reservation.domain.usecase.user.registerUser.exception.EmailAlreadyUsedException;
import org.com.reservation.domain.usecase.user.registerUser.exception.InvalidPasswordException;
import org.com.reservation.domain.usecase.user.registerUser.exception.RoleNotFoundException;

import java.util.Optional;

@Builder
@AllArgsConstructor
public class RegisterUserUsecase {
    private final UserDataProvider userDataProvider;
    private final RoleDataProvider roleDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;

    private final EmailUtils emailUtils;
    private final PasswordUtils passwordUtils;

    public void execute(User user) {
        validatePassword(user.getPassword());
        validateEmail(user.getEmail());

        checkEmailAlreadyUsed(user.getEmail());
        hashPassword(user);
        User userPersisted = persistNewUser(user);
        updateAndSetUserRole(userPersisted);
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new InvalidPasswordException();
        }
    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new InvalidEmailException();
        }

        boolean isEmailValid = emailUtils.regexValidation(email);

        if (!isEmailValid) {
            throw new InvalidEmailException();
        }
    }

    private void checkEmailAlreadyUsed(String email) {
        Optional<User> user = userDataProvider.findByUserEmail(email);

        if (user.isPresent()) {
            throw new EmailAlreadyUsedException();
        }
    }

    private void hashPassword(User user) {
        String hashedPassword = passwordUtils.encode(user.getPassword());
        user.setPassword(hashedPassword);
    }

    private void updateAndSetUserRole(User userPersisted) {
        Optional<Role> role = roleDataProvider.findByRoleName(EnumRole.USER);

        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found: " + EnumRole.USER);
        }

        UserRole userRole = new UserRole();
        UserRole.KeyId keyId = new UserRole.KeyId();
        keyId.setUserId(userPersisted.getId());
        keyId.setRoleId(role.get().getId());
        userRole.setId(keyId);
        userRole.setUser(userPersisted);
        userRole.setRole(role.get());

        persistUserRole(userRole);
    }

    private void persistUserRole(UserRole userRole) {
        userRoleDataProvider.persist(userRole);
    }

    private User persistNewUser(User user) {
        return userDataProvider.persist(user);
    }
}
