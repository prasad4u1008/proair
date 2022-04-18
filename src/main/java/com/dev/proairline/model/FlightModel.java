package com.dev.proairline.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.preferences.flight.IFlightPreference;

public class FlightModel implements IModel {

	private static final Logger logger = LogManager.getLogger(FlightModel.class);

	private String flightNumber;
	private FLIGHT_TYPE flightType;
	private IFlightPreference flightPreference;

	public FlightModel(String flightNumber, String flightType, IFlightPreference flightPreference) {
		logger.debug("Inside the Constructor Flight Model");
		this.flightNumber = flightNumber;
		this.flightType = FLIGHT_TYPE.find(flightType);
		this.flightPreference = flightPreference;
		logger.debug("Flight Number :" + flightNumber + " Flight Type : " + this.flightType.getType()
				+ " Flight Preference " + flightPreference);
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public FLIGHT_TYPE getFlightType() {
		return flightType;
	}

	public void setFlightType(FLIGHT_TYPE flightType) {
		this.flightType = flightType;
	}

	public IFlightPreference getFlightPreference() {
		return flightPreference;
	}

	public void setFlightPreference(IFlightPreference flightPreference) {
		this.flightPreference = flightPreference;
	}

	@Override
	public String toString() {
		return "FlightModel [flightNumber=" + flightNumber + ", flightType=" + flightType + ", flightPreference="
				+ flightPreference + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((flightType == null) ? 0 : flightType.hashCode());
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
		FlightModel other = (FlightModel) obj;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (flightType != other.flightType)
			return false;
		return true;
	}
	
	

}
