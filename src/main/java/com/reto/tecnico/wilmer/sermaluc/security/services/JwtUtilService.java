package com.reto.tecnico.wilmer.sermaluc.security.services;

import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtilService {
  private static final String JWT_SECRET_KEY = "JSDB-DFNG-JKSX-HNSU-DHWT";

  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * 1;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractAllClaims(token));
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public AuthResponse generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", userDetails.getUsername());

    String jwtToken = createToken(claims, userDetails.getUsername());
    long expirationTime = JWT_TOKEN_VALIDITY / 1000;

    AuthResponse authResponse = new AuthResponse();
    authResponse.setAccess_token(jwtToken);
    authResponse.setToken_type("Bearer");
    authResponse.setExpires_in(expirationTime);
    return authResponse;
  }

  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
            .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
