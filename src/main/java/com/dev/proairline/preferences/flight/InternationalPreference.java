package com.dev.proairline.preferences.flight;

public class InternationalPreference implements IFlightPreference{
	
	private Integer[] seatingOrder;

	public void setSeatingOrder(Integer[] seatingOrder) {

		this.seatingOrder = seatingOrder;
	}

}
