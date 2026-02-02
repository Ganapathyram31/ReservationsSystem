package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.restaraunt.util.DBUtil;
import com.restaurant.bean.Reservation;

public class ReservationDAO {
	Connection connection = DBUtil.getDBConnection();
	public int generateReservationID() {
		int reservation_id=0;
		String query="SELECT RESERVATION_SEQ.NEXTVAL FROM DUAL";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				 reservation_id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservation_id;

		} 

	public boolean recordReservation(Reservation res) {
	    String query = "INSERT INTO RESERVATION_TBL (reservationID, tableID, customerName, partySize,reservationDate, reservationTime, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
       try {
	  PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, res.getReservationID());
	        ps.setString(2, res.getTableID());
	        ps.setString(3, res.getCustomerName());
	        ps.setInt(4, res.getPartySize());
	        ps.setDate(5, new java.sql.Date(res.getReservationDate().getTime()));
	        ps.setString(6, res.getReservationTime());
	        ps.setString(7, res.getStatus());

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    
	}
       return false;
	}
	public boolean cancelReservation(int reservationID) {
	    String query =
	      "update reservation_tbl set status='CANCELLED' where reservationID=?";
	    try {
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, reservationID);
	        int rows = ps.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

		
	}
	
	

	

		
	
	
	 
	


