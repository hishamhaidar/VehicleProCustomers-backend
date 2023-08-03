package com.hhaidar.VehicleProCustomersbackend.config;
import com.hhaidar.VehicleProCustomersbackend.model.GarageCustomers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {
    private final String SECRET_KEY ="7a4a63e490bc5e65da4eef781c880e4019776cf75897e159e5096b9b37f4fc21";
    public String generateToken(GarageCustomers customer){

       return Jwts.builder()
                .setSubject(customer.getCustomerEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*48))
                .signWith(getSiningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String jwtToken,UserDetails userDetails){
        final String email = extractEmail(jwtToken);
        return (email.equals(userDetails.getUsername())&& !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken,Claims::getExpiration);
    }

    public String extractEmail(String jwtToken){
        return extractClaim(jwtToken,Claims::getSubject);
    }
    public <T> T extractClaim(String jwtToken , Function<Claims,T> claimsResolver){
        final Claims claims = extractALlClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractALlClaims(String jwtToken){
        return Jwts.parserBuilder()
                .setSigningKey(getSiningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSiningKey() {
        byte[] keyBytes= Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
