package com.amy.security.util.data;

import com.amy.security.model.SmdRole;
import com.amy.security.service.implement.SssRoleService;
import com.amy.security.util.enumerators.SenRoleName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Crea los roles
@Component
public class SutCreateRole implements CommandLineRunner{

	@Autowired
	SssRoleService sssRoleService;
	
	@Override
	public void run(String... args) throws Exception {
		SmdRole rolAdmin = new SmdRole(SenRoleName.ROLE_ADMIN);
		SmdRole rolUser = new SmdRole(SenRoleName.ROLE_USER);
		SmdRole rolGuest = new SmdRole(SenRoleName.ROLE_GUEST);
		
		sssRoleService.save(rolAdmin);
		sssRoleService.save(rolUser);
		sssRoleService.save(rolGuest);
	}
}
