package com.example.Nimesa.NimeshaAssignment;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.s3.AmazonS3;
import com.example.Nimesa.NimeshaAssignment.Response.DiscoveryResultResponse;
import com.example.Nimesa.NimeshaAssignment.Response.InstanceBucketResponse;
import com.example.Nimesa.NimeshaAssignment.Response.JobStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class DiscoverController {

    @Autowired
    private DiscoverServices discoverServices;
    @Autowired
    private AmazonEC2 client;
    @Autowired
    private AmazonS3 amazonS3;
    //Overall Bucket And Instances feching from AWS
    @PostMapping("/discover")
    public ResponseEntity<InstanceBucketResponse> discoverServices(@RequestBody List<String> services) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(discoverServices.discoverInstanceAndS3(services));
    }
    //Just for Testing
    @GetMapping("/instances")
    public void discoverEC2Instances(){
        discoverServices.discoverEC2Instances(client);
    }
    //Just for Testing
    @GetMapping("/bucket")
    public void discoverBucket(){
        discoverServices.discoverS3Buckets(amazonS3);
    }
    @GetMapping("/getJobResult")
    private ResponseEntity<JobStatusResponse> getJobStatus(@RequestParam("jobId") String jobId)
    {
            JobStatusResponse jobStatusResponse=discoverServices.getJobStatus(jobId);
            return ResponseEntity.status(HttpStatus.OK).body(jobStatusResponse);
    }
    @GetMapping("/getDiscoveryResult")
    private ResponseEntity<DiscoveryResultResponse> getDiscoveryResult(@RequestParam("services") String service)
    {
        return ResponseEntity.status(HttpStatus.OK).body(discoverServices.getDiscovertResult(service));
    }
}
