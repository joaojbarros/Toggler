package com.joaojbarros.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
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
	
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
	    Link link = new Link();
	    link.setRel("self");
	    link.setHref("http://localhost:8080/toggler/toggles/"+toggleName);
	    links.add(link);
		return links;
	}
}