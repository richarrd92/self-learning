package cooksystem.server.repositories;

import cooksystem.server.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooksystem.server.entities.Team;

import java.util.*;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByCompany(Company company);
}
