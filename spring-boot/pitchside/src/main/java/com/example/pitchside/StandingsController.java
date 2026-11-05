package com.example.pitchside;

import org.springframework.stereotype.Controller;

@Controller 
public class StandingsController {
    StandingsController(MatchService matchService) {
        // Constructor logic here
        System.out.println("StandingsController created with hascode: " + System.identityHashCode(matchService));
        ;
    }
}
