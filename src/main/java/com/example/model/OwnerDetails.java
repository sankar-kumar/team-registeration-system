package com.example.model;

public class OwnerDetails {
	
	private String ownerName;
	private String ownerSince;
	private String phoneNumber;
	private String mail;
	private Address address;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSince() {
		return ownerSince;
	}

	public void setOwnerSince(String ownerSince) {
		this.ownerSince = ownerSince;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
