package com.example.challengesApp.services.impl;

import com.example.challengesApp.entities.Challenge;
import com.example.challengesApp.repositories.ChallengeRepository;
import com.example.challengesApp.services.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    @Override
    public Challenge getChallengeByMonth(String month) {
        if (month == null || month.isEmpty()) return null;
        return challengeRepository.findByChallengeMonth(month).orElse(null);
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Challenge updateChallenge(Long id, Challenge challenge) {
        Challenge existing = challengeRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setChallengeMonth(challenge.getChallengeMonth());
        existing.setDescription(challenge.getDescription());

        return challengeRepository.save(existing);
    }

    @Override
    public void deleteChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id).orElse(null);
        if (challenge != null) {
            challengeRepository.delete(challenge);
        }
    }
}
