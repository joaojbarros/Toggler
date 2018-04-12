package com.joaojbarros.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ToggleValue {

	private Boolean toggleValue;

	public Boolean getToggleValue() {
		return toggleValue;
	}

	public void setToggleValue(Boolean toggleValue) {
		this.toggleValue = toggleValue;
	}
	
}