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
		if (checkUser == null) {
			User userdata = new User();
			userdata.setEmail(user.getEmail());
			userdata.setId(user.getId());
			userdata.setFullName(user.getFullName());
			userdata.setUserName(user.getUserName());
			userdata.setPassword(user.getPassword());
			userdata.setReferralCode(generateUniqueReferralCode(8));
			userdata.setReferredBy(user.getReferredBy());
			System.out.println(user.getReferredBy());
			if (user.getReferredBy() != null) {
				User referredBy = userRepository.findByReferralCode(user.getReferredBy());
				int rewardpoints = userRepository.getRewardPoints(referredBy.getEmail());
				referredBy.setRewardPoints(rewardpoints + 100);
			}
			try {
				return userRepository.save(userdata);
			} catch (Exception e) {
				System.out.println("Exception occur while saving data in db" + e);
			}
		}
		return null;
	}

	public User checkEmail(String email) {
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
			System.out.println("Exception while fetcing email" + e);
		}
		return null;
	}

	public String getReferralCode(String email) {
		try {
			User data = userRepository.findByEmail(email);
			return data.getReferralCode();

		} catch (Exception e) {
			System.out.println("Exception while fetcing email" + e);
		}
		return null;
	}

	private String generateUniqueReferralCode(int length) {
		String code;
		do {
			code = ReferralCodeGenerator.generateReferralCode(length);
		} while (userRepository.findByReferralCode(code) != null);
		return code;
	}

	public int getReferralCount(String referralCode) {
		try {
			List<User> user = userRepository.findByReferredBy(referralCode);
			return user.size();

		} catch (Exception e) {
			System.out.println("Exception while fetcing referrals" + e);
		}
		return 0;
	}

	public int getRewards(String email) {
		try {
			return userRepository.getRewardPoints(email);

		} catch (Exception e) {
			System.out.println("Exception while fetcing rewards" + e);
		}
		return 0;

	}

	public List<User> findUser() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			System.out.println("Exception while fetcing users" + e);
		}
		return null;
	}

}
