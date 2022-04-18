package com.dev.proairline.dblayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.IModel;
import com.dev.proairline.model.SEAT_COLUMN;
import com.dev.proairline.model.SeatModel;
import com.dev.proairline.preferences.flight.IFlightPreference;

public class SeatAdmin extends Admin {
	private static final Logger logger = LogManager.getLogger(SeatAdmin.class);

	public SeatAdmin(DatabaseConnection c) {
		connect = c;
	}

	public void admin() {

	}

	public boolean addModel(IModel iModel) {
		if (iModel instanceof SeatModel) {
			SeatModel seatModel = (SeatModel) iModel;
			ResultSet rs;
			String sql = "SELECT sID from Seat";
			rs = connect.execute(sql);
			try {
				while (rs.next()) {
					String id = rs.getString("sID");
					if (id.equalsIgnoreCase(seatModel.getSeatID()))
						return false;
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}

			if (seatModel.getSeatID().equals("") || seatModel.getSeatColumn().getName().equals("")
					|| seatModel.getSeatNumber().equals("") || seatModel.getFlightNumber().equals(""))
				return false;
			if (seatModel.getSeatID().length() > 2)
				return false;
			sql = "INSERT INTO Seat(sID, seatColumn, seatNo, booked, flghtNumber) " + "VALUES(\""
					+ seatModel.getSeatID() + "\", \"" + seatModel.getSeatColumn().getName() + "\", "
					+ seatModel.getSeatNumber() + ", " + seatModel.isBooked() + ", " + seatModel.getFlightNumber()
					+ ")";
			try {
				System.out.println(sql);
				connect.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return false;
			}
		}
		return false;
	}

	public SeatModel getSeat(SeatModel seatModel) {
		ResultSet rs;
		SeatModel resultSeatModel = null;
		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Seat";
		// if any attribute is specified, append WHERE to sql
		if (seatModel.getSeatID().compareTo("") + seatModel.getSeatNumber().compareTo("")
				+ seatModel.getSeatColumn().getName().compareTo("") + seatModel.getFlightNumber().compareTo("") != 0) {
			sql = sql + " WHERE sID = " + seatModel.getSeatID() + " AND seatColumn = "
					+ seatModel.getSeatColumn().getName() + " AND seatNo = " + seatModel.getSeatNumber()
					+ " AND flightNumber = " + seatModel.getFlightNumber();

			logger.info("Current executing SQL Statement " + sql);
			rs = connect.execute(sql);
			try {
				while (rs.next()) {
					resultSeatModel = new SeatModel(rs.getString("sID"), rs.getString("seatNo"),
							rs.getString("flightNumber"), rs.getString("seatColumn"), rs.getBoolean("booked"));
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return resultSeatModel;
	}

	public List<SeatModel> getAvailableSeats(FlightModel flightModel) {
		ResultSet rs;
		List<SeatModel> resultSeatModelList = new ArrayList<SeatModel>();
		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Seat";
		sql = sql + " WHERE flightNumber = "+ flightModel.getFlightNumber()+" AND booked = false";

		logger.info("Current executing SQL Statement " + sql);
		rs = connect.execute(sql);
		try {
			while (rs.next()) {
				SeatModel resultSeatModel = new SeatModel(rs.getString("sID"), rs.getString("seatNo"),
						rs.getString("flightNumber"), rs.getString("seatColumn"), rs.getBoolean("booked"));
				resultSeatModelList.add(resultSeatModel);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return resultSeatModelList;
	}

	public List<SeatModel> getAllOccupiedSeats(FlightModel flightModel) {
		ResultSet rs;
		List<SeatModel> resultSeatModelList = new ArrayList<SeatModel>();
		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Seat";
		sql = sql + " WHERE flightNumber = " + flightModel + " AND booked = true";

		logger.info("Current executing SQL Statement " + sql);
		rs = connect.execute(sql);
		try {
			while (rs.next()) {
				SeatModel resultSeatModel = new SeatModel(rs.getString("sID"), rs.getString("seatNo"),
						rs.getString("flightNumber"), rs.getString("seatColumn"), rs.getBoolean("booked"));
				resultSeatModelList.add(resultSeatModel);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return resultSeatModelList;
	}

	public List<SeatModel> getAvailableSeats(FlightModel flightModel, SEAT_COLUMN seatColumn) {
		ResultSet rs;
		List<SeatModel> resultSeatModelList = new ArrayList<SeatModel>();
		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Seat";
		sql = sql + " WHERE flightNumber = " + flightModel.getFlightNumber() + " AND booked = false AND seatColumn = " + seatColumn;

		logger.info("Current executing SQL Statement " + sql);
		rs = connect.execute(sql);
		try {
			while (rs.next()) {
				SeatModel resultSeatModel = new SeatModel(rs.getString("sID"), rs.getString("seatNo"),
						rs.getString("flightNumber"), rs.getString("seatColumn"), rs.getBoolean("booked"));
				resultSeatModelList.add(resultSeatModel);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return resultSeatModelList;
	}

	
}
