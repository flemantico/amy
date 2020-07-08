package com.amy.server_security.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//La utiliza spring security para oibtener los datos y privilegios de usuario
public class MdlPrincipalUser implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public MdlPrincipalUser(String userName, String password, Collection<? extends GrantedAuthority> authorities) {
		this.userName = userName;
		this.password = password;
		this.authorities = authorities;
	}

	public static MdlPrincipalUser build(MdlUser user) {
		List<GrantedAuthority> authorities =
		user.getSmdRoles().stream().map(role -> new SimpleGrantedAuthority(role.getSenRoleName().name())).collect(Collectors.toList());
	
		return new MdlPrincipalUser(user.getUserName(), user.getPassword(), authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}