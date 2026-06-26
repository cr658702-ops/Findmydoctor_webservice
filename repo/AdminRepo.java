package com.ravi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.entity.Admin;



@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {
	
}
