package com.ravi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.DoctorAvail;



@Repository
public interface DoctorAvailRepo extends JpaRepository<DoctorAvail, Integer> {

}
