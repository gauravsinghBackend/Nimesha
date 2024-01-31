package com.example.Nimesa.NimeshaAssignment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3BucketDto {
    private Long id;
    private String bucketName;
    private String creationDate;
}
