package com.restaraunt.util;

public class TableUnavailableException extends Exception {
	public String toString() {
		return "TableUnavailableException" +getMessage();
	}

}
