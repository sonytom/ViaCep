package com.example.spring.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest{

	@Autowired
	private MockMvc mvc;

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

