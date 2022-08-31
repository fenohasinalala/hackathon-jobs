package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.Domain;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DomainRepository extends JpaRepository<Domain,Long> {
    List<Domain> findByNameIgnoreCase(String name, Pageable pageable);
    Optional<Domain> findDomainByName(String name);

}
