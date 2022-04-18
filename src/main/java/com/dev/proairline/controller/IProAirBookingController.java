package com.dev.proairline.controller;

import java.util.List;

import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.SeatModel;
import com.dev.proairline.preferences.flight.IFlightPreference;


public interface IProAirBookingController {
	
	void printBookedSeats(FlightModel flightModel);
	
	List<SeatModel> bookSeats(FlightModel flightModel,int numberOfSeats);
	
	List<String> getTotalAvailableNoSeatsColumnWise(FlightModel flightModel);
	
	int getLeftColumnAvailableNoSeats(FlightModel flightModel);
	
	int getMiddleColumnAvailableNoSeats(FlightModel flightModel);
	
	int getRightColumnAvailableNoSeats(FlightModel flightModel);
	

}
