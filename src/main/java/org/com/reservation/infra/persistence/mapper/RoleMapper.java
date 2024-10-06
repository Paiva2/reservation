package org.com.reservation.infra.persistence.mapper;

import org.com.reservation.domain.entity.Role;
import org.com.reservation.infra.persistence.entity.RoleEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class RoleMapper {
    public static Optional<Role> toRoleOptional(RoleEntity roleEntity) {
        if (roleEntity == null) return Optional.empty();

        Role role = new Role();
        copyProperties(roleEntity, role);

        return Optional.of(role);
    }

    private static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
