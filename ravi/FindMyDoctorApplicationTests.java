package com.incapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ravi.service.DoctorService;

@SpringBootTest
class FindMyDoctorApplicationTests {

	@Autowired
	private DoctorService doctorService;
	@Test
	void myTest() {
		assertNotNull(doctorService.getDoctor("abc@gmail.com"));
	}

}
