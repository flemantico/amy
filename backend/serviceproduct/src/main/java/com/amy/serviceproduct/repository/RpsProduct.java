package com.amy.serviceproduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amy.serviceproduct.model.MdlProduct;

@Repository
public interface RpsProduct extends JpaRepository<MdlProduct, Integer> {
	Optional<MdlProduct> findByName(String name);
	boolean existsByName(String name);
}
