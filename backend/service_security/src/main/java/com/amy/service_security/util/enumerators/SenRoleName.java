package com.amy.service_security.util.enumerators;

/**
 * Enumerators for the control of user roles.
 */
public enum SenRoleName {
	/**
	 * ROLE_ADMIN: Puede consultar, agregar, editar, eliminar
	 * ROLE_USER: Solo puede consultar
	 */
	ROLE_ADMIN, ROLE_USER, ROLE_GUEST
}