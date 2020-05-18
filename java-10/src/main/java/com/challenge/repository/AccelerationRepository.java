package com.challenge.repository;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    List<Acceleration> findByName(String name);

    List<Acceleration> findByCandidatesIdCompanyId(Long companyId);
}
