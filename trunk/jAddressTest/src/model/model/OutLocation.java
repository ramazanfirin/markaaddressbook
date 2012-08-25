package model.model;

import util.Util;
import model.interfaces.AbsractInterface;

public class OutLocation implements AbsractInterface{
	private Long id;
	
	private AuthorizedPerson firstAuthorizedPerson=new AuthorizedPerson();
	private AuthorizedPerson secondAuthorizedPerson = new AuthorizedPerson() ;
	
	private String firstPhone="";
	private String secondPhone="";
	private String thirdPhone="";
	
	private String ipPhone="";
	
	private String shortCode="";
	private String note="";
	
	private Address address = new Address();
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstPhone(String firstPhone) {
		this.firstPhone = firstPhone;
	}

	public String getSecondPhone() {
		return secondPhone;
	}

	public void setSecondPhone(String secondPhone) {
		this.secondPhone = secondPhone;
	}

	public String getThirdPhone() {
		return thirdPhone;
	}

	public void setThirdPhone(String thirdPhone) {
		this.thirdPhone = thirdPhone;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFirstPhone() {
		return firstPhone;
	}

	public AuthorizedPerson getFirstAuthorizedPerson() {
		return firstAuthorizedPerson;
	}

	public void setFirstAuthorizedPerson(AuthorizedPerson firstAuthorizedPerson) {
		this.firstAuthorizedPerson = firstAuthorizedPerson;
	}

	public AuthorizedPerson getSecondAuthorizedPerson() {
		return secondAuthorizedPerson;
	}

	public void setSecondAuthorizedPerson(AuthorizedPerson secondAuthorizedPerson) {
		this.secondAuthorizedPerson = secondAuthorizedPerson;
	}

	public String getIpPhone() {
		return ipPhone;
	}

	public void setIpPhone(String ipPhone) {
		this.ipPhone = ipPhone;
	}

	
	public String getAllPhone(){
		String result ="";
		if(!Util.isEmpty(firstPhone))
			result = result + " " +Util.getFormattedPhone(firstPhone);
		if(!Util.isEmpty(secondPhone))
			result = result + " " +Util.getFormattedPhone(firstPhone);
		if(!Util.isEmpty(thirdPhone))
			result = result + " " +Util.getFormattedPhone(firstPhone);
		
		return result;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
