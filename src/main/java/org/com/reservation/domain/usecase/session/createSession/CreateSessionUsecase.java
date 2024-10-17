package org.com.reservation.domain.usecase.session.createSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.input.session.CreateSessionInput;
import org.com.reservation.application.controller.dto.output.session.CreateSessionOutput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.InvalidDateException;
import org.com.reservation.domain.usecase.common.exception.MovieNotFoundException;
import org.com.reservation.domain.usecase.session.createSession.exception.RoomsNotFoundException;
import org.com.reservation.domain.usecase.session.createSession.exception.SessionPeriodUnavailableException;
import org.com.reservation.domain.usecase.common.exception.InvalidPermissionsException;
import org.com.reservation.domain.usecase.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.common.exception.UserDisabledException;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
public class CreateSessionUsecase {
    private final static List<EnumRole> requiredRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final RoomDataProvider roomDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;

    public CreateSessionOutput execute(Long userId, Long movieId, CreateSessionInput input) {
        checkUserPermissions(userId);
        checkSessionPeriod(input);
        checkSessionPeriodAvailable(input);

        Set<Long> roomSet = removeRoomIdsDuplicates(input.getRoomsIds());

        Movie movie = findMovie(movieId);
        List<Room> roomList = findRoomList(roomSet);

        Session sessionFilled = fillSession(movie, input);
        Session session = persistSession(sessionFilled);

        List<RoomSession> roomSessions = new ArrayList<>();

        roomList.forEach(room -> {
            RoomSession roomSession = fillRoomSession(session, room);
            roomSessions.add(roomSession);
        });

        persistRoomSessionList(roomSessions);

        return mountOutput(movie, session, roomList);
    }

    private void checkUserPermissions(Long userId) {
        User findUser = userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        if (findUser.getDisabledAt() != null) {
            throw new UserDisabledException();
        }

        List<UserRole> userRoles = userRoleDataProvider.findByUserId(findUser.getId());

        if (userRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean hasNecessaryRole = userRoles.stream().map(UserRole::getRole).anyMatch(role -> requiredRoles.contains(role.getName()));

        if (!hasNecessaryRole) {
            throw new InvalidPermissionsException();
        }
    }

    private Movie findMovie(Long movieId) {
        return movieDataProvider.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }

    private List<Room> findRoomList(Set<Long> roomsId) {
        List<Room> rooms = roomDataProvider.findManyById(roomsId);
        List<Long> roomsIdsFound = rooms.stream().map(Room::getId).toList();

        if (roomsIdsFound.size() == roomsId.size()) {
            return rooms;
        }

        List<Long> roomsNotFound = new ArrayList<>();

        for (Long roomId : roomsId) {
            if (!roomsIdsFound.contains(roomId)) {
                roomsNotFound.add(roomId);
            }
        }

        if (!roomsNotFound.isEmpty()) {
            throw new RoomsNotFoundException(roomsNotFound);
        }

        return rooms;
    }

    private void checkSessionPeriod(CreateSessionInput input) {
        Date today = new Date();

        if (input.getStart().before(today)) {
            throw new InvalidDateException("Start date can't be before today!");
        }

        if (input.getStart().after(input.getEnd())) {
            throw new InvalidDateException("Start date can't be after end date!");
        }

        if (input.getStart().equals(input.getEnd())) {
            throw new InvalidDateException("Start date can't be equals end date!");
        }
    }

    private void checkSessionPeriodAvailable(CreateSessionInput input) {
        List<Session> sessionsOnPeriod = sessionDataProvider.findActiveSessionsByPeriodAndRooms(input.getStart(), input.getRoomsIds());
        List<String> sessionsRooms = new ArrayList<>();

        if (!sessionsOnPeriod.isEmpty()) {
            sessionsOnPeriod.forEach(session -> {
                String roomsHavingThisSession = session.getRoomSessions().stream().map(RoomSession::getRoom).map(Room::getNumber).map(Object::toString).collect(Collectors.joining(", "));
                sessionsRooms.add("Session: " + session.getId() + " - rooms number: " + roomsHavingThisSession);
            });

            throw new SessionPeriodUnavailableException(sessionsRooms.toString());
        }
    }


    private Set<Long> removeRoomIdsDuplicates(List<Long> ids) {
        return new HashSet<>(ids);
    }

    private Session fillSession(Movie movie, CreateSessionInput input) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTime(input.getStart());

        return Session.builder()
            .start(calendar.getTime())
            .end(input.getEnd())
            .movie(movie)
            .active(true)
            .build();
    }

    private Session persistSession(Session session) {
        return sessionDataProvider.persist(session);
    }

    private RoomSession fillRoomSession(Session session, Room room) {
        RoomSession roomSession = new RoomSession();
        RoomSession.KeyId keyId = new RoomSession.KeyId();
        roomSession.setSession(session);
        roomSession.setRoom(room);
        keyId.setSessionId(session.getId());
        keyId.setRoomId(room.getId());

        roomSession.setId(keyId);

        return roomSession;
    }

    private void persistRoomSessionList(List<RoomSession> roomSessions) {
        roomSessionDataProvider.persistAll(roomSessions);
    }

    private CreateSessionOutput mountOutput(Movie movie, Session session, List<Room> room) {
        return CreateSessionOutput.builder()
            .sessionId(session.getId())
            .start(session.getStart())
            .end(session.getEnd())
            .movie(CreateSessionOutput.MovieOutput.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .build()
            ).room(room.stream().map(CreateSessionOutput::convertRoomList).toList())
            .build();
    }
}
