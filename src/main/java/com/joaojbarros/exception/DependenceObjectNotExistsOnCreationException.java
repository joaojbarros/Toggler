package com.joaojbarros.exception;

public class DependenceObjectNotExistsOnCreationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public DependenceObjectNotExistsOnCreationException(String value, String object) {
        super("Not found entity "+object+" with identifier "+value+". Can't process the entity creation.");
    }
}