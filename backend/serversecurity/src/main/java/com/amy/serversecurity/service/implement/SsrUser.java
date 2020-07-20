package com.amy.serversecurity.service.implement;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amy.serversecurity.model.MdlPrincipalUser;
import com.amy.serversecurity.model.MdlUser;
import com.amy.serversecurity.repository.RpsUser;
import com.amy.serversecurity.service.interfaz.SntUser;


@Service
@Transactional
public class SsrUser implements SntUser{
	private static Logger LOG = LoggerFactory.getLogger(SsrUser.class);

	@Autowired
	private RpsUser rpsUser;

	@Override
	public MdlUser save(MdlUser smdUser) {
		LOG.trace("insert");
		return rpsUser.save(smdUser);
	}
	
	@Override
	public void delete(MdlUser smdUser) {
		LOG.trace("delete");
		rpsUser.delete(smdUser);
	}

	@Override
	public void deleteById(Integer id) {
		LOG.trace("deleteById");
		rpsUser.deleteById(id);
	}

	@Override
	public Optional <MdlUser> findById(Integer id) {
		LOG.trace("getOne");
		return rpsUser.findById(id);
	}

	@Override
	public List<MdlUser> list() {
		LOG.trace("list");		
		return rpsUser.findAll();
	}

	@Override
	public Page<MdlUser> findAll(Pageable pageable) {
		LOG.trace("list");		
		return rpsUser.findAll(pageable);
	}

	@Override
	//public Optional <User> findByUserName(String userName) {
	public Optional <MdlUser> findByUserName(String userName) {
		LOG.trace("findByUserName");		
		return rpsUser.findByUserName(userName);
	}
	
	@Override
    public boolean existsById(int id){
        return rpsUser.existsById(id);
    }

    @Override
    public boolean existsByUserName(String userName){
        return rpsUser.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email){
        return rpsUser.existsByEmail(email);
    }
    
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOG.trace("loadUserByUsername");
		MdlUser smdUser = rpsUser.findByUserName(userName).get();

		//List<GrantedAuthority> roles = new ArrayList<>();
		//roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		//UserDetails userDetails = 
		//new org.springframework.security.core.userdetails
		//.User(mcuUser.getUserName(), mcuUser.getPassword(), roles);
		
		//return userDetails;
		
		return MdlPrincipalUser.build(smdUser);
		
		
		
		
	} 
}
