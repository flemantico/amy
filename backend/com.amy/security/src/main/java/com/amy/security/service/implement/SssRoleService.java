package com.amy.security.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amy.security.model.SmdRole;
import com.amy.security.repository.SrpRoleRepository;
import com.amy.security.service.interfaz.SsiRoleService;
import com.amy.security.util.enumerators.SenRoleName;


@Service
@Transactional
public class SssRoleService implements SsiRoleService{

	@Autowired
	private SrpRoleRepository srpRoleRepository;
	
	@Override
	public SmdRole save(SmdRole smcRole) {
		return srpRoleRepository.save(smcRole);
	}

	@Override
	public void delete(SmdRole smcRole) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<SmdRole> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SmdRole> findBySenRoleName(SenRoleName roleName) {
		return srpRoleRepository.findBySenRoleName(roleName);
	}	
	
	@Override
	public List<SmdRole> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(int id) {
		return false;
	}
	
	@Override
	public boolean existsBySenRoleName(SenRoleName roleName) {
		return false;
	}
	
	


	
}
