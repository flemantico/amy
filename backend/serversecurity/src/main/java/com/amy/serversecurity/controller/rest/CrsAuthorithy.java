package com.amy.serversecurity.controller.rest;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
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

import com.amy.serversecurity.dto.DtoJwt;
import com.amy.serversecurity.dto.DtoLoguinUser;
import com.amy.serversecurity.dto.DtoMessaje;
import com.amy.serversecurity.dto.DtoRegisterUser;
import com.amy.serversecurity.model.MdlRole;
import com.amy.serversecurity.model.MdlUser;
import com.amy.serversecurity.security.jwt.SjwProvider;
import com.amy.serversecurity.service.interfaz.SntRole;
import com.amy.serversecurity.service.interfaz.SntUser;
import com.amy.serversecurity.util.enumerators.UnmRoleName;

@RestController
@RequestMapping("/oauth/v1")
@CrossOrigin(origins ={"*"})
//@CrossOrigin(origins = "http://192.168.100.14:4200") Es el servidor del fornt que accedá a la API
//@CrossOrigin(origins = "localhost:4200")
public class CrsAuthorithy {
	private final static Logger logger = LoggerFactory.getLogger(SjwProvider.class);

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private SntUser sntUser;
	@Autowired
	private SntRole sntRole;
	@Autowired
	SjwProvider swtProvider;
	
	//http://localhost:8080/auth/v1/new[JSON]
	/*
	 * 
	{
		"name": "guest",
		"email": "guest@guest.com",
		"userName": "guest",
		"password": "guest",
	    "roles": ["guest"]
	}

	{
		"name": "user",
		"email": "user@user.com",
		"userName": "user",
		"password": "user",
	    "roles": ["user"]
	}
		
	{
		"name": "admin",
		"email": "admin@admin.com",
	    "userName": "admin",
	    "password": "admin",
	    "roles": ["admin"]
	}
	 * */
	@PostMapping("/create")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody DtoRegisterUser dtoRegisterUser, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new DtoMessaje("Campos mal puestos o email inválido."), HttpStatus.BAD_REQUEST);
		if(sntUser.existsByUserName(dtoRegisterUser.getUserName()))
			return new ResponseEntity<>(new DtoMessaje("El nombre ya existe."), HttpStatus.BAD_REQUEST);
		if(sntUser.existsByEmail(dtoRegisterUser.getEmail()))
			return new ResponseEntity<>(new DtoMessaje("El email ya existe."), HttpStatus.BAD_REQUEST);	
		
		MdlUser smdUser = new MdlUser(dtoRegisterUser.getName(), dtoRegisterUser.getEmail(), dtoRegisterUser.getUserName(), passwordEncoder.encode(dtoRegisterUser.getPassword()));
		
		Set<MdlRole> roles = new HashSet<>();
		
		roles.add(sntRole.findBySenRoleName(UnmRoleName.ROLE_GUEST).get());

		if(dtoRegisterUser.getRoles().contains("user")){
			roles.add(sntRole.findBySenRoleName(UnmRoleName.ROLE_USER).get());
		}

		if(dtoRegisterUser.getRoles().contains("admin")){
			roles.add(sntRole.findBySenRoleName(UnmRoleName.ROLE_USER).get());
			roles.add(sntRole.findBySenRoleName(UnmRoleName.ROLE_ADMIN).get());
		}

		smdUser.setSmdRoles(roles);
		sntUser.save(smdUser);
		
		return new ResponseEntity<>(new DtoMessaje("Usuario creado."), HttpStatus.CREATED);
	}
	
	//http://localhost:8080/auth/v1/login
	//{"userName": "user", "password": "user"}
	//para obtener el token
	@RequestMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody DtoLoguinUser dtoLoginUser, BindingResult bindingResult){
		logger.warn("login");
	//public ResponseEntity<DtoJwt> login(@Valid @RequestBody DtoLoguinUser dtoLoginUser, BindingResult bindingResult){
		//dtoMessaje dtomessaje;

		if (bindingResult.hasErrors()){
			//return new ResponseEntity(new DtoMessaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(new DtoMessaje("Campos mal puestos."), HttpStatus.BAD_REQUEST);
		}
		
		if(!sntUser.existsByUserName(dtoLoginUser.getUserName()))
			return new ResponseEntity<>(new DtoMessaje("El usuario no existe."), HttpStatus.BAD_REQUEST);

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLoginUser.getUserName(), dtoLoginUser.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = swtProvider.createAccessJwtToken(authentication);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		DtoJwt dtoJwt = new DtoJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<>(dtoJwt, HttpStatus.OK);
		
	}	
}
