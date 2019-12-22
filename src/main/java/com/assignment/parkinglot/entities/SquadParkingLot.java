package com.assignment.parkinglot.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.assignment.parkinglot.exceptions.ParkingException;
import com.assignment.parkinglot.utilities.AppConstants.ParkingSlotAvailability;

public class SquadParkingLot implements ParkingLot {

	private Map<Slot, Vehicle> parkingSlotToVehicleMap;
	private List<Slot> parkingSlots;
	final Logger LOGGER = Logger.getLogger(SquadParkingLot.class);

	public SquadParkingLot(Map<Slot, Vehicle> parkingSlotToVehicleMap, List<Slot> parkingSlots) {
		super();
		this.parkingSlotToVehicleMap = parkingSlotToVehicleMap;
		this.parkingSlots = parkingSlots;
	}

	public Slot add(Vehicle vehicle) throws ParkingException {
		LOGGER.info("Finding available slot for parking...");
		Slot slot = getAvailableSlot();
		if (slot == null)
			return null;
		LOGGER.info("Found slot for parking " + slot);
		if (slot.getAvailability() == ParkingSlotAvailability.FREE) {
			parkingSlotToVehicleMap.put(slot, vehicle);
			changeStatusOfSlotToBusy(slot);
		}
		LOGGER.info("Parked vehicle: " + vehicle + "at slot " + slot);
		return slot;
	}

	public Slot getAvailableSlot() throws ParkingException {
		int freeSlot = -1;
		for (int slot = 0; slot < parkingSlots.size(); slot++) {
			if (parkingSlots.get(slot).getAvailability() == ParkingSlotAvailability.FREE) {
				freeSlot = slot;
				break;
			}
		}
		if (freeSlot == -1)
			return null;
		return parkingSlots.get(freeSlot);
	}

	public Vehicle freeSlot(Slot slot) {
		LOGGER.info("Freing slot " + slot);
		Vehicle vehicle = null;
		vehicle = parkingSlotToVehicleMap.get(slot);
		parkingSlotToVehicleMap.remove(slot);
		changeStatusOfSlotToFree(slot);
		LOGGER.info("Slot no " + slot.getSlotNo() + " was freed");
		return vehicle;

	}

	public int getSlotNumberForVehicle(String registrationNumber) {
		LOGGER.info("Searching slot no for a vehicle with registration no: " + registrationNumber);
		Iterator<Entry<Slot, Vehicle>> itr = parkingSlotToVehicleMap.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Slot, Vehicle> entry = itr.next();
			Vehicle vehicle = entry.getValue();
			if (vehicle.getVehicleNumber().equals(registrationNumber))
				return entry.getKey().getSlotNo();
		}
		LOGGER.info("No slot was found for the given vehicle");
		return -1;
	}

	public List<Slot> getSlotsForDriverAge(int age) {
		List<Slot> slots = new ArrayList<Slot>();
		for (Slot s : parkingSlots) {
			if (s.getAvailability() == ParkingSlotAvailability.BUSY) {
				Vehicle vehicle = parkingSlotToVehicleMap.get(s);
				if (vehicle.getDriver().getAge() == age)
					slots.add(s);
			}
		}
		return slots;
	}

	public List<Vehicle> getVehicleForReigistrationNumberForDriverAge(int age) {
		List<Vehicle> list = new ArrayList<Vehicle>();
		Iterator<Entry<Slot, Vehicle>> itr = parkingSlotToVehicleMap.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Slot, Vehicle> entry = itr.next();
			Vehicle vehicle = entry.getValue();
			if (vehicle.getDriver().getAge() == age)
				list.add(vehicle);
		}
		return list;
	}

	private void changeStatusOfSlotToFree(Slot s) {
		for (int slot = 0; slot < parkingSlots.size(); slot++) {
			if (parkingSlots.get(slot).getSlotNo() == s.getSlotNo()) {
				parkingSlots.get(slot).setAvailability(ParkingSlotAvailability.FREE);
			}
		}
	}

	private void changeStatusOfSlotToBusy(Slot s) {
		for (int slot = 0; slot < parkingSlots.size(); slot++) {
			if (parkingSlots.get(slot).getSlotNo() == s.getSlotNo()) {
				parkingSlots.get(slot).setAvailability(ParkingSlotAvailability.BUSY);
			}
		}
	}

}
