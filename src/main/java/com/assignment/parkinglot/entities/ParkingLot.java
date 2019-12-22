package com.assignment.parkinglot.entities;

import com.assignment.parkinglot.exceptions.ParkingException;

/**
 * Represents a Parking Lot.
 * 
 * @author Nitesh
 *
 */
public interface ParkingLot {
	/**
	 * Park a vehicle to the parking lot
	 * 
	 * @param vehicle
	 * @return The slot no at which vehicle was parked
	 * @throws ParkingException
	 */
	public Slot add(Vehicle vehicle) throws ParkingException;

	/**
	 * Get the first available slot for parking.
	 * 
	 * @return Returns the first available slot from entry point.
	 * @throws ParkingException
	 */
	public Slot getAvailableSlot() throws ParkingException;

	/**
	 * Unparks the vehicle from the given slot
	 * 
	 * @param slot
	 * @return the vehicle which was unparked
	 */
	public Vehicle freeSlot(Slot slot);

}
