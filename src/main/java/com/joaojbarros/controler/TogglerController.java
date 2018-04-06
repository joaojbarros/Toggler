package com.joaojbarros.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaojbarros.model.Toggles;
import com.joaojbarros.service.TogglesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/configs")
@Api(value = "API to manager the configurations from a resource", 
	description = "This API provides the capability to include a toggle and erase the cache from aplicaton"
	, produces = "application/json")
public class TogglerController {
	
	@Autowired
	private TogglesService togglesService;

	@ApiOperation(value = "Search toggles parameters an your values", produces = "application/json")
	@RequestMapping(value = "/toggler", method = RequestMethod.GET)
    public Toggles getToggles(@RequestParam(value="serviceId") String serviceId,@RequestParam(value="version") String version) {
        return togglesService.findByServiceIdVersion(serviceId, version);
    }
	

}


