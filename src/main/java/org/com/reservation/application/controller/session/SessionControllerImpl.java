package org.com.reservation.application.controller.session;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.input.session.CreateSessionInput;
import org.com.reservation.application.controller.dto.output.session.CreateSessionOutput;
import org.com.reservation.domain.usecase.session.createSession.CreateSessionUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class SessionControllerImpl implements SessionController {
    private final CreateSessionUsecase createSessionUsecase;

    @Override
    @Transactional
    public ResponseEntity<CreateSessionOutput> newSession(Authentication authentication, Long movieId, CreateSessionInput input) {
        Long subject = Long.valueOf(authentication.getName());
        CreateSessionOutput output = createSessionUsecase.execute(subject, movieId, input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }
}
