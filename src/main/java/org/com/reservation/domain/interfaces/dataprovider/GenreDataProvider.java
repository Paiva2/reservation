package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface GenreDataProvider {
    List<Genre> findGenresByIds(List<Long> ids);

    List<Genre> findGenresByIds(Set<Long> ids);

    Page<Genre> listAll(Pageable pageable);
}
