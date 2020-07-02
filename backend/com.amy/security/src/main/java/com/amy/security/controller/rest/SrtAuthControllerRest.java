package com.amy.security.controller.rest;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amy.security.dto.dtoJwt;
import com.amy.security.dto.dtoLoguinUser;
import com.amy.security.dto.dtoMessaje;
import com.amy.security.dto.dtoRegisterUser;
import com.amy.security.model.SmdRole;
import com.amy.security.model.SmdUser;
import com.amy.security.security.jwt.JwtProvider;
import com.amy.security.service.interfaz.SsiRoleService;
import com.amy.security.service.interfaz.SsiUserService;
import com.amy.security.util.enumerators.SenRoleName;

@RestController
@RequestMapping("/auth/v1")
@CrossOrigin(origins ={"*"})
//@CrossOrigin(origins = "http://192.168.100.14:4200")
//@CrossOrigin(origins = "localhost:4200")
public class SrtAuthControllerRest {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private SsiUserService ssiuser;
	
	@Autowired
	private SsiRoleService ssirole;
	
	@Autowired
	JwtProvider jwtProvider;
	
	//http://localhost:8080/auth/v1/new[JSON]
	/*
	 * {
		"email": "user@user.com",
	    "userName": "user",
	    "password": "user"
		}
		
		{
		"email": "admin@admin.com",
	    "userName": "admin",
	    "password": "admin",
	    "roles": ["admin"]
		}
	 * */
	@PostMapping("/register")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody dtoRegisterUser registerUser, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new dtoMessaje("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
		if(ssiuser.existsByUserName(registerUser.getUserName()))
			return new ResponseEntity<>(new dtoMessaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);
		if(ssiuser.existsByEmail(registerUser.getEmail()))
			return new ResponseEntity<>(new dtoMessaje("el email ya existe"), HttpStatus.BAD_REQUEST);	
		
		SmdUser usuario = new SmdUser(registerUser.getName(), registerUser.getEmail(), registerUser.getUserName(), passwordEncoder.encode(registerUser.getPassword()));
		
		Set<SmdRole> roles = new HashSet<>();
		
		roles.add(ssirole.findBySenRoleName(SenRoleName.ROLE_USER).get());
		
		if(registerUser.getRoles().contains("admin"))
			roles.add(ssirole.findBySenRoleName(SenRoleName.ROLE_ADMIN).get());

		usuario.setSmdRoles(roles);
		ssiuser.save(usuario);
		
		return new ResponseEntity<>(new dtoMessaje("usuario guardado"), HttpStatus.CREATED);
	}
	
	//http://localhost:8080/auth/v1/login
	//{"userName": "user", "password": "user"}
	//para obtener el token
	@RequestMapping("/login")
	public ResponseEntity<dtoJwt> login(@Valid @RequestBody dtoLoguinUser dtoLoginUser, BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return new ResponseEntity(new dtoMessaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		
		Authentication authentication = 
		authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(dtoLoginUser
				.getUserName(), dtoLoginUser.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		dtoJwt jwtDto = new dtoJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
		
	}	
}
