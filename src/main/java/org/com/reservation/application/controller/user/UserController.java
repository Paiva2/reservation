package org.com.reservation.application.controller.user;

import jakarta.validation.Valid;
import org.com.reservation.application.controller.dto.input.user.RegisterUserInput;
import org.com.reservation.application.controller.dto.input.user.UserLoginInput;
import org.com.reservation.application.controller.dto.output.user.UserLoginOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
public interface UserController {
    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid RegisterUserInput input);

    @PostMapping("/login")
    ResponseEntity<UserLoginOutput> login(@RequestBody @Valid UserLoginInput input);
}
