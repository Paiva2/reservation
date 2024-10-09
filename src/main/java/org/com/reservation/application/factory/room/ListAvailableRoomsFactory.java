package org.com.reservation.application.factory.room;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.RoomDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserDataProvider;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.usecase.room.listAvailableRooms.ListAvailableRoomsUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class ListAvailableRoomsFactory {
    private final UserDataProvider userDataProvider;
    private final RoomDataProvider roomDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;

    @Bean("listAvailableRoomsUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ListAvailableRoomsUsecase create() {
        return ListAvailableRoomsUsecase.builder()
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .roomDataProvider(roomDataProvider)
            .build();
    }
}
