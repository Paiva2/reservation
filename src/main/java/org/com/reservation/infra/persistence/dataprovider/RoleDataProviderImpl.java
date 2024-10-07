package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.entity.Role;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.domain.interfaces.dataprovider.RoleDataProvider;
import org.com.reservation.infra.annotations.DataProvider;
import org.com.reservation.infra.persistence.entity.RoleEntity;
import org.com.reservation.infra.persistence.mapper.RoleMapper;
import org.com.reservation.infra.persistence.repository.RoleRepository;

import java.util.Optional;

@AllArgsConstructor
@DataProvider
public class RoleDataProviderImpl implements RoleDataProvider {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRoleName(EnumRole role) {
        Optional<RoleEntity> roleEntity = roleRepository.findByName(role);
        if (roleEntity.isEmpty()) return Optional.empty();

        return RoleMapper.toRoleOptional(roleEntity.get());
    }
}
