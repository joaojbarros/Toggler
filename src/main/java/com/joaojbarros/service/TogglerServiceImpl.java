package com.joaojbarros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaojbarros.model.Toggles;
import com.joaojbarros.repository.TogglesRepository;

@Service("userService")
public class TogglerServiceImpl implements TogglesService{

	@Autowired
	private TogglesRepository togglesRepository;
	
	@Override
	public Toggles findByServiceIdVersion(String serviceId, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveToggle(Toggles toggles) {
		// TODO Auto-generated method stub
		
	}

}
