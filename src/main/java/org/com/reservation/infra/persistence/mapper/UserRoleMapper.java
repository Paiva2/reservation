package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Role;
import org.com.reservation.domain.entity.User;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.infra.persistence.entity.RoleEntity;
import org.com.reservation.infra.persistence.entity.UserEntity;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;
import org.springframework.beans.BeanUtils;

public class UserRoleMapper {
    public static UserRoleEntity toUserRoleEntity(UserRole userRole) {
        if (userRole == null) return null;

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setId(new UserRoleEntity.KeyId());

        copyProperties(userRole, userRoleEntity);

        if (userRole.getRole() != null) {
            userRoleEntity.setRole(new RoleEntity());
            copyProperties(userRole.getRole(), userRoleEntity.getRole());
        }

        if (userRole.getUser() != null) {
            userRoleEntity.setUser(new UserEntity());
            copyProperties(userRole.getUser(), userRoleEntity.getUser());
        }

        copyProperties(userRole.getId(), userRoleEntity.getId());

        return userRoleEntity;
    }

    public static UserRole toUserRole(UserRoleEntity userRoleEntity) {
        if (userRoleEntity == null) return null;

        UserRole userRole = new UserRole();
        userRole.setId(new UserRole.KeyId());
        userRole.setRole(new Role());
        userRole.setUser(new User());

        copyProperties(userRoleEntity, userRole);
        copyProperties(userRoleEntity.getId(), userRole.getId());
        copyProperties(userRoleEntity.getRole(), userRole.getRole());
        copyProperties(userRoleEntity.getUser(), userRole.getUser());

        return userRole;
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
