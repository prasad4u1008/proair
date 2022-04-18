package com.dev.proairline.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.SEAT_COLUMN;
import com.dev.proairline.model.SeatModel;
import com.dev.proairline.provider.FlightProvider;

public class SeatBookingController {

	private static final Logger logger = LogManager.getLogger(SeatBookingController.class);
	private FlightProvider flightProvider;

	public SeatBookingController(FlightProvider flightProvider) {
		this.flightProvider = flightProvider;
	}

	public synchronized List<SeatModel> bookSeats(FlightModel flightModel, int numberOfSeats) {
		List<SeatModel> smList = new ArrayList<SeatModel>();
		if (numberOfSeats <= flightProvider.getSeatAdmin().getAvailableSeats(flightModel).size()) {
			if (numberOfSeats >= flightProvider.getProAirAbsFactory().getMiddleColumn().getPreference()
					.getMaxRowSize()) {
				List<SeatModel> availableMiddelSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,
						SEAT_COLUMN.SEAT_COLUMN_MIDDLE);
				if (availableMiddelSeats.size() > 0 && availableMiddelSeats.size() > numberOfSeats) {
					for (SeatModel seatModel : availableMiddelSeats) {
						seatModel.setBooked(true);
						smList.add(seatModel);
						flightProvider.getProAirAbsFactory().getMiddleColumn().updateSeatingOrder(seatModel);
					}
				}
			} else if (numberOfSeats <= flightProvider.getProAirAbsFactory().getLeftColumn().getPreference()
					.getMaxRowSize()) {
				List<SeatModel> availableLeftSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,
						SEAT_COLUMN.SEAT_COLUMN_LEFT);
				if (availableLeftSeats.size() > 0 && availableLeftSeats.size() > numberOfSeats) {
					for (SeatModel seatModel : availableLeftSeats) {
						seatModel.setBooked(true);
						smList.add(seatModel);
						flightProvider.getProAirAbsFactory().getLeftColumn().updateSeatingOrder(seatModel);
					}
				}
			} else if (numberOfSeats <= flightProvider.getProAirAbsFactory().getRightColumn().getPreference()
					.getMaxRowSize()) {
				List<SeatModel> availableRightSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,
						SEAT_COLUMN.SEAT_COLUMN_RIGHT);
				if (availableRightSeats.size() > 0 && availableRightSeats.size() > numberOfSeats) {
					for (SeatModel seatModel : availableRightSeats) {
						seatModel.setBooked(true);
						smList.add(seatModel);
						flightProvider.getProAirAbsFactory().getRightColumn().updateSeatingOrder(seatModel);
					}
				}
			} else {
				logger.info("No Consicutive seats are available");
				List<SeatModel> availableMiddleSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,
						SEAT_COLUMN.SEAT_COLUMN_MIDDLE);
				List<SeatModel> seatsBooked = bookSeats(flightModel, availableMiddleSeats.size());
				smList.addAll(seatsBooked);

				List<SeatModel> availableLeftSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,
						SEAT_COLUMN.SEAT_COLUMN_LEFT);
				int currentBalanceBooking = numberOfSeats - seatsBooked.size();
				if (currentBalanceBooking > 0) {
					if (currentBalanceBooking <= availableLeftSeats.size()) {
						seatsBooked = bookSeats(flightModel, currentBalanceBooking);
						smList.addAll(seatsBooked);
					} else {
						seatsBooked = bookSeats(flightModel, availableLeftSeats.size());
						smList.addAll(seatsBooked);
					}
				}

				List<SeatModel> availableRightSeats = flightProvider.getSeatAdmin().getAvailableSeats(flightModel,
						SEAT_COLUMN.SEAT_COLUMN_RIGHT);
				currentBalanceBooking = currentBalanceBooking - seatsBooked.size();
				if (currentBalanceBooking > 0) {
					if (currentBalanceBooking <= availableRightSeats.size()) {
						seatsBooked = bookSeats(flightModel, currentBalanceBooking);
						smList.addAll(seatsBooked);
					} else {
						seatsBooked = bookSeats(flightModel, availableRightSeats.size());
						smList.addAll(seatsBooked);
					}
				}
				currentBalanceBooking = currentBalanceBooking - seatsBooked.size();
				if(currentBalanceBooking > 0) {
					logger.error("Seats are not Booked due to unavaialbility : " + currentBalanceBooking);
				}
			}
		} else {
			logger.error("Flight is full no reservation supported");
		}
		return smList;
	}
}
