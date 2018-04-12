package com.joaojbarros;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaojbarros.configuration.WebMvcConfig;
import com.joaojbarros.model.ServiceToggle;
import com.joaojbarros.repository.TogglerRepository;

@RunWith(SpringRunner.class)
@EnableMongoRepositories(basePackageClasses = TogglerRepository.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@DataJpaTest
public class TogglerRepositoryTest {
	
	@Autowired
    private TogglerRepository togglerRepository;
	
    private StringBuilder str;
    @Before
    public void init() {
    	str = new StringBuilder();
    	
    	str.append(" {                                                           ");
    	str.append("         \"serviceId\":\"serviceZZZ\",                       ");    
    	str.append("		 \"serviceName\":\"serviceABC Name\",                 ");   
    	str.append("         \"version\":\"1.0\",                                ");    
    	str.append("         \"serviceFeatures\":[                               ");  
    	str.append("            {                                                ");
    	str.append("               \"featureId\":\"feature1\",                   ");    
    	str.append("               \"featureName\":\"feature update CRM\",       ");    
    	str.append("               \"serviceTogglesDefinitions\":[               ");  
    	str.append("                  {                                          ");
    	str.append("                     \"toggleName\":\"isButtonBlue\",        ");    
    	str.append("                     \"stateIfTrue\":\"enable\",             ");    
    	str.append("                     \"stateIfFalse\":\"enable\"             ");    
    	str.append("                  },                                         ");
    	str.append("                  {                                          ");
    	str.append("                     \"toggleName\":\"isButtonGreen\",       ");    
    	str.append("                     \"stateIfTrue\":\"enable\",             ");    
    	str.append("                     \"stateIfFalse\":\"disable\"            ");    
    	str.append("                  },                                         ");
    	str.append("                  {                                          ");
    	str.append("                     \"toggleName\":\"isButtonRed\",         ");    
    	str.append("                     \"stateIfTrue\":\"disable\",            ");    
    	str.append("                     \"stateIfFalse\":\"disable\"            ");    
    	str.append("                  }                                          ");
    	str.append("               ]                                             ");
    	str.append("            },                                               ");
    	str.append("            {                                                ");
    	str.append("               \"featureId\":\"feature2\",                   ");    
    	str.append("               \"featureName\":\"feature update store\",     ");    
    	str.append("               \"serviceTogglesDefinitions\":[               ");  
    	str.append("                  {                                          ");
    	str.append("                     \"toggleName\":\"isButtonBlue\",        ");    
    	str.append("                     \"stateIfTrue\":\"enable\",             ");    
    	str.append("                     \"stateIfFalse\":\"enable\"             ");    
    	str.append("                  },                                         ");
    	str.append("                  {                                          ");
    	str.append("                     \"toggleName\":\"isButtonGreen\",       ");    
    	str.append("                     \"stateIfTrue\":\"enable\",             ");    
    	str.append("                     \"stateIfFalse\":\"disable\"            ");    
    	str.append("                  },                                         ");
    	str.append("                  {                                          ");
    	str.append("                     \"toggleName\":\"isButtonRed\",         ");    
    	str.append("                     \"stateIfTrue\":\"disable\",            ");    
    	str.append("                     \"stateIfFalse\":\"disable\"            ");    
    	str.append("                  }                                          ");
    	str.append("               ]                                             ");
    	str.append("            }                                                ");
    	str.append("         ]                                                   ");
    	str.append("      }                                                      ");
    }
    
    @Test
    public void testTogglerSelect() {
    	
    	ServiceToggle serviceTogglePersistent = togglerRepository.findCustomByServiceIdVersion("serviceABC", "1.0");
    	
    	assertNotNull(serviceTogglePersistent);
    	assertNotNull(serviceTogglePersistent.getServiceFeatures());
    	assertNotEquals(0, serviceTogglePersistent.getServiceFeatures().size());
    }
    
    @Test
    public void testTogglerInclusionDeletion() {
    	
    	ObjectMapper ObjectMapper = new ObjectMapper();
    	try {
			ServiceToggle serviceToggle = ObjectMapper.readValue(str.toString(), ServiceToggle.class);
			serviceToggle = togglerRepository.save(serviceToggle);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ServiceToggle serviceTogglePersistent = togglerRepository.findCustomByServiceIdVersion("serviceZZZ", "1.0");
    	
    	assertNotNull(serviceTogglePersistent);
    	assertNotNull(serviceTogglePersistent.getServiceFeatures());
    	assertNotEquals(0, serviceTogglePersistent.getServiceFeatures().size());
    	
    	togglerRepository.delete(serviceTogglePersistent);
    	
    	ServiceToggle serviceTogglePersistent2 = togglerRepository.findCustomByServiceIdVersion("serviceZZZ", "1.0");
    	assertNull(serviceTogglePersistent2);
    }
    

 
}