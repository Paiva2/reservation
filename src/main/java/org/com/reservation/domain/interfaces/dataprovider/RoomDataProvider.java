package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomDataProvider {
    Optional<Room> findById(Long id);

    List<Room> findManyById(Set<Long> ids);

    Page<Room> findAvailables(Pageable pageable);
}
