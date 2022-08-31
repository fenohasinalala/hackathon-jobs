package com.hackathon.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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
    @NotBlank(message = "candidate-name is mandatory")
    private String candidateName;

    @Column(length = 100)
    @Email(message = "email must be valid")
    @NotBlank(message = "email is mandatory")
    private String email;

    private String profile;

    private Double salary;

    private LocalDate dateApplication;

    @ManyToOne
    @JoinColumn(name = "id_job_offer", nullable = false)
    private JobOffer jobOffer;
}
