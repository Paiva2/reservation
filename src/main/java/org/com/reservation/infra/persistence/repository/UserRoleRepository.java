package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleEntity.KeyId> {
    @Query("SELECT UR From UserRoleEntity UR JOIN FETCH UR.role rl JOIN FETCH UR.user usr WHERE usr.id IN (:userId)")
    List<UserRoleEntity> findAllByUserId(@Param("userId") Long userId);
}
