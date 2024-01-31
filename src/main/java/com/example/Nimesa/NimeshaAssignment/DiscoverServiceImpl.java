package com.example.Nimesa.NimeshaAssignment;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.example.Nimesa.NimeshaAssignment.Dto.InstanceBucketDto;
import com.example.Nimesa.NimeshaAssignment.Model.EC2Instance;
import com.example.Nimesa.NimeshaAssignment.Model.Job;
import com.example.Nimesa.NimeshaAssignment.Model.JobStatus;
import com.example.Nimesa.NimeshaAssignment.Model.S3Bucket;
import com.example.Nimesa.NimeshaAssignment.Dto.JobResponseDto;
import com.example.Nimesa.NimeshaAssignment.Response.DiscoveryResultResponse;
import com.example.Nimesa.NimeshaAssignment.Response.InstanceBucketResponse;
import com.example.Nimesa.NimeshaAssignment.Response.JobStatusResponse;
import com.example.Nimesa.Util.Status;
import com.example.Nimesa.Util.StringAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscoverServiceImpl implements DiscoverServices{
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private AmazonEC2 amazonEC2;
    @Autowired
    private JobRepository jobRepository;
    //Not required
    @Autowired
    private Ec2Repository ec2Repository;
    @Autowired
    private S3Repository s3Repository;
    @Override
    public InstanceBucketResponse discoverInstanceAndS3(List<String> services) {
        String jobId = UUID.randomUUID().toString();
        Job job =Job.builder()
                .jobId(jobId)
                .jobStatus(JobStatus.IN_PROGRESS)
                .build();
        Job savedJob = jobRepository.save(job);
        //It will start the excution of thread simulatneously
        CompletableFuture<Void> ec2Task = CompletableFuture.runAsync(() -> discoverEC2Instances(amazonEC2));
        CompletableFuture<Void> s3Task = CompletableFuture.runAsync(() -> discoverS3Buckets(amazonS3));
        // Wait for both tasks to complete
        CompletableFuture.allOf(ec2Task, s3Task).join();
        if (ec2Task.isCompletedExceptionally() || s3Task.isCompletedExceptionally()) {
            savedJob.setJobStatus(JobStatus.FAILED);
            jobRepository.save(savedJob);
        } else {
            savedJob.setJobStatus(JobStatus.SUCCESS);
            jobRepository.save(savedJob);
        }
        InstanceBucketDto instanceBucketDto=new InstanceBucketDto();
        instanceBucketDto.setJobId(jobId);
        return InstanceBucketResponse.builder()
                .status(com.example.Nimesa.Util.Status.SUCCESS)
                .message(StringAll.INSTANCES_AND_BUCKET_RETRIEVED)
                .data(instanceBucketDto)
                .build();
    }

    @Async("ec2Executor")
    public void discoverEC2Instances(AmazonEC2 client) {
        List<EC2Instance> results = new ArrayList<>();
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        boolean done = false;
        try {

                DescribeInstancesResult response = client.describeInstances(request);
            // Describe EC2 instances in the Mumbai region
//            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult describeInstancesResult = client.describeInstances(request);
            List<Instance> instances = describeInstancesResult.getReservations().stream()
                    .flatMap(reservation -> reservation.getInstances().stream())
                    .collect(Collectors.toList());

            // Extract relevant information from EC2 instances and map to EC2Instance objects
            for (Instance instance : instances) {
                String instanceId = instance.getInstanceId();
                String instanceType = instance.getInstanceType();
                String privateIpAddress = instance.getPrivateIpAddress();

                // **Fix potential NullPointerException:** Check for null values before accessing privateIpAddress
                if (privateIpAddress != null) {
                    EC2Instance result = EC2Instance.builder()
                            .instanceId(instanceId)
                            .instanceType(instanceType)
                            .privateIpAddress(privateIpAddress)
                            .build();
                    results.add(result);
                }
            }
        } catch (AmazonEC2Exception e) {

        } catch (Exception e) {

        }
        ec2Repository.saveAll(results);
    }


    @Async("s3Executor")
    public void discoverS3Buckets(AmazonS3 client) {
        List<S3Bucket> results = new ArrayList<>();

        try {
            // Retrieve the list of S3 buckets
            List<Bucket> buckets = client.listBuckets();
            // Iterate through the buckets and extract relevant information
            for (Bucket bucket : buckets) {
                String bucketName = bucket.getName();
                String creationDate = bucket.getCreationDate().toString(); // Convert creation date to string

                // Construct a ServiceDiscoveryResult object
                S3Bucket result = S3Bucket.builder()
                        .bucketName(bucketName)
                        .creationDate(creationDate)
                        .build();
                results.add(result);
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
        List<S3Bucket> s3Bucket= s3Repository.saveAll(results);
    }

    @Override
    public JobStatusResponse getJobStatus(String jobId) {
        Optional<Job> optionalJob = jobRepository.findByJobId(jobId);
        JobResponseDto jobResponseDto=new JobResponseDto();
        jobResponseDto.setJobStatus(optionalJob.get().getJobStatus());
        jobResponseDto.setJobId(optionalJob.get().getJobId());
        return JobStatusResponse.builder()
                .data(jobResponseDto)
                .status(Status.SUCCESS)
                .message(StringAll.JOB_STATUS)
                .build();
    }

    @Override
    public DiscoveryResultResponse getDiscovertResult(String service) {
        if (service.equals("EC2"))
        {
            List<EC2Instance> ec2Instances = ec2Repository.findAll();
        }
        else if (service.equals("S3"))
        {
            List<S3Bucket> s3Buckets=s3Repository.findAll();
        }
        return null;
    }
}
