package com.amy.security.service.interfaz;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amy.security.model.SmdRole;
import com.amy.security.util.enumerators.SenRoleName;

@Service
public interface SsiRoleService {
	SmdRole save(SmdRole smcRole);
	void delete(SmdRole smcRole);
	void deleteById(Integer id);
	Optional <SmdRole> findById(Integer id);
	Optional <SmdRole> findBySenRoleName(SenRoleName senRoleName);
	List<SmdRole> list();
    boolean existsById(int id);
    boolean existsBySenRoleName(SenRoleName senRoleName);
}
