package com.joaojbarros.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaojbarros.model.ServiceToggles;
import com.joaojbarros.service.TogglesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins  = "http://localhost:4200")
@RequestMapping(value = "/configs")
@Api(value = "API to manager the configurations from a resource", 
	description = "This API provides the capability to include a toggle and purge the cache from aplicaton"
	, produces = "application/json")
public class TogglerController {
	
	@Autowired
	private TogglesService togglesService;

	@ApiOperation(value = "Search toggles parameters an your values", response=ServiceToggles.class,  produces = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseModel com uma mensagem de sucesso",
					response=ServiceToggles.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseModel com a Exception",
					response=ServiceToggles.class
					)
 
	})
	@RequestMapping(value = "/toggler", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ServiceToggles getToggles(@RequestParam(value="serviceId") String serviceId, @RequestParam(value="version") String version) {
        return togglesService.findByServiceIdVersion(serviceId, version);
    }
	

}


