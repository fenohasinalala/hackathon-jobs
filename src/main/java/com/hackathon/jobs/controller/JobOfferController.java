package com.hackathon.jobs.controller;

import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.service.JobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class JobOfferController {

    private JobOfferService jobOfferService;

    @GetMapping("/job-offers")
    public List<JobOffer> getJobOffers(@RequestParam int page,
                                     @RequestParam(value = "page_size") int pageSize,
                                     @RequestParam(value = "reference",required = false , defaultValue = "") String reference,
                                     @RequestParam(value = "post",required = false , defaultValue = "") String post,
                                     @RequestParam(value = "profile",required = false , defaultValue = "") String profile,
                                     @RequestParam(value = "location",required = false , defaultValue = "") String location,
                                     @RequestParam(value = "description",required = false , defaultValue = "") String description){
        return jobOfferService.getJobOffers(page, pageSize, reference, post, profile, location, description);
    }

    @PutMapping("/job-offers/{id}")
    public JobOffer modifyJobOfferById(@PathVariable Long id, @Valid @RequestBody JobOffer newJobOffer) {
        return jobOfferService.putModificationJobOfferById(id, newJobOffer);
    }

}
