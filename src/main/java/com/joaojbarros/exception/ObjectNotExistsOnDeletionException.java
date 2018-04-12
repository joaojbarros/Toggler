package com.joaojbarros.exception;

public class ObjectNotExistsOnDeletionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public ObjectNotExistsOnDeletionException(String value, String object) {
        super("Not found entity "+object+" with identifier "+value+". Can't process the deletion.");
    }
}