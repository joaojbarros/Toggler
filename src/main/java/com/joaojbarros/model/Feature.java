package com.joaojbarros.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Feature {

	/**
	 * The Nameid Schema
	 * <p>
	 *
	 *
	 */
	public String nameId = "";
	/**
	 * The Name Schema
	 * <p>
	 *
	 *
	 */
	public String name = "";
	public List<TogglesDefinition> togglesDefinitions = null;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}