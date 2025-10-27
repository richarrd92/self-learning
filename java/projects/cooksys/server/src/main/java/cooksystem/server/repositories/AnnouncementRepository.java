package cooksystem.server.repositories;

import cooksystem.server.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooksystem.server.entities.Announcement;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCompanyOrderByDateDesc(Company company);
}
