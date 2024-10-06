package org.com.reservation.application.controller.user;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.input.user.RegisterUserInput;
import org.com.reservation.domain.usecase.user.registerUser.RegisterUserUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {
    private final RegisterUserUsecase registerUserUsecase;

    @Override
    @Transactional
    public ResponseEntity<Void> register(RegisterUserInput input) {
        registerUserUsecase.execute(input.toUser(input));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
