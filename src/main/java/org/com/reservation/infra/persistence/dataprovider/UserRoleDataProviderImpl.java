package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;
import org.com.reservation.infra.persistence.mapper.UserRoleMapper;
import org.com.reservation.infra.persistence.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserRoleDataProviderImpl implements UserRoleDataProvider {
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole persist(UserRole userRole) {
        UserRoleEntity userRoleMapped = UserRoleMapper.toUserRoleEntity(userRole);
        UserRoleEntity userRoleEntity = userRoleRepository.save(userRoleMapped);
        return UserRoleMapper.toUserRole(userRoleEntity);
    }
}
