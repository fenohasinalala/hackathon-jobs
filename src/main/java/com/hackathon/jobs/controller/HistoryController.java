package com.hackathon.jobs.controller;

import com.hackathon.jobs.service.HistoryService;
import com.hackathon.jobs.model.History;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
public class HistoryController {
    private HistoryService service;

    //GET MAPPING
    @GetMapping("/bookings")
    public List<History> getAllHistory(){
        return service.allHistory();
    }
    @GetMapping("/bookings/{id}")
    public Optional<History> getCategoryById(@PathVariable Long id){
        return service.getHistoryById(id);
    }

    //POST MAPPING
    @PostMapping("/bookings")
    public History postHistory(@RequestBody History history){
        return service.insertCategory(history);
    }
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> deleteHistoryById(@PathVariable(name = "id")Long id){
        return service.deleteById(id);
    }

}
