package com.dev.proairline.preferences.seatcolumn;

public class RightColumnPreference implements IPreference{
	
	private int maxRowSize;
	private int maxSeatingCapacity;
	private Integer[] rowNumbers;
	
	
	public void setMaxSeatingCapcity(int maxSize) {
		this.maxSeatingCapacity = maxSize;
	}

	public void setRowNumbers(Integer[] nums) {
		this.rowNumbers = nums;
	}

	public int getMaxRowSize() {
		return this.maxRowSize;
	}

	public Integer[] getRowNumbers() {
		return this.rowNumbers;
	}

	public void setMaxRowSize(int maxRowSize) {
		this.maxRowSize = maxRowSize;
		
	}
	
	public int getMaxSeatingCapcity() {
		return this.maxSeatingCapacity;
	}

	
	

}
