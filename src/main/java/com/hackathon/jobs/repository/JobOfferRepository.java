package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.model.Worker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    List<JobOffer> findByReferenceContainingIgnoreCaseAndPostContainingIgnoreCaseAndProfileContainingIgnoreCaseAndLocationContainingIgnoreCaseAndDescriptionContainingIgnoreCase(
            Pageable pageable,
            String reference,
            String post,
            String profile,
            String location,
            String description);

    Optional<JobOffer> findByReference(String reference);
}
