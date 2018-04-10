package com.joaojbarros.model;

public class ServiceTogglesDefinition {

	
	private String toggleName;

	private String stateIfTrue;

	private String stateIfFalse;

	public String getStateIfTrue() {
		return stateIfTrue;
	}

	public void setStateIfTrue(String stateIfTrue) {
		this.stateIfTrue = stateIfTrue;
	}

	public String getStateIfFalse() {
		return stateIfFalse;
	}

	public void setStateIfFalse(String stateIfFalse) {
		this.stateIfFalse = stateIfFalse;
	}

	public String getToggleName() {
		return toggleName;
	}

	public void setToggleName(String toggleName) {
		this.toggleName = toggleName;
	}
}