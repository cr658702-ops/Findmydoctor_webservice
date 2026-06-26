package com.ravi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.DoctorOnline;



@Repository
public interface DoctorOnlineRepo extends JpaRepository<DoctorOnline, String> {

}
