package com.example.challengesApp.controllers;

import com.example.challengesApp.entities.Challenge;
import com.example.challengesApp.services.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping()
    public List<Challenge> getAllChallenges(){
        return challengeService.getAllChallenges();
    }

    @GetMapping("/{month}")
    public Challenge getChallengeByMonth(@PathVariable String month){
        return challengeService.getChallengeByMonth(month);
    }

    @PostMapping()
    public Challenge createChallenge(@RequestBody Challenge challenge){
        return challengeService.createChallenge(challenge);
    }

    @PutMapping("/{id}")
    public Challenge updateChallenge(@PathVariable Long id, @RequestBody Challenge challenge){
        return challengeService.updateChallenge(id, challenge);
    }

    @DeleteMapping("/{id}")
    public void deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallenge(id);
    }
}
