package org.com.reservation.application.controller.room;

import org.com.reservation.application.controller.dto.output.room.ListAvailableRoomsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/room")
public interface RoomController {
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<ListAvailableRoomsOutput> listAvailableRooms(Authentication authentication, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size);
}
