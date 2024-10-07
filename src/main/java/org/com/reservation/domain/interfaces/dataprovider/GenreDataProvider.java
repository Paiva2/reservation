package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Genre;

import java.util.List;

public interface GenreDataProvider {
    List<Genre> findGenresByIds(List<Long> ids);
}
