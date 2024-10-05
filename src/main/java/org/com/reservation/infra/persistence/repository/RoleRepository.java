package org.com.reservation.infra.persistence.repository;

import org.com.reservation.domain.enumeration.EnumRole;
import org.com.reservation.infra.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(EnumRole name);
}
