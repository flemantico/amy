package com.amy.security.service.interfaz;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amy.security.model.MdlRole;
import com.amy.security.util.enumerators.SenRoleName;

@Service
public interface SntRole {
	MdlRole save(MdlRole smdRole);
	void delete(MdlRole smdRole);
	void deleteById(Integer id);
	Optional <MdlRole> findById(Integer id);
	Optional <MdlRole> findBySenRoleName(SenRoleName senRoleName);
	List<MdlRole> list();
    boolean existsById(int id);
    boolean existsBySenRoleName(SenRoleName senRoleName);
}
