package com.hackathon.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hackathon.jobs.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdBook(Long id);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrSynopsisContainingIgnoreCase(String title, String author, String synopsis);
}
