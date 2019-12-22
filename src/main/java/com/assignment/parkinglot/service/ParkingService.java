package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entities.Slot;
import com.assignment.parkinglot.entities.Vehicle;
import com.assignment.parkinglot.exceptions.ParkingException;

/**
 * Service class for Parking
 * 
 * @author Nitesh
 *
 */
public interface ParkingService {

	/**
	 * Created the parking lot with the given capacity
	 * 
	 * @param capacity
	 * @throws ParkingException
	 */
	public void createParkingLot(int capacity) throws ParkingException;

	/**
	 * Parks the given vehicle
	 * 
	 * @param vehicle
	 * @return the slot at which the vehicle was parked
	 * @throws ParkingException
	 */
	public Slot parkVehicle(Vehicle vehicle) throws ParkingException;

	/**
	 * Unparks the given slot
	 * 
	 * @param slot
	 * @return the vehicle which was unparked
	 * @throws ParkingException
	 */
	public Vehicle unparkVehicle(int slot) throws ParkingException;

	/**
	 * Get all the slots in the parking lot with drivers of the given age
	 * 
	 * @param age Driver's age
	 * @return All the slots with the driver's age
	 * @throws ParkingException
	 */
	public String getSlotsForDriverAge(int age) throws ParkingException;

	/**
	 * Get the slot no of the vehicle parked with the given registration no
	 * 
	 * @param registrationNumber
	 * @return Slot no of the parked vehicle
	 * @throws ParkingException
	 */
	public String getSlotNumberForVehicle(String registrationNumber) throws ParkingException;

	/**
	 * Get all the vehicles registration no with the given age
	 * 
	 * @param age
	 * @return
	 * @throws ParkingException
	 */
	public String getVehicleForReigistrationNumberForDriverAge(int age) throws ParkingException;

}
