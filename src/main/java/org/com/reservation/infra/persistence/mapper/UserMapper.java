package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Reservation;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.com.reservation.infra.persistence.entity.UserEntity;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper {
    public static Optional<User> toUserOptional(Optional<UserEntity> userEntity) {
        if (userEntity.isEmpty()) return Optional.empty();

        return Optional.of(convertEntityToDomain(userEntity.get()));
    }

    public static User toUser(UserEntity userEntity) {
        return convertEntityToDomain(userEntity);
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        copyProperties(user, userEntity);

        if (user.getUserRoles() != null) {
            List<UserRole> userRoles = user.getUserRoles();
            List<UserRoleEntity> userRolesEntity = new ArrayList<>();

            for (UserRole userRoleDomain : userRoles) {
                UserRoleEntity userRoleEntity = UserRoleMapper.toUserRoleEntity(userRoleDomain);
                userRolesEntity.add(userRoleEntity);
            }

            userEntity.setUserRoles(userRolesEntity);
        }

        if (user.getReservations() != null) {
            List<Reservation> reservations = user.getReservations();
            List<ReservationEntity> reservationsEntity = new ArrayList<>();

            for (Reservation reservationDomain : reservations) {
                ReservationEntity reservationEntity = ReservationMapper.toReservationEntity(reservationDomain);
                reservationsEntity.add(reservationEntity);
            }

            userEntity.setReservations(reservationsEntity);
        }

        return userEntity;
    }
    
    private static User convertEntityToDomain(UserEntity userEntity) {
        User user = new User();
        copyProperties(userEntity, user);

        if (userEntity.getUserRoles() != null && !userEntity.getUserRoles().isEmpty()) {
            List<UserRoleEntity> userRolesEntity = userEntity.getUserRoles();
            List<UserRole> userRoles = new ArrayList<>();

            for (UserRoleEntity entity : userRolesEntity) {
                UserRole userRole = UserRoleMapper.toUserRole(entity);
                userRoles.add(userRole);
            }

            user.setUserRoles(userRoles);
        }

        if (userEntity.getReservations() != null && !userEntity.getReservations().isEmpty()) {
            List<ReservationEntity> reservationsEntity = userEntity.getReservations();
            List<Reservation> reservations = new ArrayList<>();

            for (ReservationEntity entity : reservationsEntity) {
                Reservation reservation = ReservationMapper.toReservation(entity);
                reservations.add(reservation);
            }

            user.setReservations(reservations);
        }

        return user;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
