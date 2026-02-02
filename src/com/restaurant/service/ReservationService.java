package com.restaurant.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.restaurant.bean.Reservation;
import com.restaurant.bean.Table;
import com.restaurant.dao.ReservationDAO;
import com.restaurant.dao.TableDAO;
import com.restaraunt.util.ActiveReservationException;
import com.restaraunt.util.DBUtil;
import com.restaraunt.util.TableUnavailableException;
import com.restaraunt.util.ValidationException;

public class ReservationService {

    Connection connection = DBUtil.getDBConnection();
    TableDAO tableDAO = new TableDAO();
    ReservationDAO reservationDAO = new ReservationDAO();

   
    public Table viewTableDetails(String tableID)
            throws ValidationException {

        if (tableID == null) {
            throw new ValidationException();
        }

        return tableDAO.findTable(tableID);   
    }

  
    public List<Table> viewAllTables() {
        return tableDAO.viewAllTables();
    }

  
    public boolean addNewTable(Table table)
            throws ValidationException {

        if (table.getTableID() == null || table.getCapacity() <= 0) {

            throw new ValidationException();
        }

     
        if (tableDAO.findTable(table.getTableID()) != null) 
            throw new ValidationException();


        return tableDAO.insertTable(table);
    }
    

   
    public boolean removeTable(String tableID)
            throws ValidationException, ActiveReservationException {

        if (tableID == null || tableID.trim().isEmpty()) {
            throw new ValidationException();
        }

       
        return tableDAO.deleteTable(tableID);
    }
    public boolean reserveTable(String tableID, String customerName,
            int partySize, java.util.Date date, String time)
throws ValidationException, TableUnavailableException {

           if (tableID == null || customerName == null ||
            tableID.trim().isEmpty() || customerName.trim().isEmpty() ||
               partySize <= 0 || date == null || time == null) {

                   throw new ValidationException();
                                 }


                  java.sql.Date sqlDate =new java.sql.Date(date.getTime());

                     Table table = tableDAO.findTable(tableID);
                        if (table == null) return false;

                if ("Unavailable".equalsIgnoreCase(table.getStatus())) {
                  throw new TableUnavailableException();
                      }

                       if (partySize > table.getCapacity()) return false;

                        try {
                    connection.setAutoCommit(false);

                             int resId = reservationDAO.generateReservationID();

                Reservation res = new Reservation(resId, tableID, customerName,partySize, sqlDate, time, "ACTIVE");

                      reservationDAO.recordReservation(res);
                           tableDAO.updateStatus(tableID, "Unavailable");

                          connection.commit();
                               return true;

                           } catch (SQLException e) {
                      try {
                              connection.rollback();
                              } catch (SQLException ex) {
                                     ex.printStackTrace();
                          }
                           return false;
                            }
                                      }

    

  
    
    public boolean cancelReservation(int reservationID)
            throws ValidationException {

        if (reservationID <= 0) {
            throw new ValidationException();
        }

        try {
            connection.setAutoCommit(false);

        
            boolean cancelled = reservationDAO.cancelReservation(reservationID);

            if (!cancelled) return false;

          
            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
