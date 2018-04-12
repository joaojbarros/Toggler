package com.joaojbarros;

import static org.junit.Assert.assertEquals;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaojbarros.model.Toggle;
import com.joaojbarros.repository.ToggleRepository;
import com.joaojbarros.repository.TogglerRepository;

@EnableMongoRepositories(basePackageClasses = TogglerRepository.class)
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT,
  classes = TogglerApplication.class)
@AutoConfigureMockMvc
public class ToogleIntegratedTest {
 
	private StringBuilder str;
	
	 @Before
	    public void init() {
	    	str = new StringBuilder();
	    	
	    	str.append("                  {                                          ");
	    	str.append("                     \"toggleName\":\"isButtonPurple\",      ");
	    	str.append("                     \"toggleValue\": true				     ");
	    	str.append("                  }                                          ");
	    	
	 }
    
 
    @Autowired
    private ToggleRepository toggleRepository;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    private Toggle createToggleRequest() throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper ObjectMapper = new ObjectMapper();
    	Toggle toggle = ObjectMapper.readValue(str.toString(), Toggle.class);
		return toggle;
    }
    
    @Test
    public void postServiceToggleAndGetThat()
      throws Exception {
     
    	Toggle toggle = createToggleRequest();
    	
    	String requestJson = str.toString();
    	
    	HttpHeaders headers = null;
    	
    	Toggle peristent = toggleRepository.findCustomByToggleName(toggle.getToggleName());
    	
    	if(peristent!=null) {
    		toggleRepository.delete(peristent);    		
    	}
    	

    	String url = "/toggler/toggles/";
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
     	headers.add( "Authorization", getAuthValue());

    	HttpEntity<String> entityPost = new HttpEntity<String>(requestJson,headers);
    	Toggle answer = restTemplate.postForObject(url, entityPost, Toggle.class);

    	assertNotNull(answer);
    	
        assertEquals(toggle.getToggleName(), answer.getToggleName());
        
    	peristent = toggleRepository.findCustomByToggleName(toggle.getToggleName());
        
        assertNotNull(peristent);
    	
    	if(peristent!=null) {
    		toggleRepository.delete(peristent);    		
    	}
    }
    
    @Test
    public void postServiceToggleAndGetThatByIds()
      throws Exception {
     
    	Toggle toggle = createToggleRequest();
    	
    	String requestJson = str.toString();
    	
    	HttpHeaders headers = null;
    	
    	Toggle peristent = toggleRepository.findCustomByToggleName(toggle.getToggleName());
    	
    	if(peristent!=null) {
    		toggleRepository.delete(peristent);    		
    	}
    	

    	String url = "/toggler/toggles/";
    	headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
     	headers.add( "Authorization", getAuthValue());

    	HttpEntity<String> entityPost = new HttpEntity<String>(requestJson,headers);
    	Toggle answerPost = restTemplate.postForObject(url, entityPost, Toggle.class);

    	assertNotNull(answerPost);
    	
        assertEquals(toggle.getToggleName(), answerPost.getToggleName());
    	
    	if(peristent!=null) {
    		toggleRepository.delete(peristent);    		
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