package com.cooksys.ftd.social_media.controllers;
import com.cooksys.ftd.social_media.services.UserService;
import com.cooksys.ftd.social_media.services.ValidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
    private final ValidateService validateService;
    private final UserService userService;

    @GetMapping("/tag/exists/{label}")
    public boolean hashtagExists(@PathVariable String label){
        return validateService.hashtagExists(label);
    }

    @GetMapping("/username/exists/@{username}")
    public boolean usernameExists(@PathVariable String username){
        return userService.usernameExists(username);
    }

    @GetMapping("/username/available/@{username}")
    public boolean usernameAvailable(@PathVariable String username){
        return !userService.usernameExists(username);
    }
}
