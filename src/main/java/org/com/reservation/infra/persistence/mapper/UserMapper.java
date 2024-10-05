package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.infra.persistence.entity.UserEntity;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;

import java.util.List;
import java.util.Optional;

public class UserMapper {
    public static Optional<User> toUserOptional(Optional<UserEntity> userEntity) {
        if (userEntity.isEmpty()) return Optional.empty();

        List<UserRole> userRoles;
        List<Reservation> reservations;

        if (userEntity.get().getUserRoles().isEmpty()) {
            userRoles = null;
        } else {
            userRoles = userEntity.get().getUserRoles().stream().map(UserRoleMapper::toUserRole).toList();
        }

        if (userEntity.get().getReservations().isEmpty()) {
            reservations = null;
        } else {
            reservations = userEntity.get().getReservations().stream().map(ReservationMapper::toReservation).toList();
        }

        return Optional.of(User.builder()
            .firstName(userEntity.get().getFirstName())
            .lastName(userEntity.get().getLastName())
            .profilePicture(userEntity.get().getProfilePicture())
            .email(userEntity.get().getEmail())
            .password(userEntity.get().getPassword())
            .userRoles(userRoles)
            .createdAt(userEntity.get().getCreatedAt())
            .updatedAt(userEntity.get().getUpdatedAt())
            .disabledAt(userEntity.get().getDisabledAt())
            .reservations(reservations)
            .build()
        );
    }

    public static User toUser(UserEntity userEntity) {
        List<UserRole> userRoles;
        List<Reservation> reservations;

        if (userEntity.getUserRoles().isEmpty()) {
            userRoles = null;
        } else {
            userRoles = userEntity.getUserRoles().stream().map(UserRoleMapper::toUserRole).toList();
        }

        if (userEntity.getReservations().isEmpty()) {
            reservations = null;
        } else {
            reservations = userEntity.getReservations().stream().map(ReservationMapper::toReservation).toList();
        }

        return User.builder()
            .firstName(userEntity.getFirstName())
            .lastName(userEntity.getLastName())
            .profilePicture(userEntity.getProfilePicture())
            .email(userEntity.getEmail())
            .password(userEntity.getPassword())
            .userRoles(userRoles)
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .disabledAt(userEntity.getDisabledAt())
            .reservations(reservations)
            .build();
    }

    public static UserEntity toUserEntity(User user) {
        List<UserRoleEntity> userRoles;

        if (user.getUserRoles().isEmpty()) {
            userRoles = null;
        } else {
            userRoles = user.getUserRoles().stream().map(UserRoleMapper::toUserRoleEntity).toList();
        }

        return UserEntity.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .profilePicture(user.getProfilePicture())
            .email(user.getEmail())
            .password(user.getPassword())
            .userRoles(userRoles)
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .disabledAt(user.getDisabledAt())
            .build();
    }
}
