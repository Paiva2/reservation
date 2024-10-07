package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Genre;
import org.com.reservation.domain.interfaces.dataprovider.GenreDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.GenreEntity;
import org.com.reservation.infra.persistence.mapper.GenreMapper;
import org.com.reservation.infra.persistence.repository.GenreRepository;

import java.util.List;

@AllArgsConstructor
@DataProvider
public class GenreDataProviderImpl implements GenreDataProvider {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findGenresByIds(List<Long> ids) {
        List<GenreEntity> genreEntities = genreRepository.findAllById(ids);

        return genreEntities.stream().map(GenreMapper::toGenre).toList();
    }
}
