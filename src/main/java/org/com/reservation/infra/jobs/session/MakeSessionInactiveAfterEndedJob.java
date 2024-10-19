package org.com.reservation.infra.jobs.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.com.reservation.domain.interfaces.dataprovider.SessionDataProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Builder
@Slf4j
@Service
public class MakeSessionInactiveAfterEndedJob {
    private final SessionDataProvider sessionDataProvider;

    @Scheduled(cron = "0 0 */3 * * *")
    public void execute() {
        log.info("Running MakeSessionInactiveAfterEndedJob");
        sessionDataProvider.makeFinishedSessionsInactive();
        log.info("Finishing MakeSessionInactiveAfterEndedJob");
    }
}
