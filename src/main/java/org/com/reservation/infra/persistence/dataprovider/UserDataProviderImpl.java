package org.com.reservation.infra.persistence.dataprovider;

import lombok.AllArgsConstructor;
import org.com.reservation.domain.dataprovider.UserDataProvider;
import org.com.reservation.domain.entity.User;
import org.com.reservation.infra.persistence.entity.UserEntity;
import org.com.reservation.infra.persistence.mapper.UserMapper;
import org.com.reservation.infra.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserDataProviderImpl implements UserDataProvider {
    private final UserRepository userRepository;
    
    @Override
    public Optional<User> findByUserId(Long id) {
        Optional<User> user = UserMapper.toUserOptional(userRepository.findById(id));
        return user;
    }

    @Override
    public Optional<User> findByUserEmail(String email) {
        Optional<User> user = UserMapper.toUserOptional(userRepository.findByEmail(email));
        return user;
    }

    @Override
    public User persist(User user) {
        UserEntity userConverted = UserMapper.toUserEntity(user);
        UserEntity userSaved = userRepository.save(userConverted);
        User userSavedConverted = UserMapper.toUser(userSaved);
        return userSavedConverted;
    }
}
