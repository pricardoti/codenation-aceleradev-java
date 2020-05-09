package com.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class
User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column
    private String fullName;

    @NotNull
    @Size(max = 100)
    @Email
    @Column
    private String email;

    @NotNull
    @Size(max = 50)
    @Column
    private String nickName;

    @NotNull
    @Size(max = 255)
    @Column
    private String password;

    @NotNull
    @CreatedDate
    @Column
    private Date createdAt;

    @OneToMany
    private Set<Candidate> candidates;

    @OneToMany
    private Set<Submission> submissions;
}
