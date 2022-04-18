package com.dev.proairline.dblayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dev.proairline.model.IModel;
import com.dev.proairline.model.SeatModel;


public abstract class Admin {

	DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"1234");
	
	public abstract void admin();
	
	 public int getRowNum(ResultSet rs) {
 		int num = 0;

 		try {
 			rs.last();
 			num = rs.getRow();
 			rs.beforeFirst();
 		} catch (SQLException e) {
 			//TODO Log the connection exception.
 			
 		}
 		return num;

 	}

	public abstract boolean addModel(IModel seatModel);
	
	
}
