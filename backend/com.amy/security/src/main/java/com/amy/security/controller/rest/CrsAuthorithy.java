package com.amy.security.controller.rest;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.StreamingHttpOutputMessage.Body;
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

import com.amy.security.dto.dtoJwt;
import com.amy.security.dto.dtoLoguinUser;
import com.amy.security.dto.dtoMessaje;
import com.amy.security.dto.dtoRegisterUser;
import com.amy.security.model.MdlRole;
import com.amy.security.model.MdlUser;
import com.amy.security.security.jwt.SjwProvider;
import com.amy.security.service.interfaz.SntRole;
import com.amy.security.service.interfaz.SntUser;
import com.amy.security.util.enumerators.SenRoleName;

@RestController
@RequestMapping("/auth/v1")
@CrossOrigin(origins ={"*"})
//@CrossOrigin(origins = "http://192.168.100.14:4200")
//@CrossOrigin(origins = "localhost:4200")
public class CrsAuthorithy {
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
	public ResponseEntity<?> create(@Valid @RequestBody dtoRegisterUser dtoRegisterUser, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(new dtoMessaje("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
		if(sntUser.existsByUserName(dtoRegisterUser.getUserName()))
			return new ResponseEntity<>(new dtoMessaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);
		if(sntUser.existsByEmail(dtoRegisterUser.getEmail()))
			return new ResponseEntity<>(new dtoMessaje("el email ya existe"), HttpStatus.BAD_REQUEST);	
		
		MdlUser smdUser = new MdlUser(dtoRegisterUser.getName(), dtoRegisterUser.getEmail(), dtoRegisterUser.getUserName(), passwordEncoder.encode(dtoRegisterUser.getPassword()));
		
		Set<MdlRole> roles = new HashSet<>();
		
		roles.add(sntRole.findBySenRoleName(SenRoleName.ROLE_GUEST).get());

		if(dtoRegisterUser.getRoles().contains("user")){
			roles.add(sntRole.findBySenRoleName(SenRoleName.ROLE_USER).get());
		}

		if(dtoRegisterUser.getRoles().contains("admin")){
			roles.add(sntRole.findBySenRoleName(SenRoleName.ROLE_USER).get());
			roles.add(sntRole.findBySenRoleName(SenRoleName.ROLE_ADMIN).get());
		}

		smdUser.setSmdRoles(roles);
		sntUser.save(smdUser);
		
		return new ResponseEntity<>(new dtoMessaje("usuario creado"), HttpStatus.CREATED);
	}
	
	//http://localhost:8080/auth/v1/login
	//{"userName": "user", "password": "user"}
	//para obtener el token
	@RequestMapping("/login")
	public ResponseEntity<dtoJwt> login(@Valid @RequestBody dtoLoguinUser dtoLoginUser, BindingResult bindingResult){
		//dtoMessaje dtomessaje;
		if (bindingResult.hasErrors()){
			return new ResponseEntity(new dtoMessaje("campos mal puestos"), HttpStatus.BAD_REQUEST);

			//dtomessaje = new dtoMessaje("campos mal puestos");
			//return new ResponseEntity(dtomessaje, HttpStatus.BAD_REQUEST);
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLoginUser.getUserName(), dtoLoginUser.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = swtProvider.generateToken(authentication);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		dtoJwt jwtDto = new dtoJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
		
	}	
}
