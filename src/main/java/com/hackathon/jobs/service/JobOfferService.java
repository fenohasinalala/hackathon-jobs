package com.hackathon.jobs.service;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.exception.ResourceNotFoundException;
import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.model.validation.JobOfferValidator;
import com.hackathon.jobs.repository.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class JobOfferService {
    JobOfferRepository jobOfferRepository;
    JobOfferValidator jobOfferValidator;

    //GET MAPPING all job offers
    public List<JobOffer> getJobOffers(int page, int pageSize, String reference, String post, String profile, String location, String description) {
        if(page<1){
            throw new BadRequestException("page must be >=1");
        }
        if(pageSize>200){
            throw new BadRequestException("page size too large, must be <=200");
        }
        Pageable pageable = PageRequest.of(page - 1,pageSize,
                Sort.by(ASC,"reference"));
        return jobOfferRepository.findByReferenceContainingIgnoreCaseAndPostContainingIgnoreCaseAndProfileContainingIgnoreCaseAndLocationContainingIgnoreCaseAndDescriptionContainingIgnoreCase(pageable, reference, post, profile, location, description);
    }

    //GET MAPPING BY ID
    public JobOffer getJobOffersById(Long id) {
        JobOffer jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Worker with id "+id+" does not exists"));
        return jobOffer;
    }

    //POST MAPPING
    public JobOffer postJobOffer(JobOffer jobOffer) {
        jobOfferValidator.accept(jobOffer);
        Optional<JobOffer> jobOfferByReference = jobOfferRepository.findByReferenceIgnoreCase(jobOffer.getReference());

        if (jobOfferByReference.isPresent()){
            throw new BadRequestException("Reference is already existed");
        }
        jobOfferRepository.save(jobOffer);
        return jobOffer;
    }

}
