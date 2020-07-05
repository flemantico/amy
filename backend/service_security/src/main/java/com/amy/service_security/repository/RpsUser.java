package com.amy.service_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amy.service_security.model.MdlUser;

@Repository
public interface RpsUser extends JpaRepository<MdlUser, Integer>{
	//Optional<User> findByUserName(String userName);
	Optional <MdlUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
	//Optional<SmCUser> findById(Long id);
	
	//@Query puedo usar JPQuery ara consultas mas especificas
}
