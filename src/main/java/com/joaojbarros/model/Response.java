package com.joaojbarros.model;

import com.joaojbarros.exception.ObjectNotExistsOnDeletionException;

public class Response {

	private String version;
	private Object data;
	private ObjectNotExistsOnDeletionException error;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ObjectNotExistsOnDeletionException getError() {
		return error;
	}
	public void setError(ObjectNotExistsOnDeletionException error) {
		this.error = error;
	}
}
