package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsSeatsRepository extends JpaRepository<RoomSeatEntity, RoomSeatEntity.KeyId> {
}
