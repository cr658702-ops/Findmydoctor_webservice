package com.ravi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.DoctorDetails;



@Repository
public interface DoctorDetailsRepo extends JpaRepository<DoctorDetails, Integer> {

}
