package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Genre;
import org.com.reservation.domain.interfaces.dataprovider.GenreDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.GenreEntity;
import org.com.reservation.infra.persistence.mapper.GenreMapper;
import org.com.reservation.infra.persistence.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@DataProvider
public class GenreDataProviderImpl implements GenreDataProvider {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findGenresByIds(List<Long> ids) {
        List<GenreEntity> genreEntities = genreRepository.findAllById(ids);
        return genreEntities.stream().map(GenreMapper::toGenre).toList();
    }

    @Override
    public List<Genre> findGenresByIds(Set<Long> ids) {
        List<GenreEntity> genreEntities = genreRepository.findAllById(ids);
        return genreEntities.stream().map(GenreMapper::toGenre).toList();
    }

    @Override
    public Page<Genre> listAll(Pageable pageable) {
        Page<GenreEntity> genresPage = genreRepository.findAll(pageable);
        return genresPage.map(GenreMapper::toGenre);
    }
}
