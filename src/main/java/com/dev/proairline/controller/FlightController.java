package com.dev.proairline.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.model.FlightModel;
import com.dev.proairline.provider.FlightProvider;

public class FlightController {

	private static final Logger logger = LogManager.getLogger(FlightController.class);
	
	private FlightProvider flightProvider;
	
	public FlightController() {
		this.flightProvider = new FlightProvider();
	}
	
	public synchronized void createFilght(FlightModel flightModel) {
		flightProvider.createFlight(flightModel);
	}
}
