package com.hackathon.jobs.controller.mapper;

import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {
    public Leave toRestLeave(Leave leave){
        Leave restLeave = new Leave();
        restLeave.setId(leave.getId());
        restLeave.setType(leave.getType());
        restLeave.setDescription(leave.getDescription());
        restLeave.setMaxDuration(leave.getMaxDuration());
        return restLeave;
    }
}
