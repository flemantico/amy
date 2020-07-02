package com.amy.security.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//Verifica si existe un token, sino devuelve un 401

@Component
public class SjwEntryPoint implements AuthenticationEntryPoint{
	
	//Para identificar la clase que genera error
	private final static Logger logger = LoggerFactory.getLogger(SjwEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		logger.error("Error en el metodo commence: " + authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
	}
}
