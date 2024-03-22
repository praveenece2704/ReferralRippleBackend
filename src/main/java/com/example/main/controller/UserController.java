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

@CrossOrigin("http://referralappdev.s3-website-ap-southeast-2.amazonaws.com")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO user) {
    	System.out.println(user);
        User registeredUser = userService.registerUser(user);
        if(registeredUser!=null) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        }
        else {
        	return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(registeredUser);
        }
    }
        
	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO>  login(@RequestBody UserLoginDTO loginDTO) {
		String email = loginDTO.getEmail();
		String password = loginDTO.getPassword();

		User user = userService.checkEmail(email);

		if (user != null && user.getPassword().equals(password)) {
			return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	 @GetMapping("/referralCode/{email}")
	    public ResponseEntity<String> getReferralCode(@PathVariable("email") String email) {
		  if(userService.getReferralCode(email)!=null) {
		    return ResponseEntity.status(HttpStatus.OK).body(userService.getReferralCode(email));
		  }
		  else {
			  return ResponseEntity.status(HttpStatus.OK).body(null);
		  }
	    }
	 
	 @GetMapping("/referralCount/{code}")
	    public ResponseEntity<Integer> getCount(@PathVariable("code") String code) {
		    return ResponseEntity.status(HttpStatus.OK).body(userService.getReferralCount(code));
	    }
	 
		@GetMapping("/rewardPoints/{email}")
		public ResponseEntity<Integer> getRewardPoints(@PathVariable("email") String email) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getRewards(email));
		}
}

