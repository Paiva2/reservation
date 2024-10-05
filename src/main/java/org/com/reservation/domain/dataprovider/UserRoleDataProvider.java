package org.com.reservation.domain.dataprovider;

import org.com.reservation.domain.entity.UserRole;

public interface UserRoleDataProvider {
    UserRole persist(UserRole userRole);
}
