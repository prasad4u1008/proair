package com.dev.proairline.model;

public enum SEAT_COLUMN {

	SEAT_COLUMN_LEFT("left"), SEAT_COLUMN_MIDDLE("middle"), SEAT_COLUMN_RIGHT("right");
	
	private String name;
	
	SEAT_COLUMN(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public static SEAT_COLUMN find(String seatColumn) {
		for (SEAT_COLUMN column : SEAT_COLUMN.values()) {
			if(column.name.equals(seatColumn)) {
				return column;
			}
		}
		return null;
	}

}
