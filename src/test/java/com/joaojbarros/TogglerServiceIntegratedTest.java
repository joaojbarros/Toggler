package com.joaojbarros;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaojbarros.model.ServiceToggle;
import com.joaojbarros.repository.TogglerRepository;

@EnableMongoRepositories(basePackageClasses = TogglerRepository.class)
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT,
  classes = TogglerApplication.class)
@AutoConfigureMockMvc
public class TogglerServiceIntegratedTest {
 
	private StringBuilder strZZZ;
	
	private StringBuilder strABC;
	
    @Autowired
    private MockMvc mvc;
 
	 @Before
	    public void init() {
		 	strZZZ = new StringBuilder();
	    	strZZZ.append(" {                                                           ");
	    	strZZZ.append("         \"serviceId\":\"serviceZZZ\",                       ");    
	    	strZZZ.append("		 \"serviceName\":\"serviceABC Name\",                 ");   
	    	strZZZ.append("         \"version\":\"1.0\",                                ");    
	    	strZZZ.append("         \"serviceFeatures\":[                               ");  
	    	strZZZ.append("            {                                                ");
	    	strZZZ.append("               \"featureId\":\"feature1\",                   ");    
	    	strZZZ.append("               \"featureName\":\"feature update CRM\",       ");    
	    	strZZZ.append("               \"serviceTogglesDefinitions\":[               ");  
	    	strZZZ.append("                  {                                          ");
	    	strZZZ.append("                     \"toggleName\":\"isButtonBlue\",        ");    
	    	strZZZ.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strZZZ.append("                     \"stateIfFalse\":\"enable\"             ");    
	    	strZZZ.append("                  },                                         ");
	    	strZZZ.append("                  {                                          ");
	    	strZZZ.append("                     \"toggleName\":\"isButtonGreen\",       ");    
	    	strZZZ.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strZZZ.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strZZZ.append("                  },                                         ");
	    	strZZZ.append("                  {                                          ");
	    	strZZZ.append("                     \"toggleName\":\"isButtonRed\",         ");    
	    	strZZZ.append("                     \"stateIfTrue\":\"disable\",            ");    
	    	strZZZ.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strZZZ.append("                  }                                          ");
	    	strZZZ.append("               ]                                             ");
	    	strZZZ.append("            },                                               ");
	    	strZZZ.append("            {                                                ");
	    	strZZZ.append("               \"featureId\":\"feature2\",                   ");    
	    	strZZZ.append("               \"featureName\":\"feature update store\",     ");    
	    	strZZZ.append("               \"serviceTogglesDefinitions\":[               ");  
	    	strZZZ.append("                  {                                          ");
	    	strZZZ.append("                     \"toggleName\":\"isButtonBlue\",        ");    
	    	strZZZ.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strZZZ.append("                     \"stateIfFalse\":\"enable\"             ");    
	    	strZZZ.append("                  },                                         ");
	    	strZZZ.append("                  {                                          ");
	    	strZZZ.append("                     \"toggleName\":\"isButtonGreen\",       ");    
	    	strZZZ.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strZZZ.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strZZZ.append("                  },                                         ");
	    	strZZZ.append("                  {                                          ");
	    	strZZZ.append("                     \"toggleName\":\"isButtonRed\",         ");    
	    	strZZZ.append("                     \"stateIfTrue\":\"disable\",            ");    
	    	strZZZ.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strZZZ.append("                  }                                          ");
	    	strZZZ.append("               ]                                             ");
	    	strZZZ.append("            }                                                ");
	    	strZZZ.append("         ]                                                   ");
	    	strZZZ.append("      }                                                      ");
			
	    	
	    	strABC = new StringBuilder();
	    	strABC.append(" {                                                           ");
	    	strABC.append("         \"serviceId\":\"serviceABC\",                       ");    
	    	strABC.append("		 \"serviceName\":\"serviceABC Name\",                 ");   
	    	strABC.append("         \"version\":\"1.0\",                                ");    
	    	strABC.append("         \"serviceFeatures\":[                               ");  
	    	strABC.append("            {                                                ");
	    	strABC.append("               \"featureId\":\"feature1\",                   ");    
	    	strABC.append("               \"featureName\":\"feature update CRM\",       ");    
	    	strABC.append("               \"serviceTogglesDefinitions\":[               ");  
	    	strABC.append("                  {                                          ");
	    	strABC.append("                     \"toggleName\":\"isButtonBlue\",        ");    
	    	strABC.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strABC.append("                     \"stateIfFalse\":\"enable\"             ");    
	    	strABC.append("                  },                                         ");
	    	strABC.append("                  {                                          ");
	    	strABC.append("                     \"toggleName\":\"isButtonGreen\",       ");    
	    	strABC.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strABC.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strABC.append("                  },                                         ");
	    	strABC.append("                  {                                          ");
	    	strABC.append("                     \"toggleName\":\"isButtonRed\",         ");    
	    	strABC.append("                     \"stateIfTrue\":\"disable\",            ");    
	    	strABC.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strABC.append("                  }                                          ");
	    	strABC.append("               ]                                             ");
	    	strABC.append("            },                                               ");
	    	strABC.append("            {                                                ");
	    	strABC.append("               \"featureId\":\"feature2\",                   ");    
	    	strABC.append("               \"featureName\":\"feature update store\",     ");    
	    	strABC.append("               \"serviceTogglesDefinitions\":[               ");  
	    	strABC.append("                  {                                          ");
	    	strABC.append("                     \"toggleName\":\"isButtonBlue\",        ");    
	    	strABC.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strABC.append("                     \"stateIfFalse\":\"enable\"             ");    
	    	strABC.append("                  },                                         ");
	    	strABC.append("                  {                                          ");
	    	strABC.append("                     \"toggleName\":\"isButtonGreen\",       ");    
	    	strABC.append("                     \"stateIfTrue\":\"enable\",             ");    
	    	strABC.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strABC.append("                  },                                         ");
	    	strABC.append("                  {                                          ");
	    	strABC.append("                     \"toggleName\":\"isButtonRed\",         ");    
	    	strABC.append("                     \"stateIfTrue\":\"disable\",            ");    
	    	strABC.append("                     \"stateIfFalse\":\"disable\"            ");    
	    	strABC.append("                  }                                          ");
	    	strABC.append("               ]                                             ");
	    	strABC.append("            }                                                ");
	    	strABC.append("         ]                                                   ");
	    	strABC.append("      }                                                      ");
	 }
    
 
    @Autowired
    private TogglerRepository togglerRepository;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    private ServiceToggle createServiceTogglesRequest() throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper ObjectMapper = new ObjectMapper();
		ServiceToggle serviceToggle = ObjectMapper.readValue(strZZZ.toString(), ServiceToggle.class);
		return serviceToggle;
    }
    
    @Test
    public void postServiceToggleAndGetThat()
      throws Exception {
     
    	ServiceToggle serviceToggle = createServiceTogglesRequest();
    	
    	String requestJson = strZZZ.toString();
    	
    	HttpHeaders headers = null;
    	
    	ServiceToggle peristent = togglerRepository.findCustomByServiceIdVersion(serviceToggle.getServiceId(), serviceToggle.getVersion());
    	
    	if(peristent!=null) {
    		togglerRepository.delete(peristent);    		
    	}
    	

    	String url = "/toggler/services/";
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
     	headers.add( "Authorization", getAuthValue());

    	HttpEntity<String> entityPost = new HttpEntity<String>(requestJson,headers);
    	ServiceToggle answer = restTemplate.postForObject(url, entityPost, ServiceToggle.class);
    	System.out.println(answer);

    	assertNotNull(answer);
    	
        assertNotEquals("Foo", answer.getServiceName());
        
        url = "/toggler/services/"+answer.getServiceId()+"/"+answer.getVersion();
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
     	headers.add( "Authorization", getAuthValue());

    	HttpEntity<String> entityGet = new HttpEntity<String>(headers);
    	ServiceToggle answerGet = restTemplate.getForObject(url, ServiceToggle.class, entityGet);
    	System.out.println(answerGet);
    	
    	assertNotNull(answerGet);
    	
        assertNotEquals("Foo", answerGet.getServiceName());
        
    	peristent = togglerRepository.findCustomByServiceIdVersion(answerGet.getServiceId(), answerGet.getVersion());
    	
    	if(peristent!=null) {
    		togglerRepository.delete(peristent);    		
    	}
        
    	peristent = togglerRepository.findCustomByServiceIdVersion(answerGet.getServiceId(), answerGet.getVersion());
    	
    	if(peristent!=null) {
    		togglerRepository.delete(peristent);    		
    	}
    }
    
    @Test
    public void postServiceToggleAndGetThatByIds()
      throws Exception {
     
    	ServiceToggle serviceToggle = createServiceTogglesRequest();
    	
    	String requestJson = strZZZ.toString();
    	
    	HttpHeaders headers = null;
    	
    	ServiceToggle peristent = togglerRepository.findCustomByServiceIdVersion(serviceToggle.getServiceId(), serviceToggle.getVersion());
    	
    	if(peristent!=null) {
    		togglerRepository.delete(peristent);    		
    	}
    	

    	String url = "/toggler/services/";
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
     	headers.add( "Authorization", getAuthValue());

    	HttpEntity<String> entityPost = new HttpEntity<String>(requestJson,headers);
    	ServiceToggle answerPost = restTemplate.postForObject(url, entityPost, ServiceToggle.class);
    	System.out.println(answerPost);

    	assertNotNull(answerPost);
    	
        assertNotEquals("Foo", answerPost.getServiceName());
        
        url = "/toggler/services/"+answerPost.getServiceId()+"/"+answerPost.getVersion();
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
     	headers.add( "Authorization", getAuthValue());

    	HttpEntity<String> entityGet = new HttpEntity<String>(headers);
    	ServiceToggle answerGet = restTemplate.getForObject(url, ServiceToggle.class, entityGet);
    	System.out.println(answerGet);
    	
    	assertNotNull(answerGet);
    	
        assertNotEquals("Foo", answerGet.getServiceName());
        
    	peristent = togglerRepository.findCustomByServiceIdVersion(serviceToggle.getServiceId(), serviceToggle.getVersion());
    	
    	if(peristent!=null) {
    		togglerRepository.delete(peristent);    		
    	}
    }
       
    
    private String getAuthValue() {
		String auth = "joaojbarros@gmail.com:x1234567";
		byte[] encodedAuth = Base64.encode( 
		auth.getBytes(Charset.forName("US-ASCII")) );
		String authHeader = "Basic " + new String( encodedAuth );
		return authHeader;
    }
}