package com.hackathon.jobs.controller;

import com.hackathon.jobs.model.Application;
import com.hackathon.jobs.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class ApplicationController {
    private ApplicationService applicationService;

    ///////////////////////////////////////GET//////////////////////////////////////////////////////////////////
    @GetMapping("/applications")
    public List<Application> getAllApplication(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize,
            @RequestParam(name = "candidate_name", required = false)String candidateName,
            @RequestParam(name = "email", required = false)String email,
            @RequestParam(name = "profile", required = false)String profile,
            @RequestParam(name = "salary", required = false)Double salary
    ){
        return applicationService.getAllApplications(page, pageSize, candidateName, email, profile, salary);
    }

    @GetMapping("/applications/{id}")
    public Application getApplicationById(@PathVariable(name = "id")Long id){
        return applicationService.getApplicationById(id);
    }

    @GetMapping("/domains/{id_domain}/applications")
    public List<Application> getApplicationByDomain(@PathVariable(name = "id_domain")Long idDomain){
        return applicationService.getApplicationByIdDomain(idDomain);
    }

    @GetMapping("/applications/count")
    public int getApplicationCount(){
        return applicationService.getApplicationCount();
    }

    @GetMapping("/domains/{id_domain}/applications/count")
    public int getApplicationCountByDomain(@PathVariable(name = "id_domain")Long idDomain){
        return applicationService.getApplicationCountByDomain(idDomain);
    }

    //////////////////////////////////POST///////////////////////////////////////////////////
    @PostMapping("/applications")
    public Application insertApplication(@RequestBody Application newApplication) {
        return applicationService.insertApplication(newApplication);
    }

    ////////////////////////////////////PUT///////////////////////////////////////////////////////
    @PutMapping("/applications/{id}")
    public Application putUpdateApllication(
            @PathVariable(name = "id")Long id,
            @RequestBody Application newApplication
    ){
        return applicationService.putUpdateApplication(id, newApplication);
    }
}
