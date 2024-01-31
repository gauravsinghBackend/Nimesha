package com.example.Nimesa.NimeshaAssignment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class EC2Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String instanceId;
    private String instanceType;
    private String privateIpAddress;
    @CreationTimestamp
    private LocalDateTime ec2updatedTime= LocalDateTime.now();


}
//    String instanceId
//    String instanceType
//    String privateIpAddress
