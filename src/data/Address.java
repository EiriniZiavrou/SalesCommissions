package data;

public class Address {
	private String country;
	private String city;
	private String street;
	private int streetNumber;
	private int phoneNumber;


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean equals(Address adr){
		if(!country.equals(adr.getCountry())) return false;
		if(!city.equals(adr.getCity())) return false;
		if(!street.equals(adr.getStreet())) return false;
		if(streetNumber!=adr.getStreetNumber()) return false;
		if(phoneNumber!=adr.getPhoneNumber()) return false;
		return true;
	}
}

