package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.UserRole;

import java.util.List;

public interface UserRoleDataProvider {
    UserRole persist(UserRole userRole);

    List<UserRole> findByUserId(Long userId);
}
