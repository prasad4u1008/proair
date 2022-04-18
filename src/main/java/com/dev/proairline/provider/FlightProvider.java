package com.dev.proairline.provider;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.dblayer.Admin;
import com.dev.proairline.dblayer.DatabaseConnection;
import com.dev.proairline.dblayer.FlightAdmin;
import com.dev.proairline.dblayer.SeatAdmin;
import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.IColumn;
import com.dev.proairline.model.IFlight;
import com.dev.proairline.model.IModel;
import com.dev.proairline.model.SeatModel;

public class FlightProvider implements IFlight {
	private static final Logger logger = LogManager.getLogger(FlightProvider.class);

	private ProAirFactoryProvider proAirFacProvider;
	private SeatAdmin dbSeatAdmin;

	private DatabaseConnection connection;

	private FlightAdmin dbFlightAdmin;

	public FlightProvider() {
		connection = new DatabaseConnection("jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
				"1234");
		dbSeatAdmin = new SeatAdmin(connection);
		dbFlightAdmin = new FlightAdmin(connection);
		
		proAirFacProvider = new ProAirFactoryProvider();
		
	}

	public void createFlight(FlightModel flightModel) {
		dbFlightAdmin.addModel(flightModel);
		initFlightSeats(flightModel);
	}
	
	public void initFlightSeats(FlightModel flightModel) {
		List<IColumn> flightColumns = proAirFacProvider.createFactory(flightModel.getFlightPreference());
		for (IColumn iColumn : flightColumns) {
			iColumn.initAllSeats(flightModel.getFlightNumber());
		}
		// ADD to database the init seats
		for (IColumn iColumn : flightColumns) {
			for (IModel sm : iColumn.getSeatingOrder()) {
				dbSeatAdmin.addModel(sm);
			}
		}
	}
	
	public ProAirFactoryProvider getProAirAbsFactory() {
		return proAirFacProvider;
	}

	public SeatAdmin getSeatAdmin() {
		return dbSeatAdmin;
	}

}
