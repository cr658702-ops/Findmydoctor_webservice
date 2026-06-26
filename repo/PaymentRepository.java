package com.ravi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}