package com.amy.service_product.service.implement;

import java.util.List;
import java.util.Optional;

import com.amy.service_product.model.MdlProduct;
import com.amy.service_product.repository.RpsProduct;
import com.amy.service_product.service.interfaz.SntProduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsrProduct implements SntProduct {
	private static Logger LOG = LoggerFactory.getLogger(SsrProduct.class);

	@Autowired
	private RpsProduct rpsProduct; 
	
	@Override
	public MdlProduct create(MdlProduct mdlProduct) {
		LOG.trace("create");
		return rpsProduct.save(mdlProduct);
	}

	@Override
	public MdlProduct update(MdlProduct mdlProduct) {
		LOG.trace("update");
		return rpsProduct.save(mdlProduct);
	}
	
	@Override
	public void delete(MdlProduct mdlProduct) {
		LOG.trace("delete");
		rpsProduct.delete(mdlProduct);		
	}

	//@Override
	//public void deleteById(Integer id) {
	//	LOG.trace("deleteById");
	//	rpsProduct.deleteById(id);		
	//}

	@Override
	public Optional<MdlProduct> findById(Integer id) {
		LOG.trace("getOne");
		return rpsProduct.findById(id);
	}

	@Override
	public Optional<MdlProduct> findByName(String name) {
		LOG.trace("findByName");		
		return rpsProduct.findByName(name);
	}

	@Override
	public List<MdlProduct> list() {
		LOG.trace("list");		
		return rpsProduct.findAll();
	}

	@Override
	public Page<MdlProduct> findAll(Pageable pageable) {
		LOG.trace("list");		
		return rpsProduct.findAll(pageable);
	}

	@Override
	public boolean existsById(int id) {
		return rpsProduct.existsById(id);
	}

	@Override
	public boolean existsByName(String name) {
		 return rpsProduct.existsByName(name);
	}
}
