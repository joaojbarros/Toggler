package com.joaojbarros.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaojbarros.exception.DependenceObjectNotExistsOnCreationException;
import com.joaojbarros.exception.NotUpdatableFieldsException;
import com.joaojbarros.exception.ObjectNotExistsOnDeletionException;
import com.joaojbarros.exception.ObjectNotExistsOnUpdateException;
import com.joaojbarros.model.ServiceFeature;
import com.joaojbarros.model.ServiceToggle;
import com.joaojbarros.model.ServiceTogglesDefinition;
import com.joaojbarros.model.Toggle;
import com.joaojbarros.model.ToggleValue;
import com.joaojbarros.repository.ToggleRepository;
import com.joaojbarros.repository.TogglerDAO;
import com.joaojbarros.repository.TogglerRepository;

@Service("TogglerService")
public class TogglerServiceImpl implements TogglerService{

	@Autowired
	private TogglerRepository togglerRepository;

	@Autowired
	private ToggleRepository toggleRepository;
	
	@Autowired
	private TogglerDAO TogglesDAO;
	
	public ServiceToggle findByServiceIdVersion(String serviceId, String version) {
		return togglerRepository.findCustomByServiceIdVersion(serviceId, version);
	}

	@Override
	public ServiceToggle saveServiceToggle(ServiceToggle serviceToggles) throws Exception {
		List<ServiceFeature> serviceFeatures = serviceToggles.getServiceFeatures();
		
		for (ServiceFeature serviceFeature : serviceFeatures) {
			List<ServiceTogglesDefinition> serviceTogglesDefinitions = serviceFeature.getServiceTogglesDefinitions();
			for (ServiceTogglesDefinition serviceTogglesDefinition : serviceTogglesDefinitions) {
				Toggle toggle = toggleRepository.findCustomByToggleName(serviceTogglesDefinition.getToggleName());
				if(toggle==null) {
					throw new DependenceObjectNotExistsOnCreationException(serviceTogglesDefinition.getToggleName(), Toggle.class.getSimpleName());
				}
			}
		}
		
		
		return togglerRepository.insert(serviceToggles);
	}

	@Override
	public List<ServiceToggle> findCustomByServiceIdVersionFieldsExcludeFields(String serviceId, String version, String feature, String fields, String excludeFields) {
		return TogglesDAO.findCustomByServiceIdVersionFieldsExcludeFields(serviceId, version, feature, fields, excludeFields);		
	}

	@Override	
	public Toggle saveToggle(Toggle toggles) {
		return toggleRepository.insert(toggles);
		
	}
	
	@Override	
	public Toggle updateToggle(String toggleName, ToggleValue toggleValue) throws Exception {
		Toggle toggle = toggleRepository.findCustomByToggleName(toggleName);
		if(toggle == null) {
			if(toggle==null) {
				throw new ObjectNotExistsOnUpdateException(toggleName, Toggle.class.getSimpleName());
			}
		}else {
			toggle.setToggleValue(toggleValue.getToggleValue());
		}
			
		return toggleRepository.save(toggle);
		
	}
	
	
	@Override	
	public ServiceToggle updateServiceToggle(String serviceId, String version, ServiceToggle serviceToggle) throws Exception {
		ServiceToggle serviceTogglePersistent = togglerRepository.findCustomByServiceIdVersion(serviceId, version);
		if(serviceTogglePersistent == null) {
			if(serviceTogglePersistent==null) {
				throw new ObjectNotExistsOnUpdateException(serviceId+"/"+version, ServiceToggle.class.getSimpleName());
			}
		}
		if(!serviceId.equals(serviceToggle.getServiceId()) || !version.equals(serviceToggle.getVersion())) {
				throw new NotUpdatableFieldsException("serviceId,version", ServiceToggle.class.getSimpleName());
		}else {
			BigInteger _id = serviceTogglePersistent.get_id();
			List<ServiceFeature> serviceFeaturesPersistentDoAdd = new ArrayList<>();
			List<ServiceFeature> serviceFeaturesPersistent = serviceTogglePersistent.getServiceFeatures();
			List<ServiceFeature> serviceFeatures = serviceToggle.getServiceFeatures();
			for (ServiceFeature serviceFeature : serviceFeatures) {
				List<ServiceTogglesDefinition> serviceTogglesDefinitionsPersistentDoAdd = new ArrayList<>();
				boolean existsServiceFeature = false;
				for (ServiceFeature serviceFeaturePersistent : serviceFeaturesPersistent) {
					if(serviceFeaturePersistent.getFeatureId().equals(serviceFeature.getFeatureId())) {
						existsServiceFeature = true;
						List<ServiceTogglesDefinition> serviceTogglesDefinitionsPersistent = serviceFeaturePersistent.getServiceTogglesDefinitions();
						List<ServiceTogglesDefinition> serviceTogglesDefinitions = serviceFeature.getServiceTogglesDefinitions();
						for (ServiceTogglesDefinition serviceTogglesDefinition : serviceTogglesDefinitions) {
							boolean existsServiceTogglesDefinition = false;
							for (ServiceTogglesDefinition serviceTogglesDefinitionPersistent : serviceTogglesDefinitionsPersistent) {
								if(serviceTogglesDefinitionPersistent.getToggleName().equals(serviceTogglesDefinition.getToggleName())){
									BeanUtils.copyProperties(serviceTogglesDefinition,serviceTogglesDefinitionPersistent);
									existsServiceTogglesDefinition = true;
								}
							}
							if(!existsServiceTogglesDefinition) {
								serviceTogglesDefinitionsPersistentDoAdd.add(serviceTogglesDefinition);
							}
						
						}
						BeanUtils.copyProperties(serviceFeature,serviceFeaturePersistent);
						serviceTogglesDefinitionsPersistent.addAll(serviceTogglesDefinitionsPersistentDoAdd);
					}
				}
				if(!existsServiceFeature) {
					serviceFeaturesPersistentDoAdd.add(serviceFeature);
				}
			}
			serviceFeaturesPersistent.addAll(serviceFeaturesPersistentDoAdd);
			serviceTogglePersistent.set_id(_id);
		}
		return togglerRepository.save(serviceTogglePersistent);
	}
	
	
	@Override	
	public void deleteToggle(String toggleName) throws Exception {
		Toggle toggle = toggleRepository.findCustomByToggleName(toggleName);
		if(toggle == null) {
			if(toggle==null) {
				throw new ObjectNotExistsOnDeletionException(toggleName, Toggle.class.getSimpleName());
			}
		}else {
			toggleRepository.delete(toggle);
		}
	}
	
	@Override	
	public void deleteServiceToggle(String serviceId, String version) throws Exception {
		ServiceToggle serviceToggle = togglerRepository.findCustomByServiceIdVersion(serviceId, version);
		if(serviceToggle == null) {
			if(serviceToggle==null) {
				throw new ObjectNotExistsOnDeletionException(serviceId+"/"+version, ServiceToggle.class.getSimpleName());
			}
		}else {
			togglerRepository.delete(serviceToggle);
		}
	}


	@Override
	public Toggle findToggleByToggleName(String toggleName) {
		return toggleRepository.findCustomByToggleName(toggleName);
	}

	@Override
	public List<Toggle> findToggleByToggleValue(Boolean toggleValue) {
		List<Toggle> toggles = null;
		if(toggleValue != null) {
			toggles = toggleRepository.findCustomByToggleValue(toggleValue);
		}else {
			toggles = toggleRepository.findAll();
		}
		return toggles;
	}
}
