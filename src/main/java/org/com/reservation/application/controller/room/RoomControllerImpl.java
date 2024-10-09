package org.com.reservation.application.controller.room;

import lombok.AllArgsConstructor;
import org.com.reservation.application.controller.dto.output.room.ListAvailableRoomsOutput;
import org.com.reservation.domain.usecase.room.listAvailableRooms.ListAvailableRoomsUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RoomControllerImpl implements RoomController {
    private final ListAvailableRoomsUsecase listAvailableRoomsUsecase;

    @Override
    public ResponseEntity<ListAvailableRoomsOutput> listAvailableRooms(Authentication authentication, Integer page, Integer size) {
        Long subject = Long.valueOf(authentication.getName());
        ListAvailableRoomsOutput output = listAvailableRoomsUsecase.execute(subject, page, size);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
