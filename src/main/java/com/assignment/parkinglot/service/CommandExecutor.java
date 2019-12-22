package com.assignment.parkinglot.service;

import com.assignment.parkinglot.exceptions.ParkingException;

/**
 * 
 * @author Nitesh
 *
 */
public interface CommandExecutor {

	/**
	 * Sets the specific implementation of a parking lot
	 * 
	 * @param paServiceImple
	 */
	public void setParkingLotService(ParkingServiceImpl paServiceImpl);

	/**
	 * Executes the command
	 * 
	 * @param command
	 * @throws ParkingException
	 */
	public void execute(String command) throws ParkingException;

}
