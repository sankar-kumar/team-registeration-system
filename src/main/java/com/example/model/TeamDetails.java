package com.example.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team-data")
public class TeamDetails {

	@Id
	private String _id;
	private String teamName;
	private String teamNo;
	private String fromDate;
	private String createdDate;
	private String country;
	private Boolean isWomenInTeam;
	private OwnerDetails ownerDetails;
	private List<PlayerDetails> memberDetails;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIsWomenInTeam() {
		return isWomenInTeam;
	}

	public void setIsWomenInTeam(Boolean isWomenInTeam) {
		this.isWomenInTeam = isWomenInTeam;
	}

	public OwnerDetails getOwnerDetails() {
		return ownerDetails;
	}

	public void setOwnerDetails(OwnerDetails ownerDetails) {
		this.ownerDetails = ownerDetails;
	}

	public List<PlayerDetails> getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(List<PlayerDetails> memberDetails) {
		this.memberDetails = memberDetails;
	}

	public String getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
