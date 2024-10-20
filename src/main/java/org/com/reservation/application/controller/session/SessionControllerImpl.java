package org.com.reservation.application.controller.session;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.input.session.CreateSessionInput;
import org.com.reservation.application.controller.dto.output.roomSeat.GetAvailableRoomSeatsOnSessionOutput;
import org.com.reservation.application.controller.dto.output.session.CreateSessionOutput;
import org.com.reservation.application.controller.dto.output.session.ListAllSessionsOutput;
import org.com.reservation.application.controller.dto.output.session.ListUpcomingSessionsOutput;
import org.com.reservation.domain.usecase.session.createSession.CreateSessionUsecase;
import org.com.reservation.domain.usecase.session.getAvailableRoomSeatsOnSession.GetAvailableRoomSeatsOnSessionUsecase;
import org.com.reservation.domain.usecase.session.listAllSessions.ListAllSessionsUsecase;
import org.com.reservation.domain.usecase.session.listUpcomingSessions.ListUpComingSessionsUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@AllArgsConstructor
@RestController
public class SessionControllerImpl implements SessionController {
    private final CreateSessionUsecase createSessionUsecase;
    private final ListUpComingSessionsUsecase listUpComingSessionsUsecase;
    private final GetAvailableRoomSeatsOnSessionUsecase getAvailableRoomSeatsOnSessionUsecase;
    private final ListAllSessionsUsecase listAllSessionsUsecase;

    @Override
    @Transactional
    public ResponseEntity<CreateSessionOutput> newSession(Authentication authentication, Long movieId, CreateSessionInput input) {
        Long subject = Long.valueOf(authentication.getName());
        CreateSessionOutput output = createSessionUsecase.execute(subject, movieId, input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ListUpcomingSessionsOutput> listUpcoming(Long movieId, Integer page, Integer size) {
        ListUpcomingSessionsOutput output = listUpComingSessionsUsecase.execute(page, size, movieId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetAvailableRoomSeatsOnSessionOutput> getAvailableSeatsOnSession(Long sessionId, Long roomId) {
        GetAvailableRoomSeatsOnSessionOutput output = getAvailableRoomSeatsOnSessionUsecase.execute(sessionId, roomId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListAllSessionsOutput> listAll(Authentication authentication, Integer page, Integer size, Date date, Boolean active) {
        Long subject = Long.valueOf(authentication.getName());
        ListAllSessionsOutput output = listAllSessionsUsecase.execute(subject, page, size, date, active);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
