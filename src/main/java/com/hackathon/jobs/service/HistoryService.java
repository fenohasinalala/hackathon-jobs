package com.hackathon.jobs.service;

import com.hackathon.jobs.model.History;
import com.hackathon.jobs.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryService {
    private HistoryRepository repository;

    //GET MAPPING
    public List<History> allHistory(){
        return repository.findAll();
    }

    public Optional<History> getHistoryById(Long id){
        return repository.findById(id);
    }

    //POST MAPPING
    public History insertCategory(History history){
        return repository.save(history);
    }

    //DELETE MAPPING
    public ResponseEntity<String> deleteById(Long id){
        repository.deleteById(id);
        return ResponseEntity.ok(("Booking deleted successfully!"));

    }

}