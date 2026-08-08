package hobbymatch.backend.repositories;

import hobbymatch.backend.entities.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing database operations on Hobby entities.
 * Extends JpaRepository to provide CRUD operations (Create, Read, Update, Delete) and more.
 */
@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByName(String hobbyName);
}
