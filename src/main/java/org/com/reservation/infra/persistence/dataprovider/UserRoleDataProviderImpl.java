package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.UserRole;
import org.com.reservation.domain.interfaces.dataprovider.UserRoleDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.UserRoleEntity;
import org.com.reservation.infra.persistence.mapper.UserRoleMapper;
import org.com.reservation.infra.persistence.repository.UserRoleRepository;

import java.util.List;

@AllArgsConstructor
@DataProvider
public class UserRoleDataProviderImpl implements UserRoleDataProvider {
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole persist(UserRole userRole) {
        UserRoleEntity userRoleMapped = UserRoleMapper.toUserRoleEntity(userRole);
        UserRoleEntity userRoleEntity = userRoleRepository.save(userRoleMapped);
        return UserRoleMapper.toUserRole(userRoleEntity);
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        List<UserRoleEntity> userRoleEntities = userRoleRepository.findAllByUserId(userId);
        return userRoleEntities.stream().map(UserRoleMapper::toUserRole).toList();
    }
}
