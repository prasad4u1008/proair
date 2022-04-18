package com.dev.proairline.model;

import java.util.List;
import java.util.Set;

import com.dev.proairline.preferences.seatcolumn.IPreference;

public interface IColumn {
	
	public static String[] COLUMN_NAMES = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N" };
	
	List<SeatModel> getCurrentBookedSeats();
	
	List<SeatModel> getCurrentAvailableSeats();
	
	int getAvailableNoOfSeats();
	
	public IPreference getPreference();

	void initAllSeats(String flightNumber);

	List<SeatModel> getSeatingOrder();
	
	
	
}
