package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateEndpoint {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findAllCandidateByCompanyId(@RequestParam(name = "companyId") Long id) {
        return ResponseEntity.ok(candidateMapper.map(candidateService.findByCompanyId(id)));
    }

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity findCandidateByUserIdAndCompanyIdAndAccelerationId(
            @PathVariable("userId") Long userId,
            @PathVariable("companyId") Long companyId,
            @PathVariable("accelerationId") Long accelerationId) {
        CandidateDTO candidate = candidateMapper.map(candidateService.findById(userId, companyId, accelerationId).orElse(new Candidate()));
        return ResponseEntity.ok(candidate);
    }
}
