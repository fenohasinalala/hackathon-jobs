package com.hackathon.jobs.service;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.exception.ResourceNotFoundException;

import com.hackathon.jobs.model.Domain;
import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.model.validation.JobOfferValidator;
import com.hackathon.jobs.repository.DomainRepository;
import com.hackathon.jobs.repository.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import java.util.ArrayList;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class JobOfferService {
    JobOfferRepository jobOfferRepository;
    JobOfferValidator jobOfferValidator;
    DomainRepository domainRepository;

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

    //GET MAPPING JOB OFFERS COUNTS
    public int getJobOfferCount(){
        return jobOfferRepository.findAll().size();
    }

    //GET MAPPING DOMAIN JOB OFFERS COUNTS
    public List<JobOffer> getJobOfferByIdDomain(Long idDomain){
        List<JobOffer> allJobOffers = jobOfferRepository.findAll();
        List<JobOffer> jobOffersByDomain = new ArrayList<>();
        for(JobOffer jobOffer : allJobOffers){
            if(jobOffer.getDomain().getIdDomain() == idDomain){
                jobOffersByDomain.add(jobOffer);
            }
        }
        return jobOffersByDomain;
    }
    public int getJobOfferCountByDomainId(Long idDomain){
        return this.getJobOfferByIdDomain(idDomain).size();
    }

    //POST MAPPING
    public JobOffer postJobOffer(JobOffer jobOffer) {
        jobOfferValidator.accept(jobOffer);
        Optional<JobOffer> jobOfferByReference = jobOfferRepository.findByReferenceIgnoreCase(jobOffer.getReference());
//        Optional<JobOffer> jobOfferByAvailable = jobOfferRepository.findByAvailable(jobOffer.isAvailable());

        if (jobOfferByReference.isPresent()){
            throw new BadRequestException("Reference is already existed");
        }
        jobOfferRepository.save(jobOffer);
        return jobOffer;
    }


    //PUT MAPPING
    @Transactional
    public JobOffer putModificationJobOfferById(Long id, JobOffer newJobOffer) {
        JobOffer JobOffer = jobOfferRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Job Offer with id "+id+" does not exists"));
        jobOfferValidator.accept(newJobOffer);
        Optional<JobOffer> JobOfferHasReference = jobOfferRepository.findByReference(newJobOffer.getReference());
        if(JobOfferHasReference.isPresent() && !Objects.equals(JobOfferHasReference.get().getReference(),JobOffer.getReference())){
            throw new BadRequestException("Reference already taken");
        }
        Domain domain = domainRepository.findById(newJobOffer.getDomain().getIdDomain())
                .orElseThrow(()->new ResourceNotFoundException("Domain with id "+id+" does not exists"));
        JobOffer.setReference(newJobOffer.getReference());
        JobOffer.setPost(newJobOffer.getPost());
        JobOffer.setProfile(newJobOffer.getProfile());
        JobOffer.setLocation(newJobOffer.getLocation());
        JobOffer.setDescription(newJobOffer.getDescription());
        JobOffer.setCompany(newJobOffer.getCompany());
        JobOffer.setContract(newJobOffer.getContract());
        JobOffer.setAvailable(newJobOffer.isAvailable());
        JobOffer.setDomain(domain);

        return JobOffer;
    }
}
