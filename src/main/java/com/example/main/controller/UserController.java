package com.example.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.entity.User;
import com.example.main.entity.UserLoginDTO;
import com.example.main.entity.UserRegistrationDTO;
import com.example.main.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO user) {
    	System.out.println(user);
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    
	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO>  login(@RequestBody UserLoginDTO loginDTO) {
		String email = loginDTO.getEmail();
		String password = loginDTO.getPassword();

		User user = userService.checkEmail(email);

		if (user != null && user.getPassword().equals(password)) {
			return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
		} else {
			return null;
		}
	}
	
	 @GetMapping("/referralCode/{email}")
	    public String getReferralCode(@PathVariable("email") String email) {
		    return userService.getReferralCode(email);
	    }
	 
	 @GetMapping("/referralCount/{code}")
	    public int getCount(@PathVariable("code") String code) {
		    return userService.getReferralCount(code);
	    }
	 
	 @GetMapping("/rewardPoints/{email}")
	    public int getRewardPoints(@PathVariable("email") String email) {
		    return userService.getRewards(email);
	    }
}

