package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.restaraunt.util.DBUtil;
import com.restaurant.bean.Table;

public class TableDAO {
	Connection connection = DBUtil.getDBConnection();
	public Table findTable(String tableID) {
	String query="select * from table_tbl where tableid=?";
	try {
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setString(1, tableID);  
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			Table table = new Table(
					rs.getString(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4)
				);
			return table;
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
		
	}
	public List<Table> viewAllTables(){
		String query="select * from table_tbl";
		List<Table> mylist=new ArrayList<>();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Table table = new Table(
						rs.getString(0),
						rs.getInt(1),
						rs.getString(3),
						rs.getString(4)
						);
				mylist.add(table);
				
						
			}
		
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mylist;
		
		
	}
	public boolean insertTable(Table table) {
		String query="Insert into table_tbl values(?,?,?,?)"; 
		try {
			PreparedStatement ps= connection.prepareStatement(query);
			ps.setString(0, table.getTableID());
			ps.setInt(1, table.getCapacity());
			ps.setString(2, table.getLocation());
			ps.setString(3, table.getStatus());
			int rows = ps.executeUpdate();
			if(rows > 0) {
				return true;
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateStatus(String tableID,String status) {
		String query ="update table_tbl set status=? where tableid=?";
		try {
			PreparedStatement ps= connection.prepareStatement(query);
			 ps.setString(1, status);     
		     ps.setString(2, tableID);    
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		}
	
	public boolean deleteTable(String tableID) {
		String query ="delete from table_tbl where tableid=?";
		try {
			PreparedStatement ps= connection.prepareStatement(query);
			int rows = ps.executeUpdate();
			if(rows>0) {
				return true;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	
}


