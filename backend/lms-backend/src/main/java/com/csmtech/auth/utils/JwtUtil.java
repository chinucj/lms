package com.csmtech.auth.utils;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

//	private String secret = "lms";
//
//	@Value("${token.expiration.minutes}")
//	private int tokenExpirationMinutes;
//
//	public String extractUsername(String token) {
//
//		return extractClaim(token, Claims::getSubject);
//
//	}
//
//	public Date extractExpiration(String token) {
//
//		return extractClaim(token, Claims::getExpiration);
//
//	}
//
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//
//		final Claims claims = extractAllClaims(token);
//
//		return claimsResolver.apply(claims);
//
//	}
//
//	private Claims extractAllClaims(String token) {
//
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//
//	}
//
//	public Boolean isTokenExpired(String token) {
//
//		return extractExpiration(token).before(new Date());
//
//	}
//
//	public String generateToken(String username) {
//
//		Map<String, Object> claims = new HashMap<>();
//
//		return createToken(claims, username);
//
//	}
//
//	private String createToken(Map<String, Object> claims, String subject) {
//
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * tokenExpirationMinutes))
//
//				.signWith(SignatureAlgorithm.HS256, secret).compact();
//
//	}
//
//	public Boolean validateToken(String token, UserDetails userDetails) {
//
//		final String username = extractUsername(token);
//
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//
//	}
//
//	public String createMobileToken() {
//
//		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).
//
//				setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * tokenExpirationMinutes)).
//
//				signWith(SignatureAlgorithm.HS256, secret).compact();
//
//	}
	
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)).signWith(getSigninKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	private Key getSigninKey() {
		byte[] key = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
		return Keys.hmacShaKeyFor(key);
	}
	
	private <T> T extractClaim(String token , Function<Claims, T> claimResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimResolvers.apply(claims);
	}



	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
	}
	
	
	public boolean isTokenValid(String token , UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	
	public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 604800000)).signWith(getSigninKey(), SignatureAlgorithm.HS256)
				.compact();
	}

}