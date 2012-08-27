package model.model;

import model.interfaces.AbsractInterface;

public class Address implements AbsractInterface{

	private Long id;
	private City city;
	private String  description="";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String getMission() {
		return "";
	}
	
}
