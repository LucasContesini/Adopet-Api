package com.example.adopet.config;

import com.example.adopet.model.auth.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JWTService {

    private final String SECRET = "adopet";

    public String generateToken(Authentication authentication) {
        Date today = new Date();
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("Adopet")
                .setSubject(String.valueOf(principal.getId()))
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + 864000000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }

    public int getUserId(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return Integer.parseInt(body.getSubject());
    }
}
