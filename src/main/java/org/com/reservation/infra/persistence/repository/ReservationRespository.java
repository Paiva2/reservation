package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRespository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> deleteAllBySessionId(Long sessionId);
}
