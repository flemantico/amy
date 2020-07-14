package com.amy.serversecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amy.serversecurity.model.MdlRole;
import com.amy.serversecurity.util.enumerators.UnmRoleName;

@Repository
public interface RpsRole extends JpaRepository<MdlRole, Integer> {
	Optional <MdlRole> findBySenRoleName(UnmRoleName senRoleName);
	//Role findByRoleName(RoleName roleName);
}
