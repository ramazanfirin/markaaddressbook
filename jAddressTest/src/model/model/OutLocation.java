package model.model;

import model.interfaces.AbsractInterface;

public class OutLocation implements AbsractInterface{
	private Long id;
	
	private AuthorizedPerson firstAuthorizedPerson=new AuthorizedPerson();
	private AuthorizedPerson secondAuthorizedPerson = new AuthorizedPerson() ;
	
	private String firstPhone;
	private String secondPhone;
	private String thirdPhone;
	
	private String shortCode;
	private String note;
	
	private String adress;
	
	
	

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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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

	
}
