package com.assignment.parkinglot.service;

import org.apache.log4j.Logger;

import com.assignment.parkinglot.entities.Car;
import com.assignment.parkinglot.entities.Slot;
import com.assignment.parkinglot.entities.Vehicle;
import com.assignment.parkinglot.entities.VehicleDriver;
import com.assignment.parkinglot.exceptions.ParkingException;
import com.assignment.parkinglot.utilities.AppConstants.CommandSet;
import com.assignment.parkinglot.utilities.AppConstants.VehicleType;

public class CommandExecutorImpl implements CommandExecutor {
	private ParkingServiceImpl parkingServiceImpl;
	final Logger LOGGER = Logger.getLogger(CommandExecutorImpl.class);

	public CommandExecutorImpl(ParkingServiceImpl parkingServiceImpl) {
		super();
		this.parkingServiceImpl = parkingServiceImpl;
	}

	public void setParkingLotService(ParkingServiceImpl paServiceImpl) {
		this.parkingServiceImpl = paServiceImpl;

	}

	public void execute(String commandString) throws ParkingException {
		LOGGER.info("Executor recieved the input string:: " + commandString);
		String[] params = commandString.split(" ");
		CommandSet commandName = getCommandType(params[0]);
		LOGGER.info("Command to be executed is: " + commandName.toString());
		switch (commandName) {
		case CREATE_PARKING_LOT:
			Integer capacity = Integer.parseInt(params[1]);
			parkingServiceImpl.createParkingLot(capacity);
			System.out.println("Created parking of " + capacity + " slots");
			break;
		case PARK:
			String registrationNo = params[1];
			Integer driverAge = Integer.parseInt(params[3]);
			Vehicle vehicle = new Car(registrationNo, VehicleType.CAR, new VehicleDriver(driverAge));
			Slot slot = parkingServiceImpl.parkVehicle(vehicle);
			if (slot == null) {
				System.out
						.println("Parking lot is full. You can try another parking lot or wait until a lot is freed.");
				throw new ParkingException("No space was found in Parking Lot");

			}

			System.out.println("Car with vehicle registration number " + registrationNo
					+ " has been parked at slot number " + slot.getSlotNo());
			break;
		case LEAVE:
			Integer parkingSlot = Integer.parseInt(params[1]);
			Vehicle v = parkingServiceImpl.unparkVehicle(parkingSlot);
			if (v == null)
				throw new ParkingException("No vehicle was found on the given slot");
			System.out.println("Slot number " + parkingSlot + " vacated, the car with vehicle registration number"
					+ v.getVehicleNumber() + " left the space, the driver of the car was of age "
					+ v.getDriver().getAge());

			break;
		case SLOT_NUMBERS_FOR_DRIVERS_OF_AGE:
			Integer age = Integer.parseInt(params[1]);
			String res = parkingServiceImpl.getSlotsForDriverAge(age);
			System.out.println(res);
			break;
		case SLOT_NUMBER_FOR_CAR_WITH_NUMBER:
			String regNo = params[1];
			String slotNo = parkingServiceImpl.getSlotNumberForVehicle(regNo);
			if (slotNo == null)
				System.out.println("There is no vehicle with registration no " + regNo + " in parking lot.");
			System.out.println(
					"Car with vehicle registration number " + regNo + " has been parked at slot number " + slotNo);
			break;

		case VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE:
			int driveAge = Integer.parseInt(params[1]);
			String result = parkingServiceImpl.getVehicleForReigistrationNumberForDriverAge(driveAge);
			System.out.println(result);
		default:
			System.out.println("Invalid command used");
			break;
		}
	}

	private CommandSet getCommandType(String command) {
		command = command.toLowerCase();
		if (command.equals("create_parking_lot"))
			return CommandSet.CREATE_PARKING_LOT;
		else if (command.equals("park"))
			return CommandSet.PARK;
		else if (command.equals("leave"))
			return CommandSet.LEAVE;
		else if (command.equals("slot_numbers_for_driver_of_age"))
			return CommandSet.SLOT_NUMBERS_FOR_DRIVERS_OF_AGE;
		else if (command.equals("slot_number_for_car_with_number"))
			return CommandSet.SLOT_NUMBER_FOR_CAR_WITH_NUMBER;
		else if (command.equals("vehicle_registration_number_for_driver_of_age"))
			return CommandSet.VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE;

		return CommandSet.INVALID_COMMAND;

	}

}
