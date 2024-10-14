package org.com.reservation.application.factory.movie;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.movie.updateMovie.UpdateMovieUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@Configuration
public class UpdateMovieFactory {
    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final GenreDataProvider genreDataProvider;
    private final MovieTicketDataProvider movieTicketDataProvider;
    private final MovieGenreDataProvider movieGenreDataProvider;

    @Bean("updateMovieUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UpdateMovieUsecase create() {
        return UpdateMovieUsecase.builder()
            .userDataProvider(userDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .movieDataProvider(movieDataProvider)
            .genreDataProvider(genreDataProvider)
            .movieTicketDataProvider(movieTicketDataProvider)
            .movieGenreDataProvider(movieGenreDataProvider)
            .build();
    }
}
