package com.amy.product.repository;

import java.util.Optional;

import com.amy.product.model.MdlProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RpsProduct extends JpaRepository<MdlProduct, Integer> {
	Optional<MdlProduct> findByName(String name);
	boolean existsByName(String name);
}
