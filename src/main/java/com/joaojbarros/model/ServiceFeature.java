package com.joaojbarros.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceFeature {

	private String featureId;
	private String featureName;
	private List<ServiceTogglesDefinition> serviceTogglesDefinitions;
	
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public List<ServiceTogglesDefinition> getServiceTogglesDefinitions() {
		return serviceTogglesDefinitions;
	}
	public void setServiceTogglesDefinitions(List<ServiceTogglesDefinition> serviceTogglesDefinitions) {
		this.serviceTogglesDefinitions = serviceTogglesDefinitions;
	}
}