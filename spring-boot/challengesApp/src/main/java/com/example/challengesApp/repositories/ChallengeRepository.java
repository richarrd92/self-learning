package com.example.challengesApp.repositories;

import com.example.challengesApp.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    // Query by the String month
    Optional<Challenge> findByChallengeMonth(String challengeMonth);
}
