package org.com.reservation.domain.usecase.session.listUpcomingSessions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.output.session.ListUpcomingSessionsOutput;
import org.com.reservation.domain.entity.Session;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Builder
public class ListUpComingSessionsUsecase {
    private final SessionDataProvider sessionDataProvider;

    public ListUpcomingSessionsOutput execute(int page, int size, Long movieId) {
        if (page < 1) {
            page = 1;
        }

        if (size < 5) {
            size = 5;
        } else if (size > 50) {
            size = 50;
        }

        Page<Session> sessionsPage = findSessions(page, size, movieId);

        return mountOutput(sessionsPage);
    }

    private Page<Session> findSessions(int page, int size, Long movieId) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "SS_START");
        return sessionDataProvider.findAllUpcoming(pageable, movieId);
    }

    private ListUpcomingSessionsOutput mountOutput(Page<Session> sessionPage) {
        return ListUpcomingSessionsOutput.builder()
            .page(sessionPage.getNumber() + 1)
            .totalPages(sessionPage.getTotalPages())
            .size(sessionPage.getSize())
            .isLastPage(sessionPage.isLast())
            .totalItems(sessionPage.getTotalElements())
            .items(sessionPage.stream().map(ListUpcomingSessionsOutput.SessionOutput::new).toList())
            .build();
    }
}
