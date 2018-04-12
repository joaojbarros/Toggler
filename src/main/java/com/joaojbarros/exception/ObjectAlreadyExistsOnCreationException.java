package com.joaojbarros.exception;

public class ObjectAlreadyExistsOnCreationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public ObjectAlreadyExistsOnCreationException(String value, String object) {
        super("Entity "+object+" with identifier "+value+" already exists. Can't process the creation.");
    }
	
	public ObjectAlreadyExistsOnCreationException() {
        super("Entity already exists. Can't process the creation.");
    }
}