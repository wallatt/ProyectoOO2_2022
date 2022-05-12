package com.unla.grupo13.TrabajoPractico.entities;

public class UserRole {

	
	private int id;
	private String role;
	private boolean enabled;
	
	public UserRole () {}
	
	
	public UserRole(String role, boolean enabled) {
		
		this.role=role;
		this.enabled=enabled;
		
		
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
