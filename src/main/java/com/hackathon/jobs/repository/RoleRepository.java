package com.hackathon.jobs.repository;

import java.util.Optional;

import com.hackathon.jobs.model.ERole;
import com.hackathon.jobs.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
