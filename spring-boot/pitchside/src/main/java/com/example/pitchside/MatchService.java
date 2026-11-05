package com.example.pitchside;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class MatchService {
    MatchService(MatchRepositoryStub matchRepositoryStub) {
        // Constructor logic here
        System.out.println("MatchService created");
    }
}
