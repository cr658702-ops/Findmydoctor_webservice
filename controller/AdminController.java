package com.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.entity.Admin;
import com.ravi.service.AdminService;


@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@PutMapping("/updateDoctorStatus/{email}/{status}")
	public boolean updateUser(@PathVariable String email,@PathVariable String status) {
		return adminService.updateDoctorStatus(email,status);
	}

	@GetMapping("/getAdmin/{email}")
	public Admin getAdmin(@PathVariable String email) {
		return adminService.getAdmin(email);
	}
	
}
