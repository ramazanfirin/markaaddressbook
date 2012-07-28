package model.model;

import model.interfaces.AbsractInterface;

public class Authority implements AbsractInterface{

	private Long id;
	private String authority;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
