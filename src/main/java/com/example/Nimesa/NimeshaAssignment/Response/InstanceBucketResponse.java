package com.example.Nimesa.NimeshaAssignment.Response;

import com.example.Nimesa.NimeshaAssignment.Dto.InstanceBucketDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InstanceBucketResponse extends BaseResponse{
    private InstanceBucketDto data;
}
