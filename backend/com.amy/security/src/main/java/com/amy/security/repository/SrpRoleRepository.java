package com.amy.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amy.security.model.SmdRole;
import com.amy.security.util.enumerators.SenRoleName;

@Repository
public interface SrpRoleRepository extends JpaRepository<SmdRole, Integer> {
	Optional <SmdRole> findBySenRoleName(SenRoleName senRoleName);
	//Role findByRoleName(RoleName roleName);
}
