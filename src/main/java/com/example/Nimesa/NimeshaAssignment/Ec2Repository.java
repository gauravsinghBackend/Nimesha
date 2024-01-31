package com.example.Nimesa.NimeshaAssignment;

import com.example.Nimesa.NimeshaAssignment.Model.EC2Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ec2Repository extends JpaRepository<EC2Instance,Long> {
}
