package com.demo.springgraphql2;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;

public class JavaTests {

    @Test
    public void test() {
        byte[] key = new byte[256];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);

        // Encode the key as a string using Base64
        String encodedKey = Base64.getEncoder().encodeToString(key);

        // Print the encoded key
        System.out.println("jwt.secret=" + encodedKey);
    }

}
