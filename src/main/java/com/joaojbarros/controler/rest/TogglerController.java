package com.joaojbarros.controler.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaojbarros.model.ErrorResponse;
import com.joaojbarros.model.ServiceToggle;
import com.joaojbarros.model.Toggle;
import com.joaojbarros.model.ToggleValue;
import com.joaojbarros.service.TogglerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.rossillo.spring.web.mvc.CacheControl;


@RestController
@CrossOrigin(origins  = "http://localhost:8080")
@RequestMapping(value = "/toggler")
@Api(tags = "Toggler", value = "Toggler", 
	description = "This API provides the capability to manager toggles and your hierarchy "
	, produces = "application/json", consumes="application/json")
@ExposesResourceFor(Toggle.class)
public class TogglerController {
	
	@Autowired
	private TogglerService togglerService;
	
	protected Logger logger;
	
	public TogglerController() {
		logger = LoggerFactory.getLogger(getClass());
	}

	@ApiOperation(value = "Search toggles parameters an your values", response=ServiceToggle.class,  produces = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Return ResponseModel with success message",
					response=ServiceToggle.class,
					responseContainer = "List"
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@CacheControl(maxAge=300)
	@RequestMapping(value = "/services", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ServiceToggle> getServiceToggles(
			@ApiParam(name = "serviceId", value = "The service identification")
			@RequestParam(required = false) String serviceId,
			@ApiParam(name = "version", value = "The service version")
			@RequestParam(required = false) String version,
			@ApiParam(name = "featureId", value = "The feature identification")
			@RequestParam(required = false) String featureId,
			@ApiParam(name = "fields", value = "Fields for include (return only the fields present that) from response. Use the CSV pattern for separate the fields")
			@RequestParam(value = "fields", required = false) String fields,
			@ApiParam(name = "-fields", value = "Fields for exclude from response. Use the CSV pattern for separete the fields")
			@RequestParam(value = "-fields", required = false) String excludeFields) {
		List<ServiceToggle> serviceToggles = null;
		serviceToggles = togglerService.findCustomByServiceIdVersionFieldsExcludeFields(serviceId, version, featureId, fields, excludeFields);
		
        return serviceToggles;
    }
	
	@ApiOperation(value = "Search toggles parameters an your values by id", response=ServiceToggle.class,  produces = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Return ResponseModel with success message",
					response=ServiceToggle.class
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@CacheControl(maxAge=300)
	@RequestMapping(value = "/services/{serviceId}/{version}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ServiceToggle> getServiceTogglesById(
			@ApiParam(required = true, name = "serviceId", value = "The service identification")
			@PathVariable(required = true) String serviceId,
			@ApiParam(required = true, name = "version", value = "The service version")
			@RequestParam(required = false) String version,
			@ApiParam(name = "fields", value = "Fields for include (return only the fields present that) from response. Use the CSV pattern for separate the fields")
			@RequestParam(value = "fields", required = false) String fields,
			@ApiParam(name = "-fields", value = "Fields for exclude from response. Use the CSV pattern for separete the fields")
			@RequestParam(required = false) String excludeFields) {
		List<ServiceToggle> serviceToggles = null;
		serviceToggles = togglerService.findCustomByServiceIdVersionFieldsExcludeFields(serviceId, version, null, fields, excludeFields);
        return serviceToggles;
    }
	
	@ApiOperation(value = "Include service features parameters an your values", response=ServiceToggle.class,  produces = "application/json", consumes = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=201, 
					message="Return the representation of the created object",
					response=ServiceToggle.class
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=401, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@RequestMapping(value = "/services", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<ServiceToggle> postServiceToggles(
			@ApiParam(name = "serviceToggles", value = "The body of serviceToggles and your features definitions") @Valid @RequestBody(required = true) ServiceToggle serviceToggles) throws Exception {
		serviceToggles = togglerService.saveServiceToggle(serviceToggles);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", "http://localhost:8080/toggler/toggles/"+serviceToggles.getServiceId());
        return new ResponseEntity<ServiceToggle>(serviceToggles, headers, HttpStatus.CREATED);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 */
	@ApiOperation(value = "Include toggles and your values", response=ServiceToggle.class,  produces = "application/json", consumes = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=201, 
					message="Return the representation of the created object",
					response=Toggle.class
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@RequestMapping(value = "/toggles", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<Toggle> postToggles(
			@ApiParam(name = "toggles", value = "The body of toggle and your values") @Valid @RequestBody(required = true) Toggle toggles) {
		
		toggles = togglerService.saveToggle(toggles);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", "http://localhost:8080/toggler/tuggles/"+toggles.getToggleName());
        return new ResponseEntity<Toggle>(toggles, headers, HttpStatus.CREATED);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 */
	@ApiOperation(value = "Find toggles and your values by id", response=ServiceToggle.class,  produces = "application/json", consumes = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Return ResponseModel with success message",
					response=Toggle.class
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@CacheControl(maxAge=300)
	@RequestMapping(value = "/toggles/{toggleName}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Toggle> getTogglesById(
			@ApiParam(required = true, name = "toggleName", value = "The name of toggle")
			@PathVariable(required = true) String toggleName) {
		
		Toggle toggles = togglerService.findToggleByToggleName(toggleName);
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Toggle>(toggles, headers, HttpStatus.OK);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 */
	@ApiOperation(value = "Find toggles, optionaly by your value", response=ServiceToggle.class,  produces = "application/json", consumes = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Return ResponseModel with success message",
					response=Toggle.class,
					responseContainer = "List"
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@CacheControl(maxAge=300)
	@RequestMapping(value = "/toggles", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<List<Toggle>> getToggles(
			@ApiParam(name = "toggleValue", value = "Boolean value for toggle") @RequestParam(required = false) Boolean toggleValue) {
		List<Toggle> toggles = togglerService.findToggleByToggleValue(toggleValue);
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<List<Toggle>>(toggles, headers, HttpStatus.OK);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "Update toggle value by id", response=ServiceToggle.class,  produces = "application/json", consumes = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Return ResponseModel with success message",
					response=Toggle.class
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=422, 
					message="If the process has breaked by a business rule or database restrictions.",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@RequestMapping(value = "/toggles/{toggleName}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Toggle> patchToggle(
			@ApiParam(required = true, name = "toggleName", value = "The name of toggle")
			@PathVariable(required = true) String toggleName,
			@ApiParam(name = "toggleValue", value = "Boolean value for toggle")
			@Valid @RequestBody(required = true) ToggleValue toggleValue) throws Exception {
		Toggle toggleResponse = togglerService.updateToggle(toggleName, toggleValue);
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Toggle>(toggleResponse, headers, HttpStatus.OK);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "Update toggle value by id", response=ServiceToggle.class,  produces = "application/json", consumes = "application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Return ResponseModel with success message",
					response=ServiceToggle.class
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=422, 
					message="If the process has breaked by a business rule or database restrictions.",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@RequestMapping(value = "/services/{serviceId}/{version:.+}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<ServiceToggle> patchToggle(
			@ApiParam(required = true, name = "serviceId", value = "The serviceId")
			@PathVariable(required = true) String serviceId,
			@ApiParam(required = true, name = "version", value = "The service version")
			@PathVariable(required = true) String version,
			@ApiParam(required = true, value = "The serviceToggle body, with definition from all features")
			@Valid @RequestBody(required = true) ServiceToggle serviceToggle) throws Exception {
		ServiceToggle serviceToggleResponse = togglerService.updateServiceToggle(serviceId, version, serviceToggle);
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<ServiceToggle>(serviceToggleResponse, headers, HttpStatus.OK);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "Delete toggle by id")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Deletion Success"
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@CacheControl(maxAge=300)
	@RequestMapping(value = "/toggles/{toggleName}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ApiParam(required = true, name = "toggleName", value = "The toggle name")
	public @ResponseBody void deleteToggle(@PathVariable(required = true) String toggleName)
			throws Exception {
		togglerService.deleteToggle(toggleName);
    }
	
	/**
	 * @param serviceToggles
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "Delete serviceToggles by serviceId and version")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Deletion Success"
					),
			@ApiResponse(
					code=400, 
					message="If client send an wrong request",
					response = ErrorResponse.class
					),
			@ApiResponse(
					code=500, 
					message="If error, return ResponseError model",
					response = ErrorResponse.class
					)
 
	})
	@RequestMapping(value = "/services/{serviceId}/{version:.+}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteServiceTogglesById(
			@ApiParam(required = true, name = "serviceId", value = "The service identification")
			@PathVariable(required = true) String serviceId,
			@ApiParam(required = true, name = "version", value = "The service version")
			@RequestParam(required = false) String version) throws Exception {
		togglerService.deleteServiceToggle(serviceId, version);
    }
	
}


