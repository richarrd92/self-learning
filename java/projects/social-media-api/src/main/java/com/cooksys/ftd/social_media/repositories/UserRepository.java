package com.cooksys.ftd.social_media.repositories;

import com.cooksys.ftd.social_media.dtos.UserResponseDto;
import com.cooksys.ftd.social_media.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDeletedFalse();

    // Checks if a user exists with the given username (including deleted users)
    boolean existsByCredentialsUsernameIgnoreCase(String username);

    // Check if an active user exists with the given username
    boolean existsByCredentialsUsernameAndDeletedFalseIgnoreCase(String username);

    // Check if a deleted user exists with the given username (for reactivation)
    Optional<User> findByCredentialsUsernameAndDeletedTrueIgnoreCase(String username);

    User findByCredentialsUsername(String username);

}
