package hobbymatch.backend.repositories;

import hobbymatch.backend.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing database operations on Location entities.
 * Extends JpaRepository to provide standard CRUD operations (Create, Read, Update, Delete) and more.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
}
