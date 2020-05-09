package com.challenge.entity.embedded;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Company;
import com.challenge.entity.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class CandidateId implements Serializable {

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Acceleration acceleration;

    @NotNull
    @ManyToOne
    private Company company;
}
