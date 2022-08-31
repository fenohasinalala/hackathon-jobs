package com.hackathon.jobs.controller;

import com.hackathon.jobs.model.JobOffer;
import com.hackathon.jobs.service.JobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<JobOffer> getWorkers(@RequestParam int page,
                                     @RequestParam(value = "page_size") int pageSize,
                                     @RequestParam(value = "reference",required = false , defaultValue = "") String reference,
                                     @RequestParam(value = "post",required = false , defaultValue = "") String post,
                                     @RequestParam(value = "profile",required = false , defaultValue = "") String profile,
                                     @RequestParam(value = "location",required = false , defaultValue = "") String location,
                                     @RequestParam(value = "description",required = false , defaultValue = "") String description){
        return jobOfferService.getJobOffers(page, pageSize, reference, post, profile, location, description);
    }
    @GetMapping("/job-offers/{id}")
    public JobOffer getWorkerById(@PathVariable Long id) throws Exception {
        return jobOfferService.getJobOffersById(id);
    }
    @GetMapping("/domains/{id_domain}/job-offers")
    public List<JobOffer> getJobOfferByDomainId(@PathVariable(name = "id_domain")Long idDomain){
        return jobOfferService.getJobOfferByIdDomain(idDomain);
    }
    @GetMapping("/domains/{id_domain}/job-offers/count")
    public int getJobOfferCountByDomain(@PathVariable(name = "id_domain")Long idDomain){
        return jobOfferService.getJobOfferCountByDomainId(idDomain);
    }
    @GetMapping("/job-offers/count")
    public int getJobOfferCount() {
        return jobOfferService.getJobOfferCount();
    }
    @PostMapping("/job-offers")
    public JobOffer postJobOffer(@Valid @RequestBody JobOffer jobOffer){
        return jobOfferService.postJobOffer(jobOffer);
    }
}