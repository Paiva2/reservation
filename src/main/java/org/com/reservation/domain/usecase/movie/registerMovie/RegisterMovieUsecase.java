package org.com.reservation.domain.usecase.movie.registerMovie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.input.user.RegisterMovieInput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.movie.registerMovie.exception.GenresNotFoundException;
import org.com.reservation.domain.usecase.movie.registerMovie.exception.MovieTitleAlreadyExistsException;
import org.com.reservation.domain.usecase.user.common.InvalidPermissionsException;
import org.com.reservation.domain.usecase.user.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.UserDisabledException;

import java.util.*;

@AllArgsConstructor
@Builder
public class RegisterMovieUsecase {
    private final UserDataProvider userDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final GenreDataProvider genreDataProvider;
    private final MovieGenreDataProvider movieGenreDataProvider;
    private final MovieTicketDataProvider movieTicketDataProvider;

    private final List<EnumRole> validRoles = List.of(EnumRole.ADMIN);

    public void execute(Long userId, RegisterMovieInput input) {
        User user = findUser(userId);
        checkUserDisabled(user);
        checkUserPermissions(user);

        checkMovieWithTitle(input.getTitle());

        List<Genre> genresList = findGenres(input.getMovieGenresIds());

        Movie movie = fillMovieInformations(input);
        movie = persistMovie(movie);

        List<MovieGenre> movieGenres = fillMovieGenre(movie, genresList);
        persistMovieGenres(movieGenres);

        MovieTicket movieTicket = fillMovieTicket(input, movie);
        persistMovieTicket(movieTicket);
    }

    private User findUser(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private void checkUserPermissions(User user) {
        boolean userHasNecessaryRoles = userRoleDataProvider.findByUserId(user.getId()).stream().anyMatch(userRole -> validRoles.contains(userRole.getRole().getName()));

        if (!userHasNecessaryRoles) {
            throw new InvalidPermissionsException();
        }
    }

    private void checkMovieWithTitle(String title) {
        Optional<Movie> movie = movieDataProvider.findMovieByTitle(title);

        if (movie.isPresent()) {
            throw new MovieTitleAlreadyExistsException(title);
        }
    }

    private List<Genre> findGenres(List<Long> ids) {
        Set<Long> genreIds = new HashSet<>(ids);
        List<Genre> foundGenres = genreDataProvider.findGenresByIds(ids);

        if (foundGenres.size() == genreIds.size()) return foundGenres;

        List<Long> foundGenreIds = foundGenres.stream().map(Genre::getId).toList();
        List<Long> genresNotFound = genreIds.stream().filter(genreId -> !foundGenreIds.contains(genreId)).toList();

        if (!genresNotFound.isEmpty()) {
            throw new GenresNotFoundException(genresNotFound);
        }

        return foundGenres;
    }

    private Movie fillMovieInformations(RegisterMovieInput newMovie) {
        if (newMovie.getReleaseDate() != null) {
            setReleaseDateToMinimum(newMovie);
        }

        return Movie.builder()
            .title(newMovie.getTitle())
            .description(newMovie.getDescription())
            .posterImage(newMovie.getPosterImage())
            .trailerVideoUrl(newMovie.getTrailerVideoUrl())
            .durationSeconds(newMovie.getDurationSeconds())
            .releaseDate(newMovie.getReleaseDate())
            .studioName(newMovie.getStudioName())
            .cast(String.join(";", newMovie.getCast()))
            .build();
    }

    private void setReleaseDateToMinimum(RegisterMovieInput newMovie) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newMovie.getReleaseDate());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        newMovie.setReleaseDate(calendar.getTime());
    }

    private Movie persistMovie(Movie movie) {
        return movieDataProvider.persist(movie);
    }

    private List<MovieGenre> fillMovieGenre(Movie movie, List<Genre> genresList) {
        List<MovieGenre> movieGenres = new ArrayList<>();

        for (Genre genre : genresList) {
            MovieGenre movieGenre = new MovieGenre();
            MovieGenre.KeyId keyId = new MovieGenre.KeyId();
            keyId.setMovieId(movie.getId());
            keyId.setGenreId(genre.getId());
            movieGenre.setMovie(movie);
            movieGenre.setGenre(genre);
            movieGenre.setId(keyId);

            movieGenres.add(movieGenre);
        }

        return movieGenres;
    }

    private void persistMovieGenres(List<MovieGenre> movieGenres) {
        movieGenreDataProvider.persistAll(movieGenres);
    }

    private MovieTicket fillMovieTicket(RegisterMovieInput input, Movie movie) {
        return MovieTicket.builder()
            .normalPrice(input.getNormalPrice())
            .specialPrice(input.getSpecialPrice())
            .studentPrice(input.getStudentPrice())
            .isFree(input.getIsFree())
            .movie(movie)
            .build();
    }

    private void persistMovieTicket(MovieTicket movieTicket) {
        movieTicketDataProvider.persist(movieTicket);
    }
}
