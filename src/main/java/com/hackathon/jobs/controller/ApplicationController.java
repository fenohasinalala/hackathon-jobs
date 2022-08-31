package com.hackathon.jobs.controller;

import com.hackathon.jobs.model.Application;
import com.hackathon.jobs.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ApplicationController {
    private ApplicationService applicationService;

    @GetMapping("/applications")
    public List<Application> getAllApplication(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize,
            @RequestParam(name = "candidate_name")String candidateName,
            @RequestParam(name = "email")String email,
            @RequestParam(name = "profile")String profile,
            @RequestParam(name = "salary")Double salary
    ){
        return applicationService.getAllApplications(page, pageSize, candidateName, email, profile, salary);
    }

    @GetMapping("/applications/{id}")
    public Application getApplicationById(@PathVariable(name = "id")Long id){
        return applicationService.getApplicationById(id);
    }

    @PostMapping("/applications")
    public Application insertApplication(@RequestBody Application newApplication) throws SQLException {
        return applicationService.insertApplication(newApplication);
    }
}
