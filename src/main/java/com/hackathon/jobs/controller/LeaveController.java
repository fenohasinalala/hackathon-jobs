package com.hackathon.jobs.controller;

import com.hackathon.jobs.controller.mapper.LeaveMapper;
import com.hackathon.jobs.model.Leave;
import com.hackathon.jobs.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class LeaveController {
    private LeaveService leaveService;
    private LeaveMapper leaveMapper;

    @GetMapping("/leave-type")
    public List<Leave> getLeavesType(@RequestParam int page,
                                     @RequestParam(value = "page_size") int pageSize,
                                     @RequestParam(value = "type",required = false , defaultValue = "") String type,
                                     @RequestParam(value = "description",required = false , defaultValue = "") String description){
        return leaveService.getLeavesType(page, pageSize, type, description)
                .stream()
                .map(leaveMapper::toRestLeave)
                .toList();
    }


    @PostMapping("/leave-type")
    public Leave addLeaveType(@Valid @RequestBody Leave newleave) throws Exception {
        return leaveMapper.toRestLeave(leaveService.addLeaveType(newleave)) ;
    }

    @GetMapping("/leave-type/{id}")
    public Leave getLeaveTypeById(@PathVariable Long id) throws Exception {
        return leaveService.getLeaveTypeById(id);
    }

    @DeleteMapping("/leave-type/{id}")
    public Leave deleteLeaveTypeById(@PathVariable Long id) throws Exception {
        return leaveService.deleteLeaveTypeById(id);
    }

    @PutMapping("/leave-type/{id}")
    public Leave modifyLeaveTypeById(@PathVariable Long id,@Valid @RequestBody Leave newLeave) throws Exception {
        return leaveService.modifyLeaveById(id, newLeave);
    }

    /*
     */
}
