package com.joaojbarros;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.joaojbarros.configuration.WebMvcConfig;
import com.joaojbarros.model.Role;
import com.joaojbarros.model.User;
import com.joaojbarros.repository.UserRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@DataJpaTest
public class UserRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
 
    @Test
    public void testUserRoleInsertAndQuery() {
    	User user = new User();
    	Role role = new Role();
    	//role.setId(1256);
    	role.setRole("ADMIN");
    	Set<Role> set = new HashSet<Role>();
    	
    	entityManager.persist(role);
    	entityManager.flush();
    	set.add(role);
    	user.setActive(1);
    	user.setEmail("teste@teste.com.br");
    	user.setName("Usuario Teste");
    	user.setLastName("Usuario Teste");
    	user.setPassword("testePasword");
    	user.setRoles(set);
    	entityManager.persist(user);
    	entityManager.flush();
    	
    	assertNotNull((userRepository.findByEmail("teste@teste.com.br")));
    }
 
}