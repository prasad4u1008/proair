package com.dev.proairline.preferences.seatcolumn;

public interface IPreference {
	
	void setMaxRowSize(int maxRowSize);
	
	void setMaxSeatingCapcity(int maxSize);
	
	void setRowNumbers(Integer[] nums);

	int getMaxRowSize();
	
	Integer[] getRowNumbers();

	int getMaxSeatingCapcity();
	
}
