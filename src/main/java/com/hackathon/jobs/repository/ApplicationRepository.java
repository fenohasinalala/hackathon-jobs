package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.Application;
import com.hackathon.jobs.service.ApplicationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCandidateName(String candidateName, Pageable pageable);
    List<Application> findByEmail(String email, Pageable pageable);
    List<Application> findByProfile(String profile,Pageable pageable);
    List<Application>findBySalary(Double salary, Pageable pageable);

    List<Application> findByJobOfferIdJobOffer(Long id, Pageable pageable);
}
