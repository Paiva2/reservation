package org.com.reservation.application.controller.genre;

import org.com.reservation.application.controller.dto.output.genre.ListGenresOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/genre")
public interface GenreController {
    @GetMapping("list")
    ResponseEntity<ListGenresOutput> list(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "5") Integer size);
}
