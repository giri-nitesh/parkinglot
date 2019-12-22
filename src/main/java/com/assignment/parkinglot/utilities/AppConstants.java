package com.assignment.parkinglot.utilities;

public class AppConstants {
	public enum VehicleType {
		CAR
	}

	public enum ParkingSlotAvailability {
		BUSY, FREE
	}

	public enum CommandSet {
		CREATE_PARKING_LOT, PARK, LEAVE, SLOT_NUMBERS_FOR_DRIVERS_OF_AGE, SLOT_NUMBER_FOR_CAR_WITH_NUMBER,
		VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE, INVALID_COMMAND
	}

	public static final int PARKING_LOT_CAPACITY = 10;
	public static final String INPUT_FILE_FOLDER_PATH = "src/main/resources/";
}
