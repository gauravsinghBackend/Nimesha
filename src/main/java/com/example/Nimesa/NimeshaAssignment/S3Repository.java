package com.example.Nimesa.NimeshaAssignment;

import com.example.Nimesa.NimeshaAssignment.Model.S3Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3Repository extends JpaRepository<S3Bucket,Long> {
}
