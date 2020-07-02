package com.amy.security.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.amy.security.model.MdlPrincipalUser;

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

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authentication) {
		MdlPrincipalUser smdPrincipalUser = (MdlPrincipalUser) authentication.getPrincipal();

		return Jwts.builder()
		.setSubject(smdPrincipalUser.getUsername())
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