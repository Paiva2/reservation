package org.com.reservation.application.factory.session;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.session.getAvailableRoomSeatsOnSession.GetAvailableRoomSeatsOnSessionUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class GetAvailableRoomSeatsOnSessionFactory {
    private final SessionDataProvider sessionDataProvider;
    private final RoomDataProvider roomDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;
    private final RoomsSeatsDataProvider roomsSeatsDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;

    @Bean("getAvailableRoomSeatsOnSessionUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public GetAvailableRoomSeatsOnSessionUsecase create() {
        return GetAvailableRoomSeatsOnSessionUsecase.builder()
            .sessionDataProvider(sessionDataProvider)
            .roomDataProvider(roomDataProvider)
            .reservationDataProvider(reservationDataProvider)
            .reservationRoomSeatDataProvider(reservationRoomSeatDataProvider)
            .roomsSeatsDataProvider(roomsSeatsDataProvider)
            .roomSessionDataProvider(roomSessionDataProvider)
            .build();
    }
}
