package com.joaojbarros.exception;

public class ObjectNotExistsOnDeletionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public ObjectNotExistsOnDeletionException(String value, String object) {
        super("Não encontrado objeto "+object+" com identificador "+value+". Não é possível realizar a deleção.");
    }
}