package org.com.reservation.application.factory.movie;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.movie.deleteMovie.DeleteMovieUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class DeleteMovieFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final RoomSessionDataProvider roomSessionDataProvider;
    private final SessionDataProvider sessionDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final MovieGenreDataProvider movieGenreDataProvider;
    private final ReservationDataProvider reservationDataProvider;
    private final ReservationMovieTicketDataProvider reservationMovieTicketDataProvider;
    private final ReservationRoomSeatDataProvider reservationRoomSeatDataProvider;
    private final MovieTicketDataProvider movieTicketDataProvider;

    @Bean("deleteMovieUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DeleteMovieUsecase create() {
        return DeleteMovieUsecase.builder()
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .roomSessionDataProvider(roomSessionDataProvider)
            .sessionDataProvider(sessionDataProvider)
            .movieDataProvider(movieDataProvider)
            .movieGenreDataProvider(movieGenreDataProvider)
            .reservationDataProvider(reservationDataProvider)
            .reservationMovieTicketDataProvider(reservationMovieTicketDataProvider)
            .reservationRoomSeatDataProvider(reservationRoomSeatDataProvider)
            .movieTicketDataProvider(movieTicketDataProvider)
            .build();
    }
}
