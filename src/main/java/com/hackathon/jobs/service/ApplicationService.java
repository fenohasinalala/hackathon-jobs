package com.hackathon.jobs.service;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.model.Application;
import com.hackathon.jobs.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {
    private ApplicationRepository applicationRepository;

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
}
