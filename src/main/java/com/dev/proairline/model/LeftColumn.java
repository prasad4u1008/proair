package com.dev.proairline.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.preferences.seatcolumn.IPreference;

public class LeftColumn implements IColumn {
	private static Logger logger = LogManager.getLogger(LeftColumn.class);
	private static int MAX_ROW_CAPACITY;
	private List<Integer> seatingRow;
	private List<SeatModel> seatingOrder = new ArrayList<SeatModel>();

	private Integer[] rowNumbers;
	private static int currentRowIndex = 0;
	private static int MAX_COLUMN_SEATS = 28;
	private IPreference preference;

	public LeftColumn(IPreference preference) {
		logger.debug("Inside the LeftColumn Constructor");
		this.preference = preference;
		MAX_ROW_CAPACITY = preference.getMaxRowSize();
		logger.info("THE MAX ROW CAPACITY of the LeftColumn " + MAX_ROW_CAPACITY);
		this.rowNumbers = preference.getRowNumbers();
		logger.info("The allocated row numbers for the LeftColumn " + rowNumbers);
		// Base Row created
		logger.info("Base Row for the first booking created");
	}

	public List<SeatModel> getCurrentBookedSeats() {
		List<SeatModel> result = new ArrayList<SeatModel>();
		synchronized (seatingOrder) {
			for (SeatModel seatModel : seatingOrder) {
				if (seatModel.isBooked()) {
					result.add(seatModel);
				}
			}
		}
		return result;
	}

	public IPreference getPreference() {
		return this.preference;
	}

	public List<SeatModel> getAllSeats() {
		return seatingOrder;
	}

	public void initAllSeats(String flightNumber) {
		for (String column : COLUMN_NAMES) {
			for (int i = 0; i < this.MAX_ROW_CAPACITY; i++) {
				String seatId = flightNumber + "-" + column + rowNumbers[i];
				String seatNumber = column + rowNumbers[i];
				String seatColumn = SEAT_COLUMN.SEAT_COLUMN_LEFT.getName();
				boolean isBooked = false;
				SeatModel sm = new SeatModel(seatId, seatNumber, flightNumber, seatColumn, isBooked);
				seatingOrder.add(sm);
			}
		}
	}

	public List<SeatModel> getSeatingOrder() {
		return seatingOrder;
	}

	public List<SeatModel> getCurrentAvailableSeats() {
		List<SeatModel> availableSeats = new ArrayList<SeatModel>();
		for (SeatModel sm : seatingOrder) {
			if (!sm.isBooked()) {
				availableSeats.add(sm);
			}
		}
		return availableSeats;
	}

	public int getAvailableNoOfSeats() {
		return MAX_COLUMN_SEATS - getCurrentBookedSeats().size();
	}

	public void updateSeatingOrder(SeatModel seatModel) {
		for (Iterator<SeatModel> it = seatingOrder.iterator(); it.hasNext();) {
			SeatModel sm = it.next();
			if (sm.equals(seatModel)) {
				it.remove();
			}
			seatingOrder.add(seatModel);
		}
	}
}
