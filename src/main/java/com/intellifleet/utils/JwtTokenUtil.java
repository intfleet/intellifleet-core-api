package com.intellifleet.utils;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.intellifleet.bean.UserContext;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 6765535991341208486L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration.hour}")
	private long expiration;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(UserContext userContext) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", userContext.getUsername());
		claims.put("roles", userContext.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

		claims.put("user", userContext.getUserDetailsBean());

		JwtBuilder jwtBuilder = Jwts.builder()
				.setSubject(userContext.getUsername())
				.setHeader(Map.of("username", userContext.getUsername()))
				.setClaims(claims);
		long oneHr = 1000 * 60 * 60;	// One Hour expire time
		long expTimeLimit = oneHr * expiration; // expire total hour
		return jwtBuilder
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expTimeLimit))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token) {
		Jws<Claims> claims = Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token);

		return (String)claims.getHeader().get("username");
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	//=======================================================================================


}

