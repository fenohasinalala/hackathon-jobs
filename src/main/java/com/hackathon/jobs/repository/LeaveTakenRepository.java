package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.LeaveTaken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTakenRepository extends JpaRepository<LeaveTaken,Long> {
}
