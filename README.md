# 🍽️ Restaurant Reservation System (Java + JDBC + Oracle)

## 📌 Project Description

The Restaurant Reservation System is a console-based Java application developed using JDBC and Oracle Database. It is designed to manage restaurant table details and customer reservations efficiently. The system follows a layered architecture using Bean, DAO, Service, and Utility packages to ensure clean code structure and proper separation of concerns.
This application allows users to add tables, view table details, reserve tables for customers, and cancel reservations. It ensures that tables cannot be double-booked and maintains accurate reservation records by updating table availability automatically.

 Technologies Used

* Java (Core Java, JDBC)
* Oracle Database (21c XE)
* SQL*Plus / Oracle SQL Developer
* Eclipse IDE

---

Project Architecture

The project follows a multi-layer architecture:

* **Bean Package** – Contains entity classes (Table, Reservation)
* **DAO Package** – Handles database operations using JDBC
* **Service Package** – Contains business logic and validations
* **Util Package** – Database connection and custom exceptions
* **Main Class** – Console-based testing and execution

---

 Features

* Add new restaurant tables
* View all tables and table details
* Reserve a table for customers
* Prevent double booking of tables
* Cancel reservation and update table status
* Automatic table status update (Available / Unavailable)
* Exception handling and input validation
* Transaction management using JDBC

🗄️ Database Tables

TABLE_TBL

Stores restaurant table details:

* tableID
* capacity
* location
* status

RESERVATION_TBL

Stores reservation details:

* reservationID
* tableID
* customerName
* partySize
* reservationDate
* reservationTime
* status

A sequence (RESERVATION_SEQ) is used to generate unique reservation IDs.

---
How It Works

1. User requests a table reservation.
2. System checks table availability and capacity.
3. If available, reservation is created and table status becomes *Unavailable*.
4. On cancellation, reservation status becomes *Cancelled* and table becomes *Available* again.
5. All operations are handled using JDBC transactions to maintain data integrity.

---

 How to Run

1. Create database tables and sequence in Oracle.
2. Update DB connection in `DBUtil.java`.
3. Import project into Eclipse.
4. Run `RestaurantMain.java`.




<img width="907" height="388" alt="Screenshot 2026-02-12 103007" src="https://github.com/user-attachments/assets/35e80b1f-7083-4d32-b8a1-8549a0bcbdcc" /><br></br>
<img width="732" height="696" alt="Screenshot 2026-02-12 103130" src="https://github.com/user-attachments/assets/0b44bbe2-3b39-4d2a-a5d2-2e12058af0ef" /><br></br>
<img width="827" height="219" alt="Screenshot 2026-02-12 103358" src="https://github.com/user-attachments/assets/2db3ca39-8e77-4aad-947c-d0ab74f9f368" />



