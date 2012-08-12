package model.model;

public class AuthorizedPerson extends Person{

	public enum EnumLocationType { OutOffice ,BreakLocation};
	
	private String locationType;

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
}
