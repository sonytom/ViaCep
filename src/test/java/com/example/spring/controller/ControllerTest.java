package com.example.spring.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.jpa.ApplicationTestConfig;
import com.example.spring.ControllerAdvice;
import com.example.spring.entity.Customer;







@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class ControllerTest{

	@Autowired
	private Controller controller;
	
	@Autowired
	private MockMvc mvc;
	
	private List<Customer> customers;
	
	@Before
	public void initCustomers(){
    	this.mvc = MockMvcBuilders.standaloneSetup(controller)
    			.setControllerAdvice(new ControllerAdvice())
    			.build();
    	
    	customers=new ArrayList<Customer>();
    	Customer customerWithjpa = new Customer();
    	
    	//customers = new ArrayList<Customer>();
    	//Customer customerWithjpa = new Customer();
    	
    	//customerWithjpa.setId(10);
    	customerWithjpa.setFirstName("caralho");
    	customerWithjpa.setLastName("Franklin");
    	//ownerWithPet.setAddress("110 W. Liberty St.");
    	//ownerWithPet.setCity("Madison");
    	//ownerWithPet.setTelephone("6085551023");
    	//ownerWithPet.addPet(getTestPetWithIdAndName(ownerWithPet, 1, "Rosy"));
    	customers.add(customerWithjpa);}

	@Test
	void testGetAllCustomersSuccess() throws Exception {
		//TODO O MOCK DO REPOSITORIO VAI RETORNAR UM OBJETO BREAKING BAD
		//TODO DEVE RETORNAR UM ID1 E O FIRST NAME walter white
		mvc.perform(MockMvcRequestBuilders
				.get("/api/customers")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.[0].id").value(1))
					.andExpect(jsonPath("$.[0].firstName").value("Walter"))
					.andExpect(jsonPath("$.[0].lastName").value("White"));
	}
	

	@Test
	void testGetAllCustomersNotFound() throws Exception {
		//TODO QUANDO EX O TESTE O MOCK VAI DIZER QUE O BANCO ESTA VAZIO
		mvc.perform(MockMvcRequestBuilders
				.get("/api/customers")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
	}


}

