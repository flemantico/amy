package com.amy.serversecurity.service.implement;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amy.serversecurity.model.MdlRole;
import com.amy.serversecurity.repository.RpsRole;
import com.amy.serversecurity.service.interfaz.SntRole;
import com.amy.serversecurity.util.enumerators.UnmRoleName;


@Service
@Transactional
public class SsrRole implements SntRole{
	private static Logger LOG = LoggerFactory.getLogger(SsrUser.class);

	@Autowired
	private RpsRole rpsRole;
	
	@Override
	public MdlRole save(MdlRole smdRole) {
		return rpsRole.save(smdRole);
	}

	@Override
	public void delete(MdlRole smdRole) {	
	}

	@Override
	public void deleteById(Integer id) {
	}

	@Override
	public Optional<MdlRole> findById(Integer id) {
		return null;
	}

	@Override
	public Optional<MdlRole> findBySenRoleName(UnmRoleName senRoleName) {
		return rpsRole.findBySenRoleName(senRoleName);
	}	
	
	@Override
	public List<MdlRole> list() {
		return null;
	}

	@Override
	public Page<MdlRole> findAll(Pageable pageable) {
		LOG.trace("list");		
		return rpsRole.findAll(pageable);
	}

	@Override
	public boolean existsById(int id) {
		return false;
	}
	
	@Override
	public boolean existsBySenRoleName(UnmRoleName senRoleName) {
		return false;
	}
	
	


	
}
