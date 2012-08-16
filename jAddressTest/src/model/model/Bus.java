package model.model;

import java.util.HashSet;
import java.util.Set;

import util.Util;

import model.interfaces.AbsractInterface;

public class Bus implements AbsractInterface{
	private String plate="";
	private String phone="";
	
	private Set<Driver> driverList = new HashSet<Driver>(0);
	
	private Long id;
	
	private Driver firstDriver;
	private Driver secondDriver;
	private Driver thirdDriver;
	
	private Host host;
	
	private BusOwner firstOwner;
	private BusOwner secondOwner;
	
	private Muavin muavin;
	
	private String shortCode="";
	
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
	public Driver getThirdDriver() {
		return thirdDriver;
	}
	public void setThirdDriver(Driver thirdDriver) {
		this.thirdDriver = thirdDriver;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public BusOwner getFirstOwner() {
		return firstOwner;
	}
	public void setFirstOwner(BusOwner firstOwner) {
		this.firstOwner = firstOwner;
	}
	public BusOwner getSecondOwner() {
		return secondOwner;
	}
	public void setSecondOwner(BusOwner secondOwner) {
		this.secondOwner = secondOwner;
	}

	public String getFormattedPhone(){
		return Util.getFormattedPhone(phone);
	}
	public Muavin getMuavin() {
		return muavin;
	}
	public void setMuavin(Muavin muavin) {
		this.muavin = muavin;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
}
