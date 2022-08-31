package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findByIdHistory(Long id);
    Optional<History> findByAvailable(Long id);

}
