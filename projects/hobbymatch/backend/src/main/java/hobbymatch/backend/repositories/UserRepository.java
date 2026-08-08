package hobbymatch.backend.repositories;

import hobbymatch.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing database operations on User entities.
 * Extends JpaRepository to provide standard CRUD operations (Create, Read, Update, Delete) and more.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
