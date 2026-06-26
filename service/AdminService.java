package com.ravi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.entity.Admin;
import com.ravi.entity.Doctor;
import com.ravi.repo.AdminRepo;
import com.ravi.repo.DoctorRepo;


@Service
public class AdminService {
	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	AdminRepo adminRepo;
	
	public boolean updateDoctorStatus(String email, String status) {
		Doctor doctor = doctorRepo.findById(email).orElse(null);
	    if (doctor == null) {
	        return false;
	    }
	    doctor.setStatus(status);
	    doctorRepo.save(doctor);
	    return true;
	}
	
	public Admin getAdmin(String email) {
		return adminRepo.findById(email).orElse(null);
	}
}
