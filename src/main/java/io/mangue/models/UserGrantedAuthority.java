package io.mangue.models;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {

	public static final String USER = "USER";
	public static final String NETWORK_ADMIN = "ADMIN";

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