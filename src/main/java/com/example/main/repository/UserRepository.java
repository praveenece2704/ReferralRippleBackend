package com.example.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.main.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
    User findByReferralCode(String referralCode);
    List<User> findByReferredBy(String referralCode); //return referred by me
    @Query("select u.rewardPoints from User u where email=?1")
    int getRewardPoints(String email);
    
}
