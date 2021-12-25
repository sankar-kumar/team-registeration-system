package com.example.adapter;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.TeamDetails;

public class TeamDetailsAdapter {

	public static Logger logger = LoggerFactory.getLogger(TeamDetailsAdapter.class);

	/**
	 * @param uiTeamDetails.
	 * @return TeamDetails.
	 */
	public static TeamDetails saveTeamDetails(TeamDetails uiTeamDetails) {
		logger.info("Inside saveTeamDetails");
		TeamDetails dbTeamDetail = new TeamDetails();
		dbTeamDetail.set_id(uiTeamDetails.get_id());
		dbTeamDetail.setCountry(uiTeamDetails.getCountry());
		dbTeamDetail.setFromDate(uiTeamDetails.getFromDate());
		dbTeamDetail.setTeamName(uiTeamDetails.getTeamName());
		dbTeamDetail.setTeamNo(uiTeamDetails.getTeamNo());
		dbTeamDetail.setCreatedDate(LocalDateTime.now().toString());
		if (uiTeamDetails.getOwnerDetails() != null) {
			dbTeamDetail.setOwnerDetails(OwnerDetailsAdapter.saveOwnerDetails(uiTeamDetails.getOwnerDetails()));
		}
		if (uiTeamDetails.getMemberDetails() != null) {
			dbTeamDetail.setMemberDetails(PlayerDetailsAdapter.savePlayers(uiTeamDetails.getMemberDetails()));
		}
		return dbTeamDetail;
	}

	/**
	 * @param dbTeamDetails.
	 * @return TeamDetails.
	 */
	public static TeamDetails getTeamDetails(TeamDetails dbTeamDetails) {
		logger.info("Inside getTeamDetails");
		TeamDetails uiTeamDetails = new TeamDetails();
		uiTeamDetails.set_id(dbTeamDetails.get_id());
		uiTeamDetails.setCountry(dbTeamDetails.getCountry());
		uiTeamDetails.setFromDate(dbTeamDetails.getFromDate());
		uiTeamDetails.setTeamName(dbTeamDetails.getTeamName());
		uiTeamDetails.setTeamNo(dbTeamDetails.getTeamNo());
		uiTeamDetails.setCreatedDate(dbTeamDetails.getCreatedDate());
		if (dbTeamDetails.getOwnerDetails() != null) {
			uiTeamDetails.setOwnerDetails(OwnerDetailsAdapter.getOwnerDetails(dbTeamDetails.getOwnerDetails()));
		}
		if (dbTeamDetails.getMemberDetails() != null) {
			uiTeamDetails.setMemberDetails(PlayerDetailsAdapter.getPlayers(dbTeamDetails.getMemberDetails()));
		}
		return uiTeamDetails;
	}
}
