package com.hackathon.jobs.service;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.exception.ResourceNotFoundException;
import com.hackathon.jobs.model.Application;
import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.model.validation.ApplicationValidator;
import com.hackathon.jobs.repository.ApplicationRepository;
import com.hackathon.jobs.repository.JobOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ApplicationService {
    private ApplicationRepository applicationRepository;
    private JobOfferRepository jobOfferRepository;

    ApplicationValidator applicationValidator;

    //GET MAPPING
    public List<Application> getAllApplications(int page, int pageSize, String candidateName, String email, String profile, Double salary){
        if(page<= 0){
            throw new BadRequestException("page must be >= 1");
        }
        if(pageSize > 200){
            throw new BadRequestException("page_size is so large");
        }
        Pageable pageable = PageRequest.of(page-1, pageSize);
        if(candidateName != null){
            return applicationRepository.findByCandidateName(candidateName, pageable);
        }else if(email != null){
            return  applicationRepository.findByEmail(email, pageable);
        }else if(profile != null){
            return applicationRepository.findByProfile(profile, pageable);
        }else if(salary != null){
            return applicationRepository.findBySalary(salary, pageable);
        }else {
            return applicationRepository.findAll(pageable).toList();
        }
    }

    //GET BY ID
    public Application getApplicationById(Long id) {
        Application applicationById = applicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("the application with "+id+" does not exist"));
        return applicationById;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //POST MAPPING
    public Application insertApplication(Application applicationParameter) {
        Application newApplication = new Application();
        JobOffer jobOffer = jobOfferRepository.findById(applicationParameter.getJobOffer().getIdJobOffer())
                .orElseThrow(()->new ResourceNotFoundException("the job-offer on "+applicationParameter.getJobOffer().getIdJobOffer()));

        newApplication.setCandidateName(applicationParameter.getCandidateName());
        newApplication.setDateApplication(applicationParameter.getDateApplication());
        newApplication.setEmail(applicationParameter.getEmail());
        newApplication.setProfile(applicationParameter.getProfile());
        newApplication.setSalary(applicationParameter.getSalary());
        newApplication.setJobOffer(jobOffer);
        applicationRepository.save(newApplication);
        return newApplication;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //PUT MAPPING
    public Application putUpdateApplication(Long id, Application applicationToUpdate){
        Application application = applicationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("the application in "+id+" not found"));

        if(applicationToUpdate.getDateApplication() != null){
            application.setDateApplication(applicationToUpdate.getDateApplication());
        }
        if(applicationToUpdate.getEmail() != null){
            application.setEmail(applicationToUpdate.getEmail());
        }
        if(applicationToUpdate.getCandidateName() != null){
            application.setCandidateName(applicationToUpdate.getCandidateName());
        }
        if(applicationToUpdate.getProfile() != null){
            application.setProfile(applicationToUpdate.getProfile());
        }
        if(applicationToUpdate.getSalary() != null){
            application.setSalary(applicationToUpdate.getSalary());
        }
        if(applicationToUpdate.getJobOffer() != null){
            JobOffer newJobOffer = jobOfferRepository.findById(applicationToUpdate.getJobOffer().getIdJobOffer())
                    .orElseThrow(()->new ResourceNotFoundException("the jobOffer in "+applicationToUpdate.getJobOffer().getIdJobOffer()+"is not found"));
            application.setJobOffer(newJobOffer);
        }
        applicationRepository.save(application);
        return application;
    }
}
