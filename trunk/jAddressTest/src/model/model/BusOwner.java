package model.model;

import java.util.HashSet;
import java.util.Set;

public class BusOwner extends Person{
	
	private Set<Bus> firstBusOwnerList = new HashSet<Bus>(0);
	private Set<Bus> secondBusOwnerList = new HashSet<Bus>(0);
	
	private String shortCode="";
	
	public Set<Bus> getBusList() {
		HashSet<Bus> set = new HashSet<Bus>();
        set.addAll(getFirstBusOwnerList());
        set.addAll(getSecondBusOwnerList());
        return set;
	
	}

	public Set<Bus> getFirstBusOwnerList() {
		return firstBusOwnerList;
	}

	public void setFirstBusOwnerList(Set<Bus> firstBusOwnerList) {
		this.firstBusOwnerList = firstBusOwnerList;
	}

	public Set<Bus> getSecondBusOwnerList() {
		return secondBusOwnerList;
	}

	public void setSecondBusOwnerList(Set<Bus> secondBusOwnerList) {
		this.secondBusOwnerList = secondBusOwnerList;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	
}