package com.joaojbarros.service;

import java.util.List;

import com.joaojbarros.model.ServiceToggle;
import com.joaojbarros.model.Toggle;
import com.joaojbarros.model.ToggleValue;

public interface TogglerService {
	
	public ServiceToggle saveServiceToggle(ServiceToggle serviceToggles) throws Exception;
	public Toggle saveToggle(Toggle toggles);
	public Toggle findToggleByToggleName(String toggleName);
	public List<Toggle> findToggleByToggleValue(Boolean toggleValue);
	public List<ServiceToggle> findCustomByServiceIdVersionFieldsExcludeFields(String serviceId, String version, String feature, String fields,
			String excludeFields);
	public Toggle updateToggle(String toggleName, ToggleValue togglesValue) throws Exception;
	public void deleteToggle(String toggleName) throws Exception;
	public ServiceToggle updateServiceToggle(String serviceId, String version, ServiceToggle serviceToggle) throws Exception;
	public void deleteServiceToggle(String serviceId, String version) throws Exception;
}
