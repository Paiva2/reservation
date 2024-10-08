package org.com.reservation.application.factory.genre;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.GenreDataProvider;
import org.com.reservation.domain.usecase.genre.listGenresUsecase.ListGenresUsecase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@AllArgsConstructor
public class ListGenresFactory {
    private final GenreDataProvider genreDataProvider;

    @Bean("listGenresUsecase")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ListGenresUsecase create() {
        return ListGenresUsecase.builder()
            .genreDataProvider(genreDataProvider)
            .build();
    }
}
