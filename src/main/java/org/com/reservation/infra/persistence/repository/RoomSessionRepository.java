package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.RoomSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomSessionRepository extends JpaRepository<RoomSessionEntity, RoomSessionEntity.KeyId> {
    @Modifying
    @Query(value = "DELETE FROM tb_rooms_sessions rs WHERE rs.rs_session_id = :sessionId", nativeQuery = true)
    void deleteAllBySessionId(@Param("sessionId") Long sessionId);

    @Query("SELECT rs FROM RoomSessionEntity rs WHERE rs.room.id = :roomId AND rs.session.id = :sessionId")
    Optional<RoomSessionEntity> findBySessionAndRoom(@Param("sessionId") Long sessionId, @Param("roomId") Long roomId);
}
