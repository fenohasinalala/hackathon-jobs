package com.hackathon.jobs.repository;

import com.hackathon.jobs.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdCategory(Long id);
    List<Category> findByNameCategoryContainingIgnoreCase(String categoryName);
}
