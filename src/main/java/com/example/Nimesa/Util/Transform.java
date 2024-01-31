package com.example.Nimesa.Util;

import com.example.Nimesa.NimeshaAssignment.Dto.EC2InstancesDto;
import com.example.Nimesa.NimeshaAssignment.Dto.PatternMatchResponseDto;
import com.example.Nimesa.NimeshaAssignment.Model.EC2Instance;
import com.example.Nimesa.NimeshaAssignment.Model.S3BucketObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Transform {
    @Autowired
    private ModelMapper modelMapper;

    public List<EC2InstancesDto> convertEntityToDto(List<EC2Instance> ec2Instances) {
        List<EC2InstancesDto>ec2InstancesDtos=new ArrayList<>();
        for (EC2Instance ec2Instance: ec2Instances)
        {
            EC2InstancesDto ec2InstancesDto= modelMapper.map(ec2Instance,EC2InstancesDto.class);
            ec2InstancesDtos.add(ec2InstancesDto);
        }
        return ec2InstancesDtos;
    }

    public List<PatternMatchResponseDto> convertPatternEntityToDto(List<S3BucketObject> s3BucketObjects) {
        List<PatternMatchResponseDto> patternMatchResponseDtos=new ArrayList<>();
        for (S3BucketObject s3BucketObject: s3BucketObjects)
        {
            PatternMatchResponseDto patternMatchResponseDto=modelMapper.map(s3BucketObject, PatternMatchResponseDto.class);
            patternMatchResponseDtos.add(patternMatchResponseDto);
        }
        return patternMatchResponseDtos;
    }
}
