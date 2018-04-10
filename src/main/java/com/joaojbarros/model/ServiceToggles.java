package com.joaojbarros.model;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "serviceToggles")
public class ServiceToggles {

	@Id
	private BigInteger _id;

	@Indexed(unique = true)
	private String serviceId;

	private String serviceName;

	private String version;
	private List<ServiceFeature> serviceFeatures;

	public BigInteger get_id() {
		return _id;
	}

	public void set_id(BigInteger _id) {
		this._id = _id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<ServiceFeature> getServiceFeatures() {
		return serviceFeatures;
	}

	public void setServiceFeatures(List<ServiceFeature> serviceFeatures) {
		this.serviceFeatures = serviceFeatures;
	}
}