package com.amy.serversecurity.controller.rest;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amy.serversecurity.model.MdlUser;
//import com.amy.security.security.jwt.JwtEntryPoint;
import com.amy.serversecurity.service.interfaz.SntUser;
import com.amy.serversecurity.util.exception.UxcResourceNotFoundException;

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
	private SntUser sntuser;
	
	//@Autowired
	//private BCryptPasswordEncoder bcpe;

		//http://localhost:8080/products/v1/pages
	//http://localhost:8080/products/v1/pages/?page/2/size/10/column/name/Ascending/false
	@GetMapping(value = "/pages")
	public ResponseEntity<Page<MdlUser>> pages(
			@RequestParam (required = false, defaultValue = "0") int page,
			@RequestParam (required = false, defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "name") String column,
			@RequestParam (required = false, defaultValue = "true") boolean isAscending){
		
		Page<MdlUser> pages = sntuser.findAll(PageRequest.of(page, size, Sort.by(column)));
		
		if(!isAscending)
		pages = sntuser.findAll(PageRequest.of(page, size, Sort.by(column).descending()));
		return ResponseEntity.ok(pages);
	}
	
	//http://localhost:8080/users/v1/findById/1
    @GetMapping (value = "/getOne/{id}")
    public ResponseEntity<MdlUser> getOne(@PathVariable(value = "id") Integer id) throws UxcResourceNotFoundException {
    	MdlUser mmcUser = sntuser.findById(id).orElseThrow(() -> new UxcResourceNotFoundException("User not found for this id :: " + id));

    	//mmcUser.setId(mmcUser.getId());
    	//mmcUser.setUserName(mmcuser.getUserName());
    	//mmcUser.setPassword(bcpe.encode(mmcUser.getPassword()));
    	
    	return ResponseEntity.ok(mmcUser);
    }
    
    //http://localhost:8080/users/v1/list
	@GetMapping(value = "/list")
	public List<MdlUser> list() throws UxcResourceNotFoundException{
		logger.warn("list");
		return sntuser.list();
	}
	
	//http://localhost:8080/users/v1/insert/[JSon]
	@PostMapping (value = "/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MdlUser>  save(@Valid @RequestBody MdlUser mmcuser, BindingResult result) throws UxcResourceNotFoundException{
    	//User mmcUser = sntuser.findById(mmcuser.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + mmcuser.getId()));

    	//mmcUser.setId(mmcuser.getId());
    	//mmcUser.setUserName(mmcuser.getUserName());
    	//mmcUser.setPassword(bcpe.encode(mmcUser.getPassword()));
		
		return ResponseEntity.ok(sntuser.save(mmcuser));
	}

	//http://localhost:8080/users/v1/delete/[JSon]
	@DeleteMapping(value = "/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@RequestBody MdlUser mmcUser){
		sntuser.delete(mmcUser);
	}
	   
    //http://localhost:8080/users/v1/deleteById/1
    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MdlUser> delete(@PathVariable(value = "id") Integer id) throws UxcResourceNotFoundException {
    	MdlUser user;
    	user = sntuser.findById(id).orElseThrow(() -> new UxcResourceNotFoundException("User not found for this id :: " + id));
    	sntuser.deleteById(id);
    	return ResponseEntity.ok(user);
    }	
}
