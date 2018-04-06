package com.joaojbarros.model;

import java.util.HashMap;
import java.util.Map;

public class TogglesDefinition {

	/**
	 * The Name Schema
	 * <p>
	 *
	 *
	 */
	public String name = "";
	/**
	 * The Stateiftrue Schema
	 * <p>
	 *
	 *
	 */
	public String stateIfTrue = "";
	/**
	 * The Stateiffalse Schema
	 * <p>
	 *
	 *
	 */
	public String stateIfFalse = "";
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}