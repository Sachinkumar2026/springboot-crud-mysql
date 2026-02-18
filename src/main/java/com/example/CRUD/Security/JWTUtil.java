package com.example.CRUD.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET = "mysecretkeymysecretkeymysecretkey123";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final SecretKey KEYs = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String email,String role){

        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(KEY)
                .compact();
    }
    public static boolean isTokenValid(String token){
        try{
            Jwts.parser()
                    .verifyWith(KEYs)
                    .build()
                    .parseSignedClaims(token);

            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(KEYs)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();  // "sub" field
    }
    public static String extractRole(String token) {

        return Jwts.parser()
                .verifyWith(KEYs)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }


}
