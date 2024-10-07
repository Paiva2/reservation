package org.com.reservation.application.factory.movie;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.movie.registerMovie.RegisterMovieUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class RegisterMovieFactory {
    private final UserDataProvider userDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final GenreDataProvider genreDataProvider;
    private final MovieGenreDataProvider movieGenreDataProvider;
    private final MovieTicketDataProvider movieTicketDataProvider;
    
    @Bean("registerMovieUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RegisterMovieUsecase create() {
        return RegisterMovieUsecase.builder()
            .userDataProvider(userDataProvider)
            .movieDataProvider(movieDataProvider)
            .userRoleDataProvider(userRoleDataProvider)
            .genreDataProvider(genreDataProvider)
            .movieGenreDataProvider(movieGenreDataProvider)
            .movieTicketDataProvider(movieTicketDataProvider)
            .build();
    }
}
