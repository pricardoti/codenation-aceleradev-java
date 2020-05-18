package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

    @Query(value = "SELECT CG.* " +
            "FROM CHALLENGE CG " +
            ", ACCELERATION A " +
            ", CANDIDATE C " +
            "WHERE CG.ID = A.CHALLENGE_ID " +
            "AND C.ACCELERATION_ID = A.ID " +
            "AND A.ID = :accelerationId " +
            "AND C.USER_ID = :userId",
            nativeQuery = true)
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId, @Param("userId") Long userId);
}