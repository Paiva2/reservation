package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query("SELECT rm FROM RoomEntity rm JOIN FETCH rm.roomSeats rms ")
    Page<RoomEntity> findAllAvailable(Pageable pageable);
}
