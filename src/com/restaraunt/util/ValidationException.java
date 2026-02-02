package com.restaraunt.util;

public class ValidationException extends Exception{
	public String toString() {
		return "ValidationException"+getMessage();
	}

}
