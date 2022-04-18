package com.dev.proairline;

import java.util.List;

import com.dev.proairline.controller.ProAirBookingController;
import com.dev.proairline.model.FLIGHT_TYPE;
import com.dev.proairline.model.FlightModel;
import com.dev.proairline.model.IColumn;
import com.dev.proairline.model.LeftColumn;
import com.dev.proairline.model.MiddleColumn;
import com.dev.proairline.model.RightColumn;
import com.dev.proairline.preferences.flight.DomesticPreference;
import com.dev.proairline.preferences.flight.IFlightPreference;
import com.dev.proairline.preferences.flight.InternationalPreference;
import com.dev.proairline.provider.ProAirFactoryProvider;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
//        System.err.println("------------------Middle Column-----------------------");
//        MiddleColumn md = new MiddleColumn(3,new Integer[]{3,4,5});
//        System.err.println(md.getAvailableSeats());
//        md.createSeats(42);
//        System.err.println(md.getCurrentBookedSeats());
//        if(!md.createSeats(4)) {
//        	System.err.println("Middle Column is Full");
//        	System.err.println(md.getAvailableSeats());
//        }
//        
//        System.err.println("------------------Left Column-----------------------");
//        LeftColumn lc = new LeftColumn(3,new Integer[]{1,2});
//        System.err.println(lc.getAvailableSeats());
//        lc.createSeats(28);
//        System.err.println(lc.getCurrentBookedSeats());
//        if(!lc.createSeats(4)) {
//        	System.err.println("Lift Column is Full");
//        	System.err.println(lc.getAvailableSeats());
//        }
//        System.err.println("------------------Right Column-----------------------");
//        
//        RightColumn rc = new RightColumn(3,new Integer[]{6,7});
//        System.err.println(rc.getAvailableSeats());
//        rc.createSeats(28);
//        System.err.println(rc.getCurrentBookedSeats());
//        if(!rc.createSeats(4)) {
//        	System.err.println("Right Column is Full");
//        	System.err.println(rc.getAvailableSeats());
//        }
        
        //----------------------- WITH ABSTRACT METHODS ---------------------------------
//        
//        DomesticPreference preference = new DomesticPreference();
//        ProAirFactoryProvider proAirProvider = new ProAirFactoryProvider();
//        proAirProvider.createFactory(preference);
//        
//        System.err.println("------------------Middle Column-----------------------");
//        MiddleColumn  md = proAirProvider.getMiddleColumn();
//        System.err.println(md.getAvailableSeats());
//        md.createSeats(23);
//        System.err.println(md.getCurrentBookedSeats());
//        if(md.createSeats(4) != 0) {
//        	System.err.println("Middle Column is Full");
//        	System.err.println(md.getAvailableSeats());
//        }
//        
//        System.err.println("------------------Left Column-----------------------");
//        LeftColumn lc = proAirProvider.getLeftColumn();
//        System.err.println(lc.getAvailableSeats());
//        lc.createSeats(12);
//        System.err.println(lc.getCurrentBookedSeats());
//        if(lc.createSeats(4) != 0) {
//        	System.err.println("Lift Column is Full");
//        	System.err.println(lc.getAvailableSeats());
//        }
//        System.err.println("------------------Right Column-----------------------");
//        
//        RightColumn rc = proAirProvider.getRightColumn();
//        System.err.println(rc.getAvailableSeats());
//        rc.createSeats(1);
//        System.err.println(rc.getCurrentBookedSeats());
//        if(rc.createSeats(4) != 0) {
//        	System.err.println("Right Column is Full");
//        	System.err.println(rc.getAvailableSeats());
//        }
        
        //--------------------------------------WITH APP START--------------------------
    	
    	IFlightPreference domesticFlight = new DomesticPreference();
    	FlightModel flightModel = new FlightModel("KLM346", FLIGHT_TYPE.FLIGHT_DOMESTIC.getType(), domesticFlight);
    	
    	ProAirBookingController.getinstance().createFlight(flightModel);
    	ProAirBookingController.getinstance().bookSeats(flightModel, 12);
    	ProAirBookingController.getinstance().printBookedSeats(flightModel);
    	
    	
//        IFlightPreference intPreference = new InternationalPreference();
//        ProAirBookingController.getinstance().createProAirProvider(intPreference);
//        
//        ProAirBookingController.getinstance().bookSeats(5);
//        ProAirBookingController.getinstance().printBookedSeats();
        
        
    }
}
