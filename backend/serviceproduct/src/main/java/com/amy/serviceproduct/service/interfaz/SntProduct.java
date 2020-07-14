package com.amy.serviceproduct.service.interfaz;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amy.serviceproduct.model.MdlProduct;

@Service
public interface SntProduct {
	MdlProduct create(MdlProduct mdlProduct);
	MdlProduct update(MdlProduct mdlProduct);
	void delete(MdlProduct mdlProduct);
	//void deleteById(Integer id);
	Optional <MdlProduct> findById(Integer id);
	Optional <MdlProduct> findByName(String name);
	List<MdlProduct> list();
	Page<MdlProduct> findAll(Pageable pageable);
    boolean existsById(int id);
    boolean existsByName(String name);
}
