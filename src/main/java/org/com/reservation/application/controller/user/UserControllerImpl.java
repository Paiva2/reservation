package org.com.reservation.application.controller.user;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.input.user.RegisterUserInput;
import org.com.reservation.application.controller.input.user.UserLoginInput;
import org.com.reservation.application.controller.output.UserLoginOutput;
import org.com.reservation.domain.usecase.user.registerUser.RegisterUserUsecase;
import org.com.reservation.domain.usecase.user.userAuthentication.UserAuthenticationUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {
    private final RegisterUserUsecase registerUserUsecase;
    private final UserAuthenticationUsecase userAuthenticationUsecase;

    @Override
    @Transactional
    public ResponseEntity<Void> register(RegisterUserInput input) {
        registerUserUsecase.execute(RegisterUserInput.toUser(input));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserLoginOutput> login(UserLoginInput input) {
        String authOutput = userAuthenticationUsecase.execute(UserLoginInput.toUser(input));
        UserLoginOutput output = UserLoginOutput.builder().token(authOutput).build();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
