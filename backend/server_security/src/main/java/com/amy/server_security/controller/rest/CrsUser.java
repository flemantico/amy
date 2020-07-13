package com.amy.server_security.controller.rest;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amy.server_security.model.MdlUser;
//import com.amy.security.security.jwt.JwtEntryPoint;
import com.amy.server_security.service.interfaz.SntUser;
import com.amy.server_security.util.exception.UxcResourceNotFoundException;

@RestController
@RequestMapping(path = "/oauth/users/v1")
@CrossOrigin(origins ={"*"}) // desde cualquier url
//@RequestMapping(path = "/users/v1",  method = {RequestMethod.GET, RequestMethod.PUT})
//@CrossOrigin(origins ={"*"}, maxAge = 3600)
//@CrossOrigin(origins = "http://192.168.100.14:4200", maxAge = 3600)
//@CrossOrigin({"*"})
//@CrossOrigin(maxAge = 3600)
//@CrossOrigin(origins = "http://amy.com", maxAge = 3600)
//@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class CrsUser {
	private final static Logger logger = LoggerFactory.getLogger(CrsUser.class);
	@Autowired
	private SntUser ssiuser;
	
	//@Autowired
	//private BCryptPasswordEncoder bcpe;
	
	//http://localhost:8080/users/v1/findById/1
    @GetMapping (value = "/getOne/{id}")
    public ResponseEntity<MdlUser> getOne(@PathVariable(value = "id") Integer id) throws UxcResourceNotFoundException {
    	MdlUser mmcUser = ssiuser.findById(id).orElseThrow(() -> new UxcResourceNotFoundException("User not found for this id :: " + id));

    	//mmcUser.setId(mmcUser.getId());
    	//mmcUser.setUserName(mmcuser.getUserName());
    	//mmcUser.setPassword(bcpe.encode(mmcUser.getPassword()));
    	
    	return ResponseEntity.ok(mmcUser);
    }
    
    //http://localhost:8080/users/v1/list
	@GetMapping(value = "/list")
	public List<MdlUser> list() throws UxcResourceNotFoundException{
		logger.warn("list");
		return ssiuser.list();
	}
	
	//http://localhost:8080/users/v1/insert/[JSon]
	@PostMapping (value = "/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MdlUser>  save(@Valid @RequestBody MdlUser mmcuser, BindingResult result) throws UxcResourceNotFoundException{
    	//User mmcUser = ssiuser.findById(mmcuser.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + mmcuser.getId()));

    	//mmcUser.setId(mmcuser.getId());
    	//mmcUser.setUserName(mmcuser.getUserName());
    	//mmcUser.setPassword(bcpe.encode(mmcUser.getPassword()));
		
		return ResponseEntity.ok(ssiuser.save(mmcuser));
	}

	//http://localhost:8080/users/v1/delete/[JSon]
	@DeleteMapping(value = "/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@RequestBody MdlUser mmcUser){
		ssiuser.delete(mmcUser);
	}
	   
    //http://localhost:8080/users/v1/deleteById/1
    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MdlUser> delete(@PathVariable(value = "id") Integer id) throws UxcResourceNotFoundException {
    	MdlUser user;
    	user = ssiuser.findById(id).orElseThrow(() -> new UxcResourceNotFoundException("User not found for this id :: " + id));
    	ssiuser.deleteById(id);
    	return ResponseEntity.ok(user);
    }	
}
