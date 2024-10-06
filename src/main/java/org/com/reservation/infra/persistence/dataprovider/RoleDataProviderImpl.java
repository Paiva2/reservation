package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.interfaces.dataprovider.RoleDataProvider;
import org.com.reservation.domain.entity.Role;
import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.infra.persistence.entity.RoleEntity;
import org.com.reservation.infra.persistence.mapper.RoleMapper;
import org.com.reservation.infra.persistence.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class RoleDataProviderImpl implements RoleDataProvider {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRoleName(EnumRole role) {
        Optional<RoleEntity> roleEntity = roleRepository.findByName(role);
        if (roleEntity.isEmpty()) return Optional.empty();

        return RoleMapper.toRoleOptional(roleEntity.get());
    }
}
