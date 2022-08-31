package com.hackathon.jobs.service;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.repository.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class JobOfferService {
    JobOfferRepository jobOfferRepository;

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
}
