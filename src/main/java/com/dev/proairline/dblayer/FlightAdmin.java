package com.dev.proairline.dblayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.IModel;
import com.dev.proairline.model.SeatModel;
import com.dev.proairline.preferences.flight.IFlightPreference;

public class FlightAdmin extends Admin {
	private static final Logger logger = LogManager.getLogger(FlightAdmin.class);

	public FlightAdmin(DatabaseConnection c) {
		connect = c;
	}

	public void admin() {

	}

	public boolean deletePlane(String flightNumber) throws SQLException {
		if (flightNumber.equals(""))
			return false;
		ResultSet rs;

		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Flight WHERE flightNumber = " + flightNumber + "";
		logger.info("Current Executing SQL statement : " + sql);
		rs = connect.execute(sql);

		if (!rs.next()) {
			return false;
		}

		if (flightNumber.compareTo("") != 0)
			sql = "DELETE FROM Flight WHERE planeID = \'" + flightNumber + "\'";

		try {
			connect.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean addModel(IModel iModel) {
		if (iModel instanceof FlightModel) {
			FlightModel flightModel = (FlightModel) iModel;
			if (flightModel.getFlightType().getType().equals("") || flightModel.getFlightNumber().equals(""))
				return false;

			String sql = "INSERT INTO Flight VALUES(" + flightModel.getFlightNumber() + ", "
					+ flightModel.getFlightType().getType() + ")";

			String sqlCheck = "SELECT * FROM Flight WHERE flightNumber = " + flightModel.getFlightNumber();
			logger.info("Current Executing SQL statement : " + sqlCheck);
			ResultSet rs;
			rs = connect.execute("SELECT * FROM FLIGHT WHERE flightNumber = " + flightModel.getFlightNumber());
			try {
				if (getRowNum(rs) == 0) {
					connect.executeUpdate(sql);
					return true;

				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return false;
	}

	public List<FlightModel> getFlights(FlightModel flightModel) {

		ResultSet rs;
		List<FlightModel> flightModelList = new ArrayList<FlightModel>();

		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Flight";

		// if any attribute is specified, append WHERE to sql
		if (flightModel.getFlightNumber().compareTo("") + flightModel.getFlightType().getType().compareTo("") != 0) {
			sql = sql + " WHERE ";

			// append specified attribute to sql
			if (flightModel.getFlightNumber().compareTo("") != 0)
				sql = sql + "flightNumber = " + flightModel.getFlightNumber() + " AND ";

			if (flightModel.getFlightType().getType().compareTo("") != 0)
				sql = sql + "flightType = " + flightModel.getFlightNumber();

			sql = sql.substring(0, sql.length() - 4);
			System.out.println(sql);
			rs = connect.execute(sql);
			try {
				while (rs.next()) {
					FlightModel fm = new FlightModel(rs.getString("flightNumber"), rs.getString("flightType"),
							(IFlightPreference) (rs.getObject("flightPreference")));
					flightModelList.add(fm);
				}

			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return flightModelList;
	}

}
