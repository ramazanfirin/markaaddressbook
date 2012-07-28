package model.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.interfaces.AbsractInterface;

public class Driver implements AbsractInterface{

	
	private String name="";
	private String surname="";
	private String phone="";
	private Long id;
	private Date insertDate;
	
	private Set<Bus> firstDriverBusList = new HashSet<Bus>(0);
	private Set<Bus> secondDriverBusList = new HashSet<Bus>(0);
	private Set<Bus> thirdDriverBusList = new HashSet<Bus>(0);
	
	private Bus bus;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	public String getNameSurname(){
		return getName()+" "+getSurname();
	}
	public Set<Bus> getFirstDriverBusList() {
		return firstDriverBusList;
	}
	public void setFirstDriverBusList(Set<Bus> firstDriverBusList) {
		this.firstDriverBusList = firstDriverBusList;
	}
	public Set<Bus> getSecondDriverBusList() {
		return secondDriverBusList;
	}
	public void setSecondDriverBusList(Set<Bus> secondDriverBusList) {
		this.secondDriverBusList = secondDriverBusList;
	}
	public Set<Bus> getBusList() {
		HashSet<Bus> set = new HashSet<Bus>();
        set.addAll(getFirstDriverBusList());
        set.addAll(getSecondDriverBusList());
        set.addAll(getThirdDriverBusList());

		return set;
	
	}
	public Set<Bus> getThirdDriverBusList() {
		return thirdDriverBusList;
	}
	public void setThirdDriverBusList(Set<Bus> thirdDriverBusList) {
		this.thirdDriverBusList = thirdDriverBusList;
	}
}
