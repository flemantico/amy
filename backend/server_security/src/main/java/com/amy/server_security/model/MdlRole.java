package com.amy.server_security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.amy.server_security.util.enumerators.UnmRoleName;

@Entity
@Table(name = "roles")
public class MdlRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@NotNull 
	@Column(name = "id")	
	private Integer id;
	
	@NotNull
	//@NotBlank
	//@Size(min = 4, max = 15, message = "The role name must be between 4 and 15 characters.")
	@Enumerated(EnumType.STRING)
	//@Column(unique = true, nullable = false)
	private UnmRoleName senRoleName;

	public MdlRole() {
	}

	public MdlRole(@NotNull UnmRoleName senRoleName) {
		this.senRoleName = senRoleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UnmRoleName getSenRoleName() {
		return senRoleName;
	}

	public void setSenRoleName(UnmRoleName senRoleName) {
		this.senRoleName = senRoleName;
	}	
}
