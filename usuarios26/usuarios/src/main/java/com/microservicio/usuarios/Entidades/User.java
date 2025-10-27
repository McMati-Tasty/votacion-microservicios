package com.microservicio.usuarios.Entidades;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.microservicio.usuarios.enums.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String username;
	private String password;
	private boolean expired;
	private boolean looked;
	private boolean enabled;

	@Enumerated (EnumType.STRING)
	private Roles rol;
	
	public User() {
		
	}
	

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return looked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return expired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public User(String username, String password, boolean expired, boolean looked, boolean enabled, Roles rol ) {
		super();
		this.username = username;
		this.password = password;
		this.expired = expired;
		this.looked =  looked;
		this.enabled = enabled;
		this.rol = rol;	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of( new SimpleGrantedAuthority(this.rol.name())); 
	}

}