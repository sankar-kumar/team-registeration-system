package com.example.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Address;

public class AddressAdapter {

	public static Logger logger = LoggerFactory.getLogger(AddressAdapter.class);

	/**
	 * @param uiAddress.
	 * @return Address.
	 */
	public static Address saveAddress(Address uiAddress) {

		logger.info("Inside saveAddress");
		Address dbAddress = new Address();
		dbAddress.setStreet1(uiAddress.getStreet1());
		dbAddress.setStreet2(uiAddress.getStreet2());
		dbAddress.setState(uiAddress.getState());
		dbAddress.setCity(uiAddress.getCity());
		dbAddress.setZipcode(uiAddress.getZipcode());

		return dbAddress;
	}

	/**
	 * @param dbAddress.
	 * @return Address.
	 */
	public static Address getAddress(Address dbAddress) {

		logger.info("Inside getAddress");
		Address uiAddress = new Address();
		uiAddress.setStreet1(dbAddress.getStreet1());
		uiAddress.setStreet2(dbAddress.getStreet2());
		uiAddress.setState(dbAddress.getState());
		uiAddress.setCity(dbAddress.getCity());
		uiAddress.setZipcode(dbAddress.getZipcode());

		return uiAddress;
	}
}