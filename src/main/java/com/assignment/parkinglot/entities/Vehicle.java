package com.assignment.parkinglot.entities;

import com.assignment.parkinglot.utilities.AppConstants.VehicleType;

public abstract class Vehicle {
	private String vehicleNumber;
	private final VehicleType type;
	private VehicleDriver driver;

	public Vehicle(String vehicleNumber, VehicleType type, VehicleDriver driver) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.type = type;
		this.driver = driver;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public VehicleType getType() {
		return type;
	}

	public VehicleDriver getDriver() {
		return driver;
	}

	public void setDriver(VehicleDriver driver) {
		this.driver = driver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicleNumber == null) ? 0 : vehicleNumber.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!vehicleNumber.equals(other.vehicleNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleNumber=" + vehicleNumber + ", type=" + type + ", driver=" + driver + "]";
	}

}
