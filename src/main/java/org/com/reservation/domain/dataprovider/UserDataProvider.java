package org.com.reservation.domain.dataprovider;

import org.com.reservation.domain.entity.User;

import java.util.Optional;

public interface UserDataProvider {
    Optional<User> findByUserId(Long id);

    Optional<User> findByUserEmail(String email);

    User persist(User user);
}
