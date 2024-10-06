package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Role;
import org.com.reservation.domain.enumeration.EnumRole;

import java.util.Optional;

public interface RoleDataProvider {
    Optional<Role> findByRoleName(EnumRole role);
}
