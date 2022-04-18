package com.dev.proairline.provider;

import java.util.ArrayList;
import java.util.List;

import com.dev.proairline.model.IColumn;
import com.dev.proairline.model.LeftColumn;
import com.dev.proairline.model.MiddleColumn;
import com.dev.proairline.model.RightColumn;
import com.dev.proairline.preferences.flight.IFlightPreference;
import com.dev.proairline.preferences.seatcolumn.IPreference;
import com.dev.proairline.preferences.seatcolumn.LeftColumnPrefernce;
import com.dev.proairline.preferences.seatcolumn.MiddleColumnPreference;

public class ProAirFactoryProvider extends ProAirAbstractFactory{
	
	private LeftColumn lc;
	private MiddleColumn mc;
	private RightColumn rc;
	private IFlightPreference mainPreference;
	
	public ProAirFactoryProvider() {
		 
	}

	public List<IColumn> createFactory(IFlightPreference preference) {
		List<IColumn> flightColumns = new ArrayList<IColumn>();
		List<IPreference> currentPreference = getProvider(preference);
		for (IPreference iPreference : currentPreference) {
			if(iPreference instanceof LeftColumnPrefernce) {
				lc = new LeftColumn(iPreference);
				flightColumns.add(lc);
			} else if( iPreference instanceof MiddleColumnPreference) {
				mc = new MiddleColumn(iPreference);
				flightColumns.add(mc);
			} else {
				rc = new RightColumn(iPreference);
				flightColumns.add(mc);
			}
		}
		return flightColumns;
	}

	public MiddleColumn getMiddleColumn() {
		return mc;
	}
	
	public RightColumn getRightColumn() {
		return rc;
	}
	
	public LeftColumn getLeftColumn() {
		return lc;
	}

	public IFlightPreference getMainPreferece() {
		return mainPreference;
	}
}
