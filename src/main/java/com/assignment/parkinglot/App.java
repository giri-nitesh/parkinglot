package com.assignment.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.log4j.Logger;

import com.assignment.parkinglot.service.CommandExecutorImpl;
import com.assignment.parkinglot.service.ParkingServiceImpl;
import com.assignment.parkinglot.utilities.AppConstants;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		final Logger LOGGER = Logger.getLogger(App.class);
		ParkingServiceImpl paServiceImpl = new ParkingServiceImpl();
		CommandExecutorImpl commandExecutor = new CommandExecutorImpl(paServiceImpl);
		BufferedReader bufferReader = null;
		String input = null;
		try {
			LOGGER.info("\n\n");
			LOGGER.info("===================================================================");
			LOGGER.info("===================      SQUAD PARKING LOT     ====================");
			LOGGER.info("===================================================================");
			String usageInfo = printUsageString();
			if (args.length < 1) {
				LOGGER.info("Invalid usage of the command");
				LOGGER.info(usageInfo);
				LOGGER.info(printUsageString());
				System.out.println("Invalid usage of the command");
				System.out.println(printUsageString());
				return;
			} else {
				String fileName = args[0];
				File inputFile = new File(AppConstants.INPUT_FILE_FOLDER_PATH + fileName);
				bufferReader = new BufferedReader(new FileReader(inputFile));
				int lineNo = 1;
				while ((input = bufferReader.readLine()) != null) {
					input = input.trim();
					try {
						commandExecutor.execute(input);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					lineNo++;
				}
			}
		} catch (Exception e) {

		}
	}

	private static String printUsageString() {
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append(
				"--------------We currently support following commands, please try with any of the below: -----------------------")
				.append("\n");
		buffer = buffer.append("A) For creating parking lot of size n               ---> create_parking_lot {capacity}")
				.append("\n");
		buffer = buffer.append(
				"B) To park a car                                    ---> park {car_number} driver_age {driver_age}")
				.append("\n");
		buffer = buffer.append("C) Unpark car from parking                  ---> leave {slot_number}").append("\n");
		buffer = buffer.append(
				"E) Get cars registration no for the given age---> vehicle_registration_number_for_driver_of_age  {driver_age}")
				.append("\n");
		buffer = buffer.append(
				"F) Get slot no for the given age of driver         ---> slot_numbers_for_driver_of_age {driver_age}")
				.append("\n");
		buffer = buffer.append(
				"G) Get slot no for the given car number         ---> slot_number_for_car_with_number {car_reg_number}")
				.append("\n");
		return buffer.toString();
	}
}
