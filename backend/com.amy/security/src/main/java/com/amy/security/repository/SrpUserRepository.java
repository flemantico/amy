package com.amy.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amy.security.model.SmdUser;

@Repository
public interface SrpUserRepository extends JpaRepository<SmdUser, Integer>{
	//Optional<User> findByUserName(String userName);
	Optional <SmdUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
	//Optional<SmCUser> findById(Long id);
	
	//@Query puedo usar JPQuery ara consultas mas especificas
}
