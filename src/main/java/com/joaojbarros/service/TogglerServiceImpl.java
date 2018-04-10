package com.joaojbarros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaojbarros.model.ServiceToggles;
import com.joaojbarros.repository.TogglesRepository;

@Service("TogglerService")
public class TogglerServiceImpl implements TogglesService{

	@Autowired
	private TogglesRepository togglesRepository;
	
	@Override
	public ServiceToggles findByServiceIdVersion(String serviceId, String version) {
		// TODO Auto-generated method stub
		return togglesRepository.findCustomByServiceIdVersion(serviceId, version);
	}

	@Override
	public void saveToggle(ServiceToggles toggles) {
		// TODO Auto-generated method stub
		
	}

}
