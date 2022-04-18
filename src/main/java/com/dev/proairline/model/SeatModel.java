package com.dev.proairline.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeatModel implements IModel {
	private static Logger logger = LogManager.getLogger(SeatModel.class);

	private String seatID;
	private String seatNumber;
	private String flightNumber;
	private SEAT_COLUMN seatColumn;
	private boolean isBooked;

	public SeatModel(String seatId, String seatNumber, String flightNumber, String seatColumn, boolean isBooked) {
		logger.debug("Inside the Constructor of Seat Model");
		this.seatID = seatId;
		this.seatNumber = seatNumber;
		this.flightNumber = flightNumber;
		this.seatColumn = SEAT_COLUMN.find(seatColumn);
		logger.debug("seatID : " + seatID + " seatNumber : " + seatNumber + " flightNumber : " + flightNumber
				+ " seatColumn :" + this.seatColumn.getName() + " isBooked : " + isBooked);
	}

	public String getSeatID() {
		return seatID;
	}

	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public SEAT_COLUMN getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(SEAT_COLUMN seatColumn) {
		this.seatColumn = seatColumn;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean b) {
		this.isBooked = b;
		
	}

	@Override
	public String toString() {
		return "SeatModel [seatID=" + seatID + ", seatNumber=" + seatNumber + ", flightNumber=" + flightNumber
				+ ", seatColumn=" + seatColumn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((seatColumn == null) ? 0 : seatColumn.hashCode());
		result = prime * result + ((seatID == null) ? 0 : seatID.hashCode());
		result = prime * result + ((seatNumber == null) ? 0 : seatNumber.hashCode());
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
		SeatModel other = (SeatModel) obj;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (seatColumn != other.seatColumn)
			return false;
		if (seatID == null) {
			if (other.seatID != null)
				return false;
		} else if (!seatID.equals(other.seatID))
			return false;
		if (seatNumber == null) {
			if (other.seatNumber != null)
				return false;
		} else if (!seatNumber.equals(other.seatNumber))
			return false;
		return true;
	}
	
	
}
