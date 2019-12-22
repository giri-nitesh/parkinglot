package com.assignment.parkinglot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.assignment.parkinglot.entities.Slot;
import com.assignment.parkinglot.entities.SquadParkingLot;
import com.assignment.parkinglot.entities.Vehicle;
import com.assignment.parkinglot.exceptions.ParkingException;

public class ParkingServiceImpl implements ParkingService {

	private SquadParkingLot parkingLot;
	private Map<Slot, Vehicle> parkingSlotToVehicleMap;
	private List<Slot> parkingSlots;
	final Logger LOGGER = Logger.getLogger(ParkingServiceImpl.class);

	public ParkingServiceImpl() {
		super();
		this.parkingSlotToVehicleMap = new HashMap<Slot, Vehicle>();
		this.parkingSlots = new ArrayList<Slot>();
		this.parkingLot = new SquadParkingLot(parkingSlotToVehicleMap, parkingSlots);
	}

	public void createParkingLot(int capacity) throws ParkingException {
		LOGGER.info("Creating parking lot with capacity " + capacity);
		for (int slot = 0; slot < capacity; slot++) {
			Slot parkingSlot = new Slot(slot + 1);
			parkingSlots.add(parkingSlot);
		}
		LOGGER.info("Created parking lot sucessfully");
	}

	public Slot parkVehicle(Vehicle vehicle) throws ParkingException {
		return parkingLot.add(vehicle);
	}

	public Vehicle unparkVehicle(int slot) throws ParkingException {
		return parkingLot.freeSlot(new Slot(slot));

	}

	public String getSlotsForDriverAge(int age) {
		List<Slot> slots = parkingLot.getSlotsForDriverAge(age);
		if (slots.size() == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Slot s : slots) {
			sb.append(s.getSlotNo()).append(" ");
		}
		return sb.toString();
	}

	public String getSlotNumberForVehicle(String registrationNumber) throws ParkingException {
		Integer slotNo = parkingLot.getSlotNumberForVehicle(registrationNumber);
		if (slotNo == -1) {
			return null;
		}
		return slotNo.toString();
	}

	public String getVehicleForReigistrationNumberForDriverAge(int age) throws ParkingException {
		List<Vehicle> list = parkingLot.getVehicleForReigistrationNumberForDriverAge(age);
		if (list.size() < 1)
			return null;
		StringBuilder sb = new StringBuilder();
		for (Vehicle v : list) {
			sb.append(v.getVehicleNumber()).append(" ");
		}
		return sb.toString();
	}

}
