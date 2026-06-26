package com.ravi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.entity.Appointments;
import com.ravi.entity.Payment;
import com.ravi.repo.AppointmentsRepo;
import com.ravi.repo.PaymentRepository;
import com.ravi.service.MailService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
public class PaymentController {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @PostMapping("/create-order/{amt}")
    public String createOrder(@PathVariable int amt)  {
    	try {
    		RazorpayClient client = new RazorpayClient(keyId, keySecret);
	        JSONObject orderRequest = new JSONObject();
	        orderRequest.put("amount", amt*100); // ₹500
	        orderRequest.put("currency", "INR");

	        Order order = client.orders.create(orderRequest);
	        
	        return order.toString();
		} catch (RazorpayException e) {
			e.printStackTrace();
			return e.toString();
		}
    }
    
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    MailService mailService;
    @Autowired
    AppointmentsRepo appointmentsRepo;

    @PostMapping("/save-payment/{name}/{docBookingDate}/{docBookingTime}")
    public String savePayment(@RequestBody Payment payment,@PathVariable String name,@PathVariable LocalDate docBookingDate,@PathVariable String docBookingTime) {
    	
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        Appointments appointment=new Appointments();
        appointment.setUserEmail(payment.getUserEmail());
        appointment.setDoctorEmail(payment.getDoctorEmail());
		appointment.setBookingDateTime(LocalDateTime.now());
		appointment.setName(name);
		appointment.setDocBookingDate(docBookingDate);
		appointment.setDocBookingTime(docBookingTime);
        appointment.setStatus("Pending");
        appointmentsRepo.save(appointment);
        String m="<h1>Booking Successfully!</h1> <hr/> <p style='background-color:yellow;'>Date: "+appointment.getDocBookingDate()+" Time: "+appointment.getBookingDateTime()+"</p>";
		mailService.htmlMail(appointment.getUserEmail(), "Doctors- Appointment Booked!", m);
		mailService.htmlMail(appointment.getDoctorEmail(), "Doctors- Appointment Booked!", m);
        return "Done";
    }
}