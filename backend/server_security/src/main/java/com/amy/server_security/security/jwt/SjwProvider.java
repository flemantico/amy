package com.amy.server_security.security.jwt;

import java.util.Date;
//import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.amy.server_security.model.MdlPrincipalUser;

//import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



//Genera un token, con metodos de validacion
@Component
public class SjwProvider {
	private final static Logger logger = LoggerFactory.getLogger(SjwProvider.class);
	
	@Value("${jwt.jti}")
	private String jti;
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;
	@Value("${jwt.issuer_info}")
	private String issuer_info;
	
	public String createAccessJwtToken(Authentication authentication) {
		MdlPrincipalUser smdPrincipalUser = (MdlPrincipalUser) authentication.getPrincipal();

		if (smdPrincipalUser.getAuthorities() == null || smdPrincipalUser.getAuthorities().isEmpty()) 
			throw new IllegalArgumentException("User doesn't have any privileges.");

		//si da error sacarlo del token
		//Claims claims = Jwts.claims().setSubject(smdPrincipalUser.getUsername());
		//claims.put("scopes", smdPrincipalUser.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
		
		return Jwts.builder()
		//.setClaims(claims)
		.setSubject(smdPrincipalUser.getUsername())
		.setId(jti)
		.setIssuer(issuer_info)
		.setIssuedAt(new Date())
		.setExpiration(new Date(new Date().getTime() + expiration  * 1000))
		.signWith(SignatureAlgorithm.HS512, secret)
		.compact();
	}
	
	public String getUserNameFromToken(String token) {
	
		return Jwts.parser()
		.setSigningKey(secret)
		.parseClaimsJws(token)		
		.getBody()
		.getSubject();
	}
	
	public boolean validateToken(String token) {
		logger.warn("validateToken");
		try {
			Jwts.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token);
			logger.warn("validateToken OK");
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado: " + e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("token expirado: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("token vacio");
		} catch (SignatureException e) {
			logger.error("Error en la firma: " + e.getMessage());
		}
		logger.warn("validateToken ERR");
		return false;
	}
}