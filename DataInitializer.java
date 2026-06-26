package com.ravi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ravi.entity.Admin;
import com.ravi.repo.AdminRepo;
import com.ravi.repo.DoctorAvailRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;
    
    @Value("${admin.name}")
    private String adminName;
    
    @Autowired
    AdminRepo adminRepo;


    @Override
    public void run(String... args) throws Exception {
        if (adminRepo.findById(adminEmail).orElse(null) == null) {
            Admin admin = new Admin();
            admin.setName(adminName);
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword);

            adminRepo.save(admin);
        }
    }
}