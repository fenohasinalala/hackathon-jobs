package com.hackathon.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApplication;

    @Column(length = 100)
    private String candidateName;

    @Column(length = 100)
    @Email
    private String email;

    private String profile;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "id_job_offer", nullable = false)
    private JobOffer jobOffer;
}
