package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.RoomSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomSessionRepository extends JpaRepository<RoomSessionEntity, RoomSessionEntity.KeyId> {

    @Query("SELECT rs FROM RoomSessionEntity rs " +
        "JOIN FETCH rs.room rm " +
        "JOIN FETCH rs.session ss " +
        "WHERE rm.id IN (:roomIds) " +
        "AND ss.active IS TRUE " +
        "AND ss.end >= :start")
    List<RoomSessionEntity> findActiveByPeriodAndListOfIds(@Param("start") Date start, @Param("roomIds") List<Long> roomIds);
}
