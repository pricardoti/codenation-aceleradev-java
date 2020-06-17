package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyEndpoint {

    @Autowired
    private CompanyServiceInterface companyService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return companyService.findById(id).map(company -> ResponseEntity.ok(company)).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity findByUserIdOrccelerationId(@RequestParam(name = "userId", required = false) Long userId, @RequestParam(name = "accelerationId", required = false) Long accelerationId) {
        List<Company> companies = Optional.ofNullable(userId).isPresent()
                ? companyService.findByUserId(userId)
                : companyService.findByAccelerationId(accelerationId);
        return companies.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(companies);
    }
}
