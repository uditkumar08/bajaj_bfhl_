package com.chitkara.bfhl.service;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.user-id}")
    private String userId;

    @Value("${bfhl.email}")
    private String email;

    @Value("${bfhl.roll-number}")
    private String rollNumber;

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        StringBuilder alphabetBuilder = new StringBuilder();

        if (request != null && request.getData() != null) {
            for (String item : request.getData()) {
                if (item == null) {
                    continue;
                }

                if (item.matches("^-?\\d+$")) {
                    BigInteger num = new BigInteger(item);
                    if (num.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                    sum = sum.add(num);
                } 
                else if (item.matches("^[a-zA-Z]+$")) {
                    alphabets.add(item.toUpperCase());
                } 
                else if (!item.isEmpty()) {
                    specialCharacters.add(item);
                }

                for (int i = 0; i < item.length(); i++) {
                    char c = item.charAt(i);
                    if (Character.isLetter(c)) {
                        alphabetBuilder.append(c);
                    }
                }
            }
        }

        String reversed = alphabetBuilder.reverse().toString();

        StringBuilder alternated = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                alternated.append(Character.toUpperCase(c));
            } else {
                alternated.append(Character.toLowerCase(c));
            }
        }

        return new BfhlResponse(
                true,
                userId,
                email,
                rollNumber,
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                sum.toString(),
                alternated.toString()
        );
    }
}
