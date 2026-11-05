package cooksystem.server.repositories;

import cooksystem.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCredentialsEmail(String email);
    Optional<User> findByCredentialsUsername(String username);
    List<User> findByCompanies_Id(Long companyId);
}
