package com.ravi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.Doctor;



@Repository
public interface DoctorRepo extends JpaRepository<Doctor, String> {


	List<Doctor> findBySpeciality(String speciality);
	List<Doctor> findByStateAndCityAndSpecialityAndStatus(String state,String city,String speciality,String status);
	boolean existsByEmail(String doctorEmail);
//	boolean existsByEmailAndDocDate(String doctorEmail, java.util.Date docDate);

}
