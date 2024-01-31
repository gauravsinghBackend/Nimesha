package com.example.Nimesa.NimeshaAssignment.Response;

import com.example.Nimesa.NimeshaAssignment.Dto.S3BucketResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class S3BucketResponse extends BaseResponse{
    private S3BucketResponseDto data;
}
