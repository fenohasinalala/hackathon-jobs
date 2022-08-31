package com.hackathon.jobs.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class JobOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobOffer;

    @NotBlank(message = "Reference is mandatory")
    private String reference;

    @NotBlank(message = "Post is mandatory")
    private String post;

    @NotBlank(message = "Profile is mandatory")
    private String profile;

    private String location;

    //@Column(columnDefinition = "varchar(10000) default 'Aucune description'")
    private String description;

    //@Column(columnDefinition = "boolean default true")
    private boolean available;

    //@Column(columnDefinition = "varchar(100) default 'Anonyme'")
    private String company;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Contract is mandatory")
    private JobOffer.Contract contract;

    @ManyToOne
    @JoinColumn(name = "id_domain", nullable = false)
    private Domain domain;

    public enum Contract {
        CDD,CDI;
    }

}
