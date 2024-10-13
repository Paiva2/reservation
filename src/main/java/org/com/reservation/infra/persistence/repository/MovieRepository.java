package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTitle(String title);

    @Query(value = """
        SELECT * FROM tb_movies
        WHERE mo_id IN (
                SELECT DISTINCT mo.mo_id
                FROM tb_movies mo
                LEFT JOIN tb_sessions ss ON ss.ss_movie_id = mo.mo_id
                JOIN tb_movie_tickets mt ON mt.mvt_movie_id = mo.mo_id
                JOIN tb_movies_genres mvg ON mvg.mg_movie_id = mo.mo_id
                JOIN tb_genres gnr ON gnr.gr_id = mvg.mg_genre_id
                WHERE (:title IS NULL OR lower(mo_title) LIKE concat('%', lower(:title), '%'))
                AND (cast(:date AS DATE) IS NULL OR date_trunc('day', ss.ss_start) = date_trunc('day', cast(:date AS DATE)))
                AND (:genre IS NULL OR lower(gnr.gr_name) LIKE concat('%', lower(:genre), '%'))
            )
        """, nativeQuery = true)
    Page<MovieEntity> findAllFilterable(Pageable pageable, @Param("title") String title, @Param("genre") String genre, @Param("date") Date date);
}
