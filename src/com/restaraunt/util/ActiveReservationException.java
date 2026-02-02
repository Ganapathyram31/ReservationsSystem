package com.restaraunt.util;

public class ActiveReservationException extends Exception {
	public String toString(){
		return "ActiveReservationException"+getMessage();
	}

}
