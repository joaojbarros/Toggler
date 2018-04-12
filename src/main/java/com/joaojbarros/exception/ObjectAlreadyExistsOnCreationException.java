package com.joaojbarros.exception;

public class ObjectAlreadyExistsOnCreationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public ObjectAlreadyExistsOnCreationException(String value, String object) {
        super("Objeto "+object+" com identificador "+value+" já existe. Não é possível realizar a inclusão.");
    }
	
	public ObjectAlreadyExistsOnCreationException() {
        super("Objeto já existe. Não é possível realizar a inclusão.");
    }
}