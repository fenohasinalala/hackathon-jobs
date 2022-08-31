package com.hackathon.jobs.controller;

import com.hackathon.jobs.model.LeaveTaken;
import com.hackathon.jobs.service.LeaveTakenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class LeaveTakenController {
    private LeaveTakenService leaveTakenService;

    @GetMapping("/leaves")
    public List<LeaveTaken> getLeaves(@RequestParam int page,
                                      @RequestParam(value = "page_size") int pageSize){
        return leaveTakenService.getLeaves(page, pageSize);
    }

    @PostMapping("/leaves")
    public LeaveTaken addLeave(@RequestBody LeaveTaken leaveTaken){
        return leaveTakenService.addLeave(leaveTaken);
    }

    @GetMapping("/leaves/{id}")
    public LeaveTaken getLeaveTakenById(@PathVariable Long id) throws Exception {
        return leaveTakenService.getLeaveTakenById(id);
    }

    @DeleteMapping("/leaves/{id}")
    public LeaveTaken deleteLeaveById(@PathVariable Long id) throws Exception {
        return leaveTakenService.deleteLeaveById(id);
    }
/*
    @PutMapping("/leaves/{id}")
    public Leave modifyLeaveById(@PathVariable Long id, @RequestBody Leave newLeave) throws Exception {
        return leaveTakenService.putModificationLeaveById(id, newLeave);
    }

 */
}
