package com.example.challengesApp.services;

import com.example.challengesApp.entities.Challenge;

import java.util.List;

public interface ChallengeService {
    List<Challenge> getAllChallenges();
    Challenge getChallengeByMonth(String month);
    Challenge createChallenge(Challenge challenge);
    Challenge updateChallenge(Long id, Challenge challenge);
    void deleteChallenge(Long id);
}
