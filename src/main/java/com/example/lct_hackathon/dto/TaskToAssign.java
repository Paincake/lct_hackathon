package com.example.lct_hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TaskToAssign {
    private Long taskId;
    private int priorityLevel;
    private int longitude;
    private int latitude;
}
