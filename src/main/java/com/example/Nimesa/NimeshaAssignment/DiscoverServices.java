package com.example.Nimesa.NimeshaAssignment;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.s3.AmazonS3;
import com.example.Nimesa.NimeshaAssignment.Response.DiscoveryResultResponse;
import com.example.Nimesa.NimeshaAssignment.Response.InstanceBucketResponse;
import com.example.Nimesa.NimeshaAssignment.Response.JobStatusResponse;
import com.example.Nimesa.NimeshaAssignment.Response.S3BucketResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DiscoverServices {
    public InstanceBucketResponse discoverInstanceAndS3(List<String> services);

    public void discoverEC2Instances(AmazonEC2 client);

    void discoverS3Buckets(AmazonS3 client);

    JobStatusResponse getJobStatus(String jobId);

    DiscoveryResultResponse getDiscovertResult(String service);

    S3BucketResponse getS3BucketObject(String bucketName);
}
