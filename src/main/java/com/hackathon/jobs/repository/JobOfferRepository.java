package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.model.Worker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
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

    /*
    Optional<JobOffer> findByPostIgnoreCase(String post);
    Optional<JobOffer> findByProfileIgnoreCase(String profile);
    Optional<JobOffer> findByLocationIgnoreCase(String location);
    Optional<JobOffer> findByDescriptionIgnoreCase(String description);
    Optional<JobOffer> findByCompanyIgnoreCase(String company);
    Optional<JobOffer> findByAvailable(Boolean available);
    Optional<JobOffer> findByDomainIdDomain(Long idDomain);

     */
    Optional<JobOffer> findByAvailable(Boolean available);
}
