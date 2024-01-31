package com.example.Nimesa.NimeshaAssignment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EC2InstancesDto {
    private Long id;
    private String instanceId;
    private String instanceType;
    private String privateIpAddress;
}
