package com.hackathon.jobs.controller;

import com.hackathon.jobs.model.Domain;
import com.hackathon.jobs.service.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class DomainController {
    private DomainService domainService;

    @GetMapping("/domains")
    public List<Domain> getDomains(@RequestParam int page,
                                   @RequestParam(value = "page_size") int pageSize,
                                   @RequestParam(value = "name",required = false , defaultValue = "") String name){
        return domainService.getDomains(page, pageSize, name);
    }

    @GetMapping("/domains/{id}")
    public Domain getWorkerById(@PathVariable Long id) throws Exception {
        return domainService.getDomainById(id);
    }
    @PostMapping("/domains")
    public Domain addWorker(@Valid @RequestBody Domain Domain){
        return domainService.addDomain(Domain);
    }

    @PutMapping("/domains/{id}")
    public Domain updateDomainById(@PathVariable Long id, @Valid @RequestBody Domain newDomain) throws Exception {
        return domainService.putUpdateDomainById(id, newDomain);
    }
}
