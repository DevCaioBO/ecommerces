package com.senai.ecommerce.entities;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Auto-incremented ID
	private String authority; // Role name (e.g., "ROLE_USER", "ROLE_ADMIN")
	
	
	
	

	/**
	 * @param id
	 * @param authority
	 */
	public Role(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	
	
}
