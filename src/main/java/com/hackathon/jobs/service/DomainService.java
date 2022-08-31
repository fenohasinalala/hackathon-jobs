package com.hackathon.jobs.service;

import com.hackathon.jobs.exception.BadRequestException;
import com.hackathon.jobs.exception.ResourceNotFoundException;
import com.hackathon.jobs.model.Domain;
import com.hackathon.jobs.model.validation.DomainValidator;
import com.hackathon.jobs.repository.DomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class DomainService {
    private DomainRepository domainRepository;
    DomainValidator domainValidator;


    //GET MAPPING
    public List<Domain> getDomains(int page, int pageSize, String name) {
        if(page<1){
            throw new BadRequestException("page must be >=1");
        }
        if(pageSize>200){
            throw new BadRequestException("page size too large, must be <=200");
        }
        Pageable pageable = PageRequest.of(page - 1,pageSize,
                Sort.by(ASC,"name"));
        return domainRepository.findByNameIgnoreCase(name, pageable);
    }

    //GET MAPPING BY ID
    public Domain getDomainById(Long id) {
        Domain domain = domainRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Domain with id "+id+" does not exist"));
        return domain;
    }

    //POST MAPPING
    public Domain addDomain(Domain domain) {
        domainValidator.accept(domain);
        Optional<Domain> domainByName = domainRepository.findDomainByName(domain.getName());
        if (domainByName.isPresent()){
            throw new BadRequestException("Domain is already existed");
        }
        domainRepository.save(domain);
        return domain;
    }

    //PUT MAPPING
    @Transactional
    public Domain putUpdateDomainById(Long id, Domain newDomain) {
        domainValidator.accept(newDomain);
        Domain domain = domainRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Worker with id "+id+" does not exists"));
        // Test sans !=null et .length>0
        if(!Objects.equals(newDomain.getName(),domain.getName())){
            domain.setName(newDomain.getName());
        }
        return domain;
    }
}
