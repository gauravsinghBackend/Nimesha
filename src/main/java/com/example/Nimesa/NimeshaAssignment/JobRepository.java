package com.example.Nimesa.NimeshaAssignment;

import com.example.Nimesa.NimeshaAssignment.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    Optional<Job> findByJobId(String jobId);
}
