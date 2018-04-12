package com.joaojbarros.model;

import java.math.BigInteger;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Document(collection = "toggles")
public class Toggle {

	private BigInteger _id;
	private String toggleName;
	private Boolean toggleValue;
	public String getToggleName() {
		return toggleName;
	}
	public void setToggleName(String toggleName) {
		this.toggleName = toggleName;
	}
	
	@JsonIgnore
	public BigInteger get_id() {
		return _id;
	}
	public void set_id(BigInteger _id) {
		this._id = _id;
	}
	public Boolean getToggleValue() {
		return toggleValue;
	}
	public void setToggleValue(Boolean toggleValue) {
		this.toggleValue = toggleValue;
	}
	
}