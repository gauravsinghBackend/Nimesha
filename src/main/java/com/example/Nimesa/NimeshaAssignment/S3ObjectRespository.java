package com.example.Nimesa.NimeshaAssignment;

import com.example.Nimesa.NimeshaAssignment.Model.S3BucketObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface S3ObjectRespository extends JpaRepository<S3BucketObject,Long> {
}
