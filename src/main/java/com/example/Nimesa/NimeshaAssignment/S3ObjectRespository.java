package com.example.Nimesa.NimeshaAssignment;

import com.example.Nimesa.NimeshaAssignment.Model.S3BucketObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface S3ObjectRespository extends JpaRepository<S3BucketObject,Long> {
    Long countByBucketName(String bucketName);

    List<S3BucketObject> findByBucketNameAndFileNameLike(String bucketName, String pattern);
}
