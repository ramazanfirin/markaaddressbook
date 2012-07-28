package model.model;

import model.interfaces.AbsractInterface;


public class User implements AbsractInterface{

	
	private Long id;
	private String username="";
	private String password="";
	private Authority authority;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
}
