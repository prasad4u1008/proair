package com.dev.proairline.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.IColumn;
import com.dev.proairline.model.SEAT_COLUMN;
import com.dev.proairline.model.SeatModel;
import com.dev.proairline.preferences.flight.IFlightPreference;
import com.dev.proairline.provider.FlightProvider;
import com.dev.proairline.provider.ProAirFactoryProvider;

public class ProAirBookingController implements IProAirBookingController {

	private static Logger logger = LogManager.getLogger(ProAirFactoryProvider.class);
	private static ProAirBookingController _instance;
	private FlightProvider flightProvider;
	private SeatBookingController sbc;
	private FlightController fc;
	
	private ProAirBookingController() {
		logger.debug("Made the Constructor private for the single ton");
		flightProvider = new FlightProvider();
		fc = new FlightController();
		sbc = new SeatBookingController(flightProvider);
	}

	public static synchronized ProAirBookingController getinstance() {
		if (_instance == null) {
			logger.debug("Creating instance for the first time");
			_instance = new ProAirBookingController();
		}
		return _instance;
	}

	public void createFlight(FlightModel flightModel) {
		fc.createFilght(flightModel);
	}
	
	public void printBookedSeats(FlightModel flightModel) {
		logger.info("Printing the Currently booked Seats");
		for (SeatModel sm : flightProvider.getSeatAdmin().getAllOccupiedSeats(flightModel)) {
			logger.info(sm.toString());
		}
	}

	public synchronized List<SeatModel> bookSeats(FlightModel flightModel, int numberOfSeats) {
		logger.info("Inside the Book Seats for the booking of seats " + numberOfSeats);
		List<SeatModel> bookedSeats = sbc.bookSeats(flightModel, numberOfSeats);
		return bookedSeats;
	}

	
	private int getTotalAvailableNoSeats(FlightModel flightModel) {
		int totalNumberOfSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel).size();
		logger.info("The Total Number of Available seats " + totalNumberOfSeats);
		return totalNumberOfSeats;
	}

	public List<String> getTotalAvailableNoSeatsColumnWise(FlightModel flightModel) {
		List<String> result = new ArrayList<String>();
		result.add("Left Column : " );
		for (SeatModel sm : flightProvider.getSeatAdmin().getAvailableSeats(flightModel,SEAT_COLUMN.SEAT_COLUMN_LEFT)) {
			result.add(sm.toString());
		}
		result.add("Middle Column : " );
		for (SeatModel sm : flightProvider.getSeatAdmin().getAvailableSeats(flightModel,SEAT_COLUMN.SEAT_COLUMN_LEFT)) {
			result.add(sm.toString());
		}
		result.add("Right Column : " );
		for (SeatModel sm : flightProvider.getSeatAdmin().getAvailableSeats(flightModel,SEAT_COLUMN.SEAT_COLUMN_LEFT)) {
			result.add(sm.toString());
		}
		logger.info("Total Number of Seats Column Wise " + result);
		return result;
	}

	public int getLeftColumnAvailableNoSeats(FlightModel flightModel) {
		int totalLeftColumnAvailableNoOfSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,SEAT_COLUMN.SEAT_COLUMN_LEFT).size();
		logger.info("The Total number of Left Column seats available " + totalLeftColumnAvailableNoOfSeats);
		return totalLeftColumnAvailableNoOfSeats;
	}

	public int getMiddleColumnAvailableNoSeats(FlightModel flightModel) {
		int totalMiddleColumnAvailableNoOfSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,SEAT_COLUMN.SEAT_COLUMN_MIDDLE).size();
		logger.info("The Total number of Middle Column seats available " + totalMiddleColumnAvailableNoOfSeats);
		return totalMiddleColumnAvailableNoOfSeats;	
	}

	public int getRightColumnAvailableNoSeats(FlightModel flightModel) {
		int totalRightColumnAvailableNoOfSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,SEAT_COLUMN.SEAT_COLUMN_RIGHT).size();
		logger.info("The Total number of Right Column seats available " + totalRightColumnAvailableNoOfSeats);
		return totalRightColumnAvailableNoOfSeats;
	}

	

}
