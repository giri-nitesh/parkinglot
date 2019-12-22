package com.assignment.parkinglot.entities;

import com.assignment.parkinglot.utilities.AppConstants.ParkingSlotAvailability;

public class Slot {
	private int slotNo;

	private ParkingSlotAvailability availability;

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public ParkingSlotAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(ParkingSlotAvailability availability) {
		this.availability = availability;
	}

	public Slot(int slotNo) {
		super();
		this.slotNo = slotNo;
		this.availability = ParkingSlotAvailability.FREE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + slotNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		if (slotNo != other.slotNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slot [slotNo=" + slotNo + ", availability=" + availability + "]";
	}

}
