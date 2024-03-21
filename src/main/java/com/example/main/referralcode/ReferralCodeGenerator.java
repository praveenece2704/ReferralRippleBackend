package com.example.main.referralcode;

import java.security.SecureRandom;
import java.math.BigInteger;

public class ReferralCodeGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateReferralCode(int length) {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder referralCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(alphanumeric.length());
            referralCode.append(alphanumeric.charAt(randomIndex));
        }

        return referralCode.toString();
    }
}

