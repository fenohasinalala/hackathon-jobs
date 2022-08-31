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
public class Domain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDomain;

    @NotBlank(message = "Domain name is mandatory")
    @Column(length = 100, unique = true)
    private String name;

}
