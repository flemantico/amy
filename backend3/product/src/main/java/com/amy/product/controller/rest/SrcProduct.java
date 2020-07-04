package com.amy.product.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amy.product.model.MdlProduct;
import com.amy.product.service.interfaz.SntProduct;
import com.amy.product.util.exception.SecErrorMessage;
import com.amy.product.util.exception.SecResourceNotFoundException;
//import com.amy.security.security.jwt.JwtEntryPoint;

@RestController
@RequestMapping(path = "/products/v1")
@CrossOrigin(origins ={"*"})
//@RequestMapping (path = "/products/v1")//,  method = {RequestMethod.GET, RequestMethod.PUT})
//@CrossOrigin (origins = "http://localhost:4200")
public class SrcProduct {
	private final static Logger logger = LoggerFactory.getLogger(SrcProduct.class);
	
	@Autowired
	private SntProduct sntProduct;
	
	//http://localhost:8080/products/v1/pages
	//http://localhost:8080/products/v1/pages/?page/2/size/10/column/name/Ascending/false
	@GetMapping(value = "/pages")
	public ResponseEntity<Page<MdlProduct>> pages(
			@RequestParam (required = false, defaultValue = "0") int page,
			@RequestParam (required = false, defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "name") String column,
			@RequestParam (required = false, defaultValue = "true") boolean isAscending){
		
		Page<MdlProduct> pages = sntProduct.findAll(PageRequest.of(page, size, Sort.by(column)));
		
		if(!isAscending)
		pages = sntProduct.findAll(PageRequest.of(page, size, Sort.by(column).descending()));
		return ResponseEntity.ok(pages);
	}
	
	//http://localhost:8080/products/v1/getOne/1
    @GetMapping (value = "/getById/{id}")
    public ResponseEntity<MdlProduct> getOne(@PathVariable(value = "id") Integer id) throws SecResourceNotFoundException {
    	MdlProduct mdlProduct = sntProduct.findById(id).orElseThrow(() -> new SecResourceNotFoundException("Product not found for this id :: " + id));
    	
    	return ResponseEntity.ok(mdlProduct);
    }
    
	//http://localhost:8080/products/v1/getOne/
    @GetMapping (value = "/getByName/{name}")
    public ResponseEntity<MdlProduct> getOne(@PathVariable(value = "name") String name) throws SecResourceNotFoundException {
    	MdlProduct mdlProduct = sntProduct.findByName(name).orElseThrow(() -> new SecResourceNotFoundException("Product not found for this name :: " + name));
    	
    	return ResponseEntity.ok(mdlProduct);
    }
    
    //http://localhost:8080/products/v1/list
	@GetMapping(value = "/list")
	public ResponseEntity <List<MdlProduct>> list() throws SecResourceNotFoundException{
		List<MdlProduct> products = sntProduct.list();
		if(products.isEmpty()){
			return ResponseEntity.noContent().build(); 
//			return new ResourceNotFoundException("Products not found.");
		}
		logger.warn("list");
		return ResponseEntity.ok(products);
	}
    
	//http://localhost:8080/products/v1/create/[JSon]
	@PostMapping (value = "/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MdlProduct> create(@Valid @RequestBody MdlProduct mdlProduct, BindingResult result) throws SecResourceNotFoundException{
		if (result.hasErrors()) {
			throw new SecResourceNotFoundException(SecErrorMessage.formatMessage("01", result) + "::" + mdlProduct.toString());
		}

		return ResponseEntity.ok(sntProduct.create(mdlProduct));
	}
	
	//http://localhost:8080/products/v1/update/[JSon]
	@PutMapping (value = "/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MdlProduct> update(@Valid @RequestBody MdlProduct mdlProduct, BindingResult result) throws SecResourceNotFoundException{
		if (result.hasErrors()) {
			throw new SecResourceNotFoundException(SecErrorMessage.formatMessage("02", result) + "::" + mdlProduct.toString());
		}		

		MdlProduct smcproduct = sntProduct.findById(mdlProduct.getId()).orElseThrow(() -> new SecResourceNotFoundException("Product not found for this id :: " + mdlProduct.getId()));
    	smcproduct.setName(mdlProduct.getName());
    	smcproduct.setPrice(mdlProduct.getPrice());
		
		return ResponseEntity.ok(sntProduct.update(smcproduct));
	}
	
	//http://localhost:8080/products/v1/update/1/[JSon]
	@PutMapping (value = "/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MdlProduct> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody MdlProduct mdlProduct, BindingResult result) throws SecResourceNotFoundException{
		if (result.hasErrors()) {
			throw new SecResourceNotFoundException(SecErrorMessage.formatMessage("02", result) + "::" + mdlProduct.toString());
		}		

		MdlProduct smcproduct = sntProduct.findById(id).orElseThrow(() -> new SecResourceNotFoundException("Product not found for this id :: " + id));
    	smcproduct.setName(mdlProduct.getName());
    	smcproduct.setPrice(mdlProduct.getPrice());
		
		return ResponseEntity.ok(sntProduct.update(smcproduct));
	}
	
	//http://localhost:8080/products/v1/delete/[JSon]
	@DeleteMapping(value = "/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public  ResponseEntity<MdlProduct> delete(@RequestBody MdlProduct mdlProduct, BindingResult result) throws SecResourceNotFoundException{
		if (result.hasErrors()) {
			throw new SecResourceNotFoundException(SecErrorMessage.formatMessage("03", result) + "::" + mdlProduct.toString());
		}	
    	MdlProduct smcProduct = sntProduct.findById(mdlProduct.getId()).orElseThrow(() -> new SecResourceNotFoundException("Product not found for this id :: " + mdlProduct.getId()));
    	sntProduct.delete(smcProduct);
		
		return ResponseEntity.ok(mdlProduct);
	}
	   
    //http://localhost:8080/products/v1/delete/1
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MdlProduct> delete(@PathVariable(value = "id") Integer id) throws SecResourceNotFoundException {
    	MdlProduct mdlProduct = sntProduct.findById(id).orElseThrow(() -> new SecResourceNotFoundException("Product not found for this id :: " + id));
    	sntProduct.delete(mdlProduct);
    	
    	return ResponseEntity.ok(mdlProduct);
    }	
}

/*TODO
 * Ver diferencias entre @RequestParam y @PathVariable
 * 
 * delete/JSON. debe buscar por objeto no por id
 * 
 * ver si hace falta idempotente en create
 * */
