package com.challenge.endpoints;

import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionEndpoint {

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private SubmissionServiceInterface submissionService;

    @GetMapping
    public ResponseEntity findByChallengeIdAndAccelerationId(@RequestParam("challengeId") Long challengeId, @RequestParam("accelerationId") Long accelerationId) {
        List<Submission> submissions = submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
        return submissions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(submissionMapper.map(submissions));
    }
}
