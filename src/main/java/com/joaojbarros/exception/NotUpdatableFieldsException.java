package com.joaojbarros.exception;

public class NotUpdatableFieldsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public NotUpdatableFieldsException(String value, String object) {
        super("The field "+value+" from entity "+object+" cant be upgraded on this operation. Can't process the update.");
    }
}