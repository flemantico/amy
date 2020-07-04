package com.amy.security.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amy.security.service.interfaz.SntUser;


//Se ejecuta por cada peticion y verifica su validez para permitir el acceso al recurso
public class SjwTokenFilter extends OncePerRequestFilter{

	private final static Logger logger = LoggerFactory.getLogger(SjwTokenFilter.class);

	@Autowired
	SjwProvider swtProvider;
	
	@Autowired
	SntUser sntUser;
	
	//Verifica el token y genera la autenticacion
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			logger.warn(token);
			if(token != null && swtProvider.validateToken(token)) {
				String userName = swtProvider.getUserNameFromToken(token);
				
				UserDetails userDetails = sntUser.loadUserByUsername(userName);
				
				UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				//Asignamos el usuario al contexto de autebticacion
				SecurityContextHolder.getContext().setAuthentication(authentication);
				logger.warn("doFilterInternal OK");
			}
		} catch (Exception e) {
			logger.error("Error en el metodo doFilterInternal " + e.getMessage());
		}
		
		filterChain.doFilter(request, response);
		logger.warn("doFilterInternal FIN");
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		logger.warn(header);
		if(header != null && header.startsWith("Bearer")) {
			logger.warn("getToken");
			return header.replace("Bearer ", "");
		}else {
			return null;	
		}
	}
}