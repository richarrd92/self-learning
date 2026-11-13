package com.cooksys.ftd.social_media.repositories;
import com.cooksys.ftd.social_media.dtos.HashtagDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.ftd.social_media.entities.Hashtag;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    boolean existsByLabelIgnoreCase(String label);
    Hashtag findByLabelIgnoreCase(String label);
}
