package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerationEndpoint {

    @Autowired
    private AccelerationServiceInterface accelerationService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return accelerationService.findById(id).map(row -> ResponseEntity.ok(row)).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity findByCompanyId(@RequestParam("companyId") Long companyId) {
        List<Acceleration> accelerations = accelerationService.findByCompanyId(companyId);
        return accelerations.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(accelerations);
    }
}
