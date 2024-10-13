package org.com.reservation.application.factory.movie;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.movie.listAllMovies.ListAllMoviesUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class ListAllMoviesFactory {
    private final MovieDataProvider movieDataProvider;

    @Bean("listAllMoviesUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ListAllMoviesUsecase create() {
        return ListAllMoviesUsecase.builder()
            .movieDataProvider(movieDataProvider)
            .build();
    }
}
