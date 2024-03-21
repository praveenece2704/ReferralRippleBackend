package com.example.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entity.User;
import com.example.main.entity.UserRegistrationDTO;
import com.example.main.referralcode.ReferralCodeGenerator;
import com.example.main.repository.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;
    

    public User registerUser(UserRegistrationDTO user) {
    	User checkUser = checkEmail(user.getEmail());
    	if(checkUser==null) {
    		 User userdata=new User();
    		    userdata.setEmail(user.getEmail());
    		    userdata.setId(user.getId());
    		    userdata.setFullName(user.getFullName());
    		    userdata.setUserName(user.getUserName());
    		    userdata.setPassword(user.getPassword());
    	        userdata.setReferralCode(generateUniqueReferralCode(8));
    	        userdata.setReferredBy(user.getReferredBy());
    	        System.out.println(user.getReferredBy());
    	        if(!user.getReferredBy().isEmpty()) {
        			User referredBy = userRepository.findByReferralCode(user.getReferredBy());
        			int rewardpoints=userRepository.getRewardPoints(referredBy.getEmail());
        			referredBy.setRewardPoints(rewardpoints+100);
        		}
          return userRepository.save(userdata);
        
    	}
		return null;
    }

	public User checkEmail(String email) {	
		return userRepository.findByEmail(email);
	}
	
	public String getReferralCode(String email) {	
		User data=userRepository.findByEmail(email);
		return data.getReferralCode();
	}
	
	 private String generateUniqueReferralCode(int length) {
	        String code;
	        do {
	            code = ReferralCodeGenerator.generateReferralCode(length);
	        } while (userRepository.findByReferralCode(code) != null);
	        return code;
	    }

	public int getReferralCount(String referralCode) {
		List<User> user=userRepository.findByReferredBy(referralCode);
		return user.size();
	}

	public int getRewards(String email) {
		return userRepository.getRewardPoints(email);
		
	}

}

