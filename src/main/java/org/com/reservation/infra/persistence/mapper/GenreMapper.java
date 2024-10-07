package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Genre;
import org.com.reservation.infra.persistence.entity.GenreEntity;
import org.springframework.beans.BeanUtils;

public class GenreMapper {
    public static Genre toGenre(GenreEntity entity) {
        if (entity == null) return null;

        Genre genre = new Genre();
        copyProperties(entity, genre);

        return genre;
    }

    public static GenreEntity toGenreEntity(Genre genre) {
        if (genre == null) return null;

        GenreEntity genreEntity = new GenreEntity();
        copyProperties(genre, genreEntity);

        return genreEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
