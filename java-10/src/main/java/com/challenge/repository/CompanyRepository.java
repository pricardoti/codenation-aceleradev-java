package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    /**
     * Use "distinct" because of the "Candidate" table, can return more than one record for "accelerationId".
     *
     * @Param accelerationId
     * */
    List<Company> findDistinctByCandidatesIdAccelerationId(Long accelerationId);

    List<Company> findByCandidatesIdUserId(Long userId);
}
