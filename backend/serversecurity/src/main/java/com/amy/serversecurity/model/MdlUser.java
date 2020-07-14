package com.amy.serversecurity.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class MdlUser {
	/*
	Debe extender de persona, persona debe extender de base (usuario, alta, modificacion, etc.)
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull @NotBlank
	@Size(min = 4, max = 15, message = "The name must be between 4 and 15 characters.")
	@Column(length = 15, nullable = false)
	private String name;

	@NotNull @NotBlank
	@Email
	@Column(length = 100, nullable = false)
	private String email;
	
	@NotNull @NotBlank
	@Size(min = 4, max = 15, message = "The user name must be between 4 and 15 characters.")
	@Column(name = "user_name", unique = true, length = 15, nullable = false)
	private String userName;
	
	@NotNull  @NotBlank
	@Size(min = 2, max = 60, message = "The password must be between 2 and 15 characters")
	@Column(length = 60, nullable = false)
	private String password;
	
	//fetch para cargar los roles. EAGER lo carga de forma proactiva y LAZY de manera peresosa, solo cuando se necesiten.
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Set<MdlRole> smdRoles = new HashSet<>();
	
	public MdlUser() {
		//super();
	}
	
	public MdlUser(
			@NotNull @NotBlank @Size(min = 4, max = 15, message = "The name must be between 4 and 15 characters.") String name,
			@NotNull @NotBlank @Email String email,
			@NotNull @NotBlank @Size(min = 4, max = 15, message = "The user name must be between 4 and 15 characters.") String userName,
			@NotNull @NotBlank @Size(min = 2, max = 60, message = "The password must be between 2 and 15 characters") String password) {
		super();
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<MdlRole> getSmdRoles() {
		return smdRoles;
	}
	public void setSmdRoles(Set<MdlRole> smdRoles) {
		this.smdRoles = smdRoles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userName=" + userName + ", password=" + password + ", roles=" + smdRoles + "]";
	}
}
