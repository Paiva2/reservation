package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query("SELECT rm FROM RoomEntity rm " +
        "JOIN FETCH rm.roomSessions rs " +
        "JOIN FETCH rs.session ss " +
        "WHERE ss.active = true " +
        "AND ss.end < current_timestamp")
    Page<RoomEntity> findAllAvailable(Pageable pageable);
}
