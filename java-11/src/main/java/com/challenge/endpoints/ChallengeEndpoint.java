package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeEndpoint {

    @Autowired
    private ChallengeServiceInterface challengeService;

    @GetMapping
    public ResponseEntity findByAccelerationIdAndUserId(@RequestParam(name = "accelerationId", required = false) Long accelerationId, @RequestParam(name = "userId", required = false) Long userId) {
        List<Challenge> challenges = challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
        return challenges.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(challenges);
    }
}
