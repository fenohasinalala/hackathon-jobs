package com.hackathon.jobs.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    @NotNull(message = "salary is mandatory")
    private Integer salary;

    private LocalDate dateApplication;

    @ManyToOne
    @JoinColumn(name = "id_job_offer", nullable = false)
    private JobOffer jobOffer;
}
