package com.example.Nimesa.NimeshaAssignment.Dto;

import com.example.Nimesa.NimeshaAssignment.Model.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDto{
    private String jobId;
    private JobStatus jobStatus;
}
