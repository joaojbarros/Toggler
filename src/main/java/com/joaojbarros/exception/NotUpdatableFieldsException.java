package com.joaojbarros.exception;

public class NotUpdatableFieldsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5295140893041880586L;

	public NotUpdatableFieldsException(String value, String object) {
        super("O(s) campo(s) "+value+" do objecto "+object+" não são atualizaveis nesta operação. Não é possível a atualização.");
    }
}