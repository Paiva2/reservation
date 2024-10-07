package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Movie;
import org.com.reservation.domain.entity.MovieTicket;
import org.com.reservation.infra.persistence.entity.MovieEntity;
import org.com.reservation.infra.persistence.entity.MovieTicketEntity;
import org.springframework.beans.BeanUtils;

public class MovieTicketMapper {
    public static MovieTicket toMovieTicket(MovieTicketEntity movieTicketEntity) {
        MovieTicket movieTicket = new MovieTicket();
        copyProperties(movieTicketEntity, movieTicket);

        if (movieTicketEntity.getMovie() != null) {
            Movie movie = new Movie();
            movieTicket.setMovie(movie);

            copyProperties(movieTicketEntity.getMovie(), movieTicket.getMovie());
        }

        return movieTicket;
    }

    public static MovieTicketEntity toMovieTicketEntity(MovieTicket movieTicket) {
        MovieTicketEntity movieTicketEntity = new MovieTicketEntity();
        copyProperties(movieTicket, movieTicketEntity);

        if (movieTicket.getMovie() != null) {
            MovieEntity movieEntity = new MovieEntity();
            movieTicketEntity.setMovie(movieEntity);

            copyProperties(movieTicket.getMovie(), movieTicketEntity.getMovie());
        }
        
        return movieTicketEntity;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
