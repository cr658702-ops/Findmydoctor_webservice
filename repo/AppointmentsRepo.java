package com.ravi.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.Appointments;


@Repository
public interface AppointmentsRepo extends JpaRepository<Appointments,Integer> {
	public List<Appointments> getByUserEmail(String userEmail);
	public List<Appointments> getByDoctorEmail(String doctorEmail);
	public List<Appointments> findByDocBookingTimeAndDocBookingDateAndUserEmailAndStatus(
            String docBookingTime,
            LocalDate  docBookingDate,
            String userEmail,
            String status
    );
	public List<Appointments> findByDocBookingTimeAndDocBookingDateAndDoctorEmail(
            String docBookingTime,
            LocalDate docBookingDate,
            String doctorEmail
    );
}
