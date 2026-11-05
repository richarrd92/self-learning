package com.example.pitchside;

import org.springframework.stereotype.Controller;

@Controller 
public class MatchController {
    MatchController(MatchService matchService) {
        System.out.println("MatchController created with hascode: " + System.identityHashCode(matchService));
    }
}
