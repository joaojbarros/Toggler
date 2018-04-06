package com.joaojbarros.service;

import com.joaojbarros.model.Toggles;

public interface TogglesService {
	
	public void saveToggle(Toggles toggles);
	public Toggles findByServiceIdVersion(String serviceId, String version);
}
