package com.amy.serversecurity.service.interfaz;

import java.util.List;
import java.util.Optional;

//import java.util.ArrayList;
//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amy.serversecurity.model.MdlUser;

//import com.amy.security.security.model.model.SmdUser;
//import com.amy.security.security.service.repository.SsCUserRepository;

@Service
public interface SntUser extends UserDetailsService{
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;	
 
	//void insert(User SmdUser);
	MdlUser save(MdlUser SmdUser);
	//User insertById(Integer id, User SmdUser);
	//void update(User SmdUser);
	//User updateById(Integer id, User SmdUser);
	void delete(MdlUser SmdUser);
	void deleteById(Integer id);
	Optional <MdlUser> findById(Integer id);
	Optional <MdlUser> findByUserName(String userName);
	List<MdlUser> list();
    boolean existsById(int id);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
