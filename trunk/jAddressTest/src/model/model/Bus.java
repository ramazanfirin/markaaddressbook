package model.model;

import java.util.HashSet;
import java.util.Set;

import model.interfaces.AbsractInterface;

public class Bus implements AbsractInterface{
	private String plate="";
	private String phone="";
	
	private Set<Driver> driverList = new HashSet<Driver>(0);
	
	private Long id;
	
	private Driver firstDriver;
	private Driver secondDriver;
	
	
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
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
	public Set<Driver> getDriverList() {
		return driverList;
	}
	public void setDriverList(Set<Driver> driverList) {
		this.driverList = driverList;
	}
	public Driver getFirstDriver() {
		return firstDriver;
	}
	public void setFirstDriver(Driver firstDriver) {
		this.firstDriver = firstDriver;
	}
	public Driver getSecondDriver() {
		return secondDriver;
	}
	public void setSecondDriver(Driver secondDriver) {
		this.secondDriver = secondDriver;
	}

	
}
