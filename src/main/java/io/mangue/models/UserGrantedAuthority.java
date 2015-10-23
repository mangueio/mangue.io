package io.mangue.models;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {

	public static final String USER_AUTHORITY = "USER";
	public static final String ADMIN_AUTHORITY = "ADMIN";

	String authority;

	@Override
	public String toString() {
		return authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}