package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.RoomSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsSeatsRepository extends JpaRepository<RoomSeatEntity, RoomSeatEntity.KeyId> {
    @Query("SELECT rs FROM RoomSeatEntity rs " +
        "JOIN FETCH rs.room rm " +
        "JOIN FETCH rs.seat st " +
        "WHERE rm.id = :roomId " +
        "ORDER BY st.row, st.number ASC")
    List<RoomSeatEntity> findAllByRoomSorted(Long roomId);
}
