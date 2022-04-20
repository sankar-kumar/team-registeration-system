package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.model.PlayerDetails;

@Component
public class PlayerDetailsAdapter {

	public static Logger logger = LoggerFactory.getLogger(PlayerDetailsAdapter.class);

	/**
	 * @param uiPlayerList.
	 * @return DbPlayerDetails.
	 */
	public static List<PlayerDetails> savePlayers(List<PlayerDetails> uiPlayerList) {
		logger.info("Inside savePlayers");
//		List object for Database model.
		List<PlayerDetails> dbPlayersList = new ArrayList<>();

//		PlayerDetails-->Input we are Iterating here
		if (uiPlayerList.size() > 0) {
			for (PlayerDetails player : uiPlayerList) {
//			here we are creating one more object for Database model.
//			and adding the iterated values to previously created dataBase list in 12.
				PlayerDetails dbPlayer = new PlayerDetails();
				dbPlayer.setFirstName(player.getFirstName());
				dbPlayer.setLastName(player.getLastName());
				dbPlayer.setDateOfBirth(player.getDateOfBirth());
				dbPlayer.setAge(player.getAge());
				dbPlayer.setDateOfBirth(player.getDateOfBirth());
				dbPlayer.setMobileNumber(player.getMobileNumber());
				dbPlayer.setBloodGroup(player.getBloodGroup());
				if (player.getAddress() != null) {
					dbPlayer.setAddress(AddressAdapter.saveAddress(player.getAddress()));
				}
				dbPlayersList.add(dbPlayer);
			}
		}
		return dbPlayersList;
	}

	/**
	 * @param dbPlayerList.
	 * @return UIPlayerDetails.
	 */
	public static List<PlayerDetails> getPlayers(List<PlayerDetails> dbPlayerList) {
		logger.info("Inside getPlayers");
//		List object for Database model.
		List<PlayerDetails> uiPlayersList = new ArrayList<>();

//		PlayerDetails-->Input we are Iterating here
//		if (!CollectionUtils.isEmpty(dbPlayersList) && dbPlayerList.size() > 0) {
		if (dbPlayerList.size() > 0) {
			for (PlayerDetails player : dbPlayerList) {
//			here we are creating one more object for UI request. 
//			and adding the iterated values to previously created Ui Player list in 36.
				PlayerDetails uiPlayer = new PlayerDetails();
				uiPlayer.setFirstName(player.getFirstName());
				uiPlayer.setLastName(player.getLastName());
				uiPlayer.setDateOfBirth(player.getDateOfBirth());
				uiPlayer.setAge(player.getAge());
				uiPlayer.setDateOfBirth(player.getDateOfBirth());
				uiPlayer.setMobileNumber(player.getMobileNumber());
				uiPlayer.setBloodGroup(player.getBloodGroup());
				if (player.getAddress() != null) {
					uiPlayer.setAddress(AddressAdapter.getAddress(player.getAddress()));
				}
				uiPlayersList.add(uiPlayer);
			}
		}
		return uiPlayersList;
	}
}
