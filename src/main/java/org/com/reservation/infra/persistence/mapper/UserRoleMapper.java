package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;

public class UserRoleMapper {
    public static UserRoleEntity toUserRoleEntity(UserRole userRole) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(RoleMapper.toRoleEntity(userRole.getRole()));
        userRoleEntity.setUser(UserMapper.toUserEntity(userRole.getUser()));
        return userRoleEntity;
    }

    public static UserRole toUserRole(UserRoleEntity userRoleEntity) {
        return UserRole.builder()
            .role(RoleMapper.toRole(userRoleEntity.getRole()))
            .user(UserMapper.toUser(userRoleEntity.getUser()))
            .createdAt(userRoleEntity.getCreatedAt())
            .updatedAt(userRoleEntity.getUpdatedAt())
            .build();
    }
}
