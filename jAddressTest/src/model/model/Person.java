package model.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import util.Util;

import model.interfaces.AbsractInterface;

public class Person implements AbsractInterface{

	private Long id;
	private String name="";
	private String surname="";
	private String phone="";
	private Date insertDate= new Date();
    private Set<Bus> busList = new HashSet<Bus>(0);

	
	
	
	public Set<Bus> getBusList() {
		return busList;
	}

	public void setBusList(Set<Bus> busList) {
		this.busList = busList;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	public String getNameSurname() {
		return getName()+" "+getSurname()+"   "+getFormattedPhone();
	}
	
	public String getFormattedPhone(){
		return Util.getFormattedPhone(phone);
	}
	
	public String getNameSurnamePhone() {
		return getName()+" "+getSurname()+" "+getFormattedPhone() ;
	}
	
}
