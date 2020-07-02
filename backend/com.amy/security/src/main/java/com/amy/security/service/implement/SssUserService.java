package com.amy.security.service.implement;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amy.security.model.SmdPrincipalUser;
import com.amy.security.model.SmdUser;
import com.amy.security.repository.SrpUserRepository;
import com.amy.security.service.interfaz.SsiUserService;


@Service
@Transactional
public class SssUserService implements SsiUserService{
	private static Logger LOG = LoggerFactory.getLogger(SssUserService.class);

	@Autowired
	private SrpUserRepository srpUserRepository;

	@Override
	public SmdUser save(SmdUser smdUser) {
		LOG.trace("insert");
		return srpUserRepository.save(smdUser);
	}
	
	@Override
	public void delete(SmdUser smdUser) {
		LOG.trace("delete");
		srpUserRepository.delete(smdUser);
	}

	@Override
	public void deleteById(Integer id) {
		LOG.trace("deleteById");
		srpUserRepository.deleteById(id);
	}

	@Override
	public Optional <SmdUser> findById(Integer id) {
		LOG.trace("getOne");
		return srpUserRepository.findById(id);
	}

	@Override
	public List<SmdUser> list() {
		LOG.trace("list");		
		return srpUserRepository.findAll();
	}

	@Override
	//public Optional <User> findByUserName(String userName) {
	public Optional <SmdUser> findByUserName(String userName) {
		LOG.trace("findByUserName");		
		return srpUserRepository.findByUserName(userName);
	}
	
	@Override
    public boolean existsById(int id){
        return srpUserRepository.existsById(id);
    }

    @Override
    public boolean existsByUserName(String userName){
        return srpUserRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email){
        return srpUserRepository.existsByEmail(email);
    }
    
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOG.trace("loadUserByUsername");
		SmdUser smdUser = srpUserRepository.findByUserName(userName).get();

		//List<GrantedAuthority> roles = new ArrayList<>();
		//roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		//UserDetails userDetails = 
		//new org.springframework.security.core.userdetails
		//.User(mcuUser.getUserName(), mcuUser.getPassword(), roles);
		
		//return userDetails;
		
		return SmdPrincipalUser.build(smdUser);
		
		
		
		
	} 
}
