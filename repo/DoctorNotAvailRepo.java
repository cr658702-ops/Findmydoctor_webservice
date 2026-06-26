package com.ravi.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.DoctorNotAvail;



@Repository
public interface DoctorNotAvailRepo extends JpaRepository<DoctorNotAvail, Integer> {
	boolean existsByDoctorEmailAndDocDate(String doctorEmail, LocalDate docDate);
	List<DoctorNotAvail> findByDoctorEmail(String doctorEmail);
	public List<DoctorNotAvail> findByDoctorEmailAndDocDate(String doctorEmail, LocalDate docDate);
}
