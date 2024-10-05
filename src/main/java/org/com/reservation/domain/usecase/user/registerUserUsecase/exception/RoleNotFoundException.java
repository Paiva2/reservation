package org.com.reservation.domain.usecase.user.registerUserUsecase.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
