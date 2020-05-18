package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByCandidatesIdAccelerationName(String name);

    List<User> findByCandidatesIdCompanyId(Long companyId);
}
