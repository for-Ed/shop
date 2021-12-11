package com.shop.shop.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Signature;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private final String secretWord = "word";//not good to keep it here

    public String generateToken(String email){

        Date date = Date.from(LocalDate.now().plusDays(12).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512,secretWord)
                .compact();
    }
    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token).getBody();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
           //throw e;
        }
    }
    public String getEmailFromToken(String token){
        Claims body = Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token).getBody();
        return body.getSubject();
    }
}
