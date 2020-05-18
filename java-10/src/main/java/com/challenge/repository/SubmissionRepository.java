package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId> {

    @Query(value = "SELECT MAX(S.SCORE) AS HIGHER_SICORE " +
            "FROM SUBMISSION S " +
            "WHERE S.CHALLENGE_ID = :challengeId",
            nativeQuery = true)
    Optional<BigDecimal> findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "SELECT S.* " +
            "FROM SUBMISSION S" +
            ", CHALLENGE C" +
            ", ACCELERATION A " +
            "WHERE S.CHALLENGE_ID = C.ID " +
            "AND C.ID = A.CHALLENGE_ID " +
            "AND S.CHALLENGE_ID = :challengeId " +
            "AND A.ID = :accelerationId",
            nativeQuery = true)
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);
}
