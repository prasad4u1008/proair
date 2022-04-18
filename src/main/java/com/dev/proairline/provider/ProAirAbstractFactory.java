package com.dev.proairline.provider;

import java.util.ArrayList;
import java.util.List;

import com.dev.proairline.model.IColumn;
import com.dev.proairline.preferences.flight.DomesticPreference;
import com.dev.proairline.preferences.flight.IFlightPreference;
import com.dev.proairline.preferences.seatcolumn.IPreference;
import com.dev.proairline.preferences.seatcolumn.LeftColumnPrefernce;
import com.dev.proairline.preferences.seatcolumn.MiddleColumnPreference;
import com.dev.proairline.preferences.seatcolumn.RightColumnPreference;

public abstract class ProAirAbstractFactory {
	
	private LeftColumnPrefernce lcf;
	private MiddleColumnPreference mcf;
	private RightColumnPreference rcf;
	
	public ProAirAbstractFactory() {
		
	}

	
	public List<IPreference> getProvider(IFlightPreference preference) {
		List<IPreference> result = new ArrayList<IPreference>();
		if(preference instanceof DomesticPreference) {
		    lcf = new LeftColumnPrefernce();
		    lcf.setMaxRowSize(2);
			lcf.setMaxSeatingCapcity(lcf.getMaxRowSize() * IColumn.COLUMN_NAMES.length);
			lcf.setRowNumbers(new Integer[] {1,2});
			result.add(lcf);
			
		    mcf = new MiddleColumnPreference();
		    mcf.setMaxRowSize(3);
			mcf.setMaxSeatingCapcity(mcf.getMaxRowSize() * IColumn.COLUMN_NAMES.length);
			mcf.setRowNumbers(new Integer[] {3,4,5});
			result.add(mcf);
			
			rcf = new RightColumnPreference();
			rcf.setMaxRowSize(2);
			rcf.setMaxSeatingCapcity(rcf.getMaxRowSize() * IColumn.COLUMN_NAMES.length);
			rcf.setRowNumbers(new Integer[] {5,6});
			result.add(rcf);
		} else {
			lcf = new LeftColumnPrefernce();
			lcf.setMaxRowSize(3);
			lcf.setMaxSeatingCapcity(lcf.getMaxRowSize() * IColumn.COLUMN_NAMES.length);
			lcf.setRowNumbers(new Integer[] {1,2,3});
			result.add(lcf);
			
			mcf = new MiddleColumnPreference();
			mcf.setMaxRowSize(4);
			mcf.setMaxSeatingCapcity(mcf.getMaxRowSize() * IColumn.COLUMN_NAMES.length);
			mcf.setRowNumbers(new Integer[] {4,5,6});
			result.add(mcf);
			
			rcf = new RightColumnPreference();
			rcf.setMaxRowSize(3);
			rcf.setMaxSeatingCapcity(rcf.getMaxRowSize() * IColumn.COLUMN_NAMES.length);
			rcf.setRowNumbers(new Integer[] {7,8});
			result.add(rcf);
		}
		return result;
	}
	
}
