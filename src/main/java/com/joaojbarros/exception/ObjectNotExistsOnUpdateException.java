package com.joaojbarros.exception;

public class ObjectNotExistsOnUpdateException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public ObjectNotExistsOnUpdateException(String value, String object) {
        super("Não encontrado objeto "+object+" com identificador "+value+". Não é possível realizar a atualização.");
	}
}