package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Role;
import org.com.reservation.infra.persistence.entity.RoleEntity;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;

import java.util.List;
import java.util.Optional;

public class RoleMapper {
    public static Optional<Role> toRoleOptional(RoleEntity roleEntity) {
        return Optional.of(Role.builder()
            .id(roleEntity.getId())
            .name(roleEntity.getName())
            .createdAt(roleEntity.getCreatedAt())
            .updatedAt(roleEntity.getUpdatedAt())
            .build()
        );
    }

    public static Role toRole(RoleEntity roleEntity) {
        return Role.builder()
            .id(roleEntity.getId())
            .name(roleEntity.getName())
            .createdAt(roleEntity.getCreatedAt())
            .updatedAt(roleEntity.getUpdatedAt())
            .build();
    }

    public static RoleEntity toRoleEntity(Role role) {
        List<UserRoleEntity> userRoles;

        if (role.getUserRoles().isEmpty()) {
            userRoles = null;
        } else {
            userRoles = role.getUserRoles().stream().map(UserRoleMapper::toUserRoleEntity).toList();
        }

        return RoleEntity.builder()
            .id(role.getId())
            .name(role.getName())
            .createdAt(role.getCreatedAt())
            .updatedAt(role.getUpdatedAt())
            .userRoles(userRoles)
            .build();
    }
}
