package com.ravi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.entity.User;
import com.ravi.jwt.JwtUtil;
import com.ravi.repo.UserRepo;


@Service
public class UserService {
	@Autowired
	private UserRepo userRepository;

	public boolean register(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
	        return false;
	    }
	    userRepository.save(user);
	    return true;
	}
	public User getUser(String email) {
		return userRepository.findById(email).orElse(null);
	}

    @Autowired
    MailService mailService;
	@Autowired
	private JwtUtil jwtUtil;
	public boolean forgetPassword(String email) {
		if(userRepository.findById(email).orElse(null)==null) {
			return false;
		}
		String token=jwtUtil.generateToken(email);
		String link = "http://localhost:9090/resetPassword?token=" + token;
		String message="Click the link to verify JWT: "+link;
		String subject="Doctors -Reset Password!";
		mailService.htmlMail(email, subject, message);
		return true;
	}
	
	public User updateUser(User user) {
		User existingUser = userRepository.findById(user.getEmail()).orElse(null);
	    if (existingUser != null) {
	        existingUser.setPhone(user.getPhone());
	        existingUser.setName(user.getName());
	        existingUser.setDob(user.getDob());
	        existingUser.setGender(user.getGender());
	        userRepository.save(existingUser);
	        return existingUser;
	    }
	    return null;
	}
	
	public boolean updatePhoto(String email, byte[] photo) {
		User user = userRepository.findById(email).orElse(null);
	    if (user == null) {
	        return false;
	    }
	    user.setPhoto(photo); 
        userRepository.save(user);
	    return true;
	}

	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		User user = userRepository.findById(email).orElse(null);
	    if (user == null) {
	        return false;
	    }
	    if (!oldPassword.equals(user.getPassword())) {
	        return false;
	    }
	    user.setPassword(newPassword);
	    userRepository.save(user);
	    return true; 
	}

	public boolean updatePassword(String email, String newPassword) {
		User user = userRepository.findById(email).orElse(null);
	    if (user == null) {
	        return false;
	    }
	    user.setPassword(newPassword);
	    userRepository.save(user);
	    return true; 
	}

	public byte[] getPhoto(String email) {
		User user = userRepository.findById(email).orElse(null);
		return user.getPhoto();
	}
	public boolean verifyJwt(String token) {
		String result=jwtUtil.validateToken(token);
		if(result==null) {
			return false;
		}else {
			return true;
		}
		
	}
}
