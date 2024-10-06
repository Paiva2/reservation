package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.UserRole;

public interface UserRoleDataProvider {
    UserRole persist(UserRole userRole);
}
