package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEndpoint {

    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return userService.findById(id).map(company -> ResponseEntity.ok(company)).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity findByAccelerationNameOrCompanyId(@RequestParam(name = "accelerationName", required = false) String accelerationName,
                                                            @RequestParam(name = "companyId", required = false) Long companyId) {
        List<User> users = Collections.emptyList();

        if (Optional.ofNullable(accelerationName).isPresent())
            users = this.userService.findByAccelerationName(accelerationName);
        else if (Optional.ofNullable(companyId).isPresent())
            users = userService.findByCompanyId(companyId);

        return ResponseEntity.ok(users);
    }
}
