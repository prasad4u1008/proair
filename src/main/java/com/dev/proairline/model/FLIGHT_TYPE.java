package com.dev.proairline.model;

public enum FLIGHT_TYPE {

	FLIGHT_DOMESTIC("domestic"),FLIGHT_INTERNATIONAL("international");
	
	private String type;

	FLIGHT_TYPE(String type){
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public static FLIGHT_TYPE find(String flightType) {
		for (FLIGHT_TYPE type : FLIGHT_TYPE.values()) {
			if(type.type.equals(flightType)) {
				return type;
			}
		}
		return null;
	}
}
