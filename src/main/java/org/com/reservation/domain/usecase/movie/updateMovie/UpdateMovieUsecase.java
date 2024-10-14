package org.com.reservation.domain.usecase.movie.updateMovie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.com.reservation.application.controller.dto.input.movie.UpdateMovieInput;
import org.com.reservation.application.controller.dto.output.movie.UpdateMovieOutput;
import org.com.reservation.domain.entity.*;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.*;
import org.com.reservation.domain.usecase.common.exception.MovieNotFoundException;
import org.com.reservation.domain.usecase.common.exception.MovieTicketNotFoundException;
import org.com.reservation.domain.usecase.movie.registerMovie.exception.GenresNotFoundException;
import org.com.reservation.domain.usecase.movie.registerMovie.exception.MovieTitleAlreadyExistsException;
import org.com.reservation.domain.usecase.user.common.InvalidPermissionsException;
import org.com.reservation.domain.usecase.user.common.exception.UserNotFoundException;
import org.com.reservation.domain.usecase.user.userAuthentication.exception.UserDisabledException;

import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor
@Builder
public class UpdateMovieUsecase {
    private final static List<EnumRole> necessaryRoles = List.of(EnumRole.ADMIN);

    private final UserDataProvider userDataProvider;
    private final UserRoleDataProvider userRoleDataProvider;
    private final MovieDataProvider movieDataProvider;
    private final GenreDataProvider genreDataProvider;
    private final MovieTicketDataProvider movieTicketDataProvider;
    private final MovieGenreDataProvider movieGenreDataProvider;

    public UpdateMovieOutput execute(Long userId, Long movieId, UpdateMovieInput input) {
        User user = findAdmin(userId);
        checkUserDisabled(user);
        checkUserPermissions(user);

        Movie movie = findMovie(movieId);

        if (input.getTitle() != null && !input.getTitle().isBlank()) {
            checkExistingTitle(input, movie.getId());
        }

        fillMovieUpdate(input, movie);
        Movie movieUpdated = updateMovie(movie);

        if (input.getGenresIds() != null && !input.getGenresIds().isEmpty()) {
            deleteCurrentMovieGenres(movie.getId());
            List<Genre> genresList = findGenres(input);
            List<MovieGenre> newMovieGenres = fillMovieGenre(movie, genresList);

            movieUpdated.setMovieGenres(saveNewMovieGenres(newMovieGenres));
        }

        if (input.getTicket() != null) {
            MovieTicket movieTicket = findMovieTicket(movie.getId());
            fillMovieTicket(input, movieTicket);

            movieUpdated.setMovieTicket(updateMovieTicket(movieTicket));
        }

        return mountOutput(movieUpdated);
    }

    private User findAdmin(Long userId) {
        return userDataProvider.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private void checkUserDisabled(User user) {
        if (user.getDisabledAt() != null) {
            throw new UserDisabledException();
        }
    }

    private void checkUserPermissions(User user) {
        List<Role> userRoles = userRoleDataProvider.findByUserId(user.getId()).stream().map(UserRole::getRole).toList();

        if (userRoles.isEmpty()) {
            throw new InvalidPermissionsException();
        }

        boolean hasNecessaryRole = userRoles.stream().anyMatch(role -> necessaryRoles.contains(role.getName()));

        if (!hasNecessaryRole) {
            throw new InvalidPermissionsException();
        }
    }

    private Movie findMovie(Long movieId) {
        return movieDataProvider.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }

    private void checkExistingTitle(UpdateMovieInput input, Long movieBeingEditedId) {
        Optional<Movie> findMovieTitle = movieDataProvider.findMovieByTitle(input.getTitle());

        if (findMovieTitle.isEmpty() || findMovieTitle.get().getId().equals(movieBeingEditedId)) return;

        throw new MovieTitleAlreadyExistsException(input.getTitle());
    }

    private List<Genre> findGenres(UpdateMovieInput input) {
        Set<Long> genresIdsNonDuplicated = new HashSet<>(input.getGenresIds());
        List<Genre> genres = genreDataProvider.findGenresByIds(genresIdsNonDuplicated);

        List<Long> genresFoundIds = genres.stream().map(Genre::getId).toList();
        List<Long> genresNotFoundIds = new ArrayList<>();

        for (Long genresIdProvided : genresIdsNonDuplicated) {
            if (!genresFoundIds.contains(genresIdProvided)) {
                genresNotFoundIds.add(genresIdProvided);
            }
        }

        if (!genresNotFoundIds.isEmpty()) {
            throw new GenresNotFoundException(genresNotFoundIds);
        }

        return genres;
    }

    private List<MovieGenre> fillMovieGenre(Movie movie, List<Genre> genreList) {
        List<MovieGenre> movieGenres = new ArrayList<>();

        for (Genre genre : genreList) {
            MovieGenre movieGenre = new MovieGenre();
            MovieGenre.KeyId keyId = new MovieGenre.KeyId();
            movieGenre.setMovie(movie);
            movieGenre.setGenre(genre);
            keyId.setGenreId(genre.getId());
            keyId.setMovieId(movie.getId());
            movieGenre.setId(keyId);

            movieGenres.add(movieGenre);
        }

        return movieGenres;
    }

    private List<MovieGenre> saveNewMovieGenres(List<MovieGenre> movieGenres) {
        return movieGenreDataProvider.persistAll(movieGenres);
    }

    private void deleteCurrentMovieGenres(Long movieId) {
        movieGenreDataProvider.deleteAllByMovieId(movieId);
    }

    private MovieTicket findMovieTicket(Long movieId) {
        return movieTicketDataProvider.findByMovieId(movieId).orElseThrow(MovieTicketNotFoundException::new);
    }

    private void fillMovieTicket(UpdateMovieInput input, MovieTicket movieTicket) {
        UpdateMovieInput.MovieTicketInput movieTicketInput = input.getTicket();

        movieTicket.setIsFree(movieTicketInput.getIsFree() != null ? movieTicketInput.getIsFree() : movieTicket.getIsFree());
        movieTicket.setNormalPrice(movieTicketInput.getNormalPrice() != null ? new BigDecimal(movieTicketInput.getNormalPrice()) : movieTicket.getNormalPrice());
        movieTicket.setSpecialPrice(movieTicketInput.getSpecialPrice() != null ? new BigDecimal(movieTicketInput.getSpecialPrice()) : movieTicket.getSpecialPrice());
        movieTicket.setStudentPrice(movieTicketInput.getStudentPrice() != null ? new BigDecimal(movieTicketInput.getStudentPrice()) : movieTicket.getStudentPrice());
    }

    private MovieTicket updateMovieTicket(MovieTicket movieTicket) {
        return movieTicketDataProvider.persist(movieTicket);
    }

    private void fillMovieUpdate(UpdateMovieInput input, Movie movie) {
        movie.setTitle(input.getTitle());
        movie.setDescription(input.getDescription());
        movie.setPosterImage(input.getPosterImage());
        movie.setTrailerVideoUrl(input.getTrailerVideoUrl());
        movie.setReleaseDate(input.getReleaseDate());
        movie.setStudioName(input.getStudioName());
        movie.setDurationSeconds(input.getDurationSeconds() != null ? input.getDurationSeconds() : movie.getDurationSeconds());

        if (input.getCast() != null) {
            if (input.getCast().isEmpty()) {
                movie.setCast(null);
            } else {
                movie.setCast(String.join(";", input.getCast()));
            }
        }
    }

    private Movie updateMovie(Movie movie) {
        return movieDataProvider.persist(movie);
    }

    private UpdateMovieOutput mountOutput(Movie movie) {
        return UpdateMovieOutput.toOutput(movie);
    }
}
