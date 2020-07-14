package com.amy.serversecurity.service.interfaz;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amy.serversecurity.model.MdlRole;
import com.amy.serversecurity.util.enumerators.UnmRoleName;

@Service
public interface SntRole {
	MdlRole save(MdlRole smdRole);
	void delete(MdlRole smdRole);
	void deleteById(Integer id);
	Optional <MdlRole> findById(Integer id);
	Optional <MdlRole> findBySenRoleName(UnmRoleName senRoleName);
	List<MdlRole> list();
    boolean existsById(int id);
    boolean existsBySenRoleName(UnmRoleName senRoleName);
}
