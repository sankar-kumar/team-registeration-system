package com.example.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.OwnerDetails;

public class OwnerDetailsAdapter {

	public static Logger logger = LoggerFactory.getLogger(OwnerDetailsAdapter.class);

	/**
	 * @param uiOwneraReq.
	 * @return OwnerDetails.
	 */
	public static OwnerDetails saveOwnerDetails(OwnerDetails uiOwneraReq) {
		logger.info("Inside saveOwnerDetails");
		OwnerDetails dbOwner = new OwnerDetails();
		dbOwner.setOwnerName(uiOwneraReq.getOwnerName());
		dbOwner.setOwnerSince(uiOwneraReq.getOwnerSince());
		dbOwner.setMail(uiOwneraReq.getMail());
		dbOwner.setPhoneNumber(uiOwneraReq.getPhoneNumber());
		if (uiOwneraReq.getAddress() != null) {
			dbOwner.setAddress(AddressAdapter.saveAddress(uiOwneraReq.getAddress()));
		}
		return dbOwner;
	}

	/**
	 * @param dbOwner.
	 * @return OwnerDetails.
	 */
	public static OwnerDetails getOwnerDetails(OwnerDetails dbOwner) {
		logger.info("Inside getOwnerDetails");
		OwnerDetails uiOwneraReq = new OwnerDetails();
		uiOwneraReq.setOwnerName(dbOwner.getOwnerName());
		uiOwneraReq.setOwnerSince(dbOwner.getOwnerSince());
		uiOwneraReq.setMail(dbOwner.getMail());
		uiOwneraReq.setPhoneNumber(dbOwner.getPhoneNumber());
		if (uiOwneraReq.getAddress() != null) {
			uiOwneraReq.setAddress(AddressAdapter.getAddress(dbOwner.getAddress()));
		}
		return uiOwneraReq;
	}

}
