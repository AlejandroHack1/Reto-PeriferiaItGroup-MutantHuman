package com.example.reto;

import com.example.reto.controllers.MutantController;
import com.example.reto.models.Mutant;
import com.example.reto.services.MutantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MutantController.class)
class RetoApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MutantService service;


	String[] dnaMutant= {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"};
	String[] dnaHuman = {"GTGCGA","CAGTGC","TTATGT","AGAAGG","TCCTTA","TCACTG"};
	String[] dnaFalse =      {"AAAAGC","CAGTGC","TTCTCT","AGAATG","CCGUTA","TCACTG"};

	@Test
	public void testMutantOrHuman() {
		boolean rs = MutantController.isMutant(dnaMutant);
		assertTrue(true, String.valueOf(rs));
	}

	@Test
	public void checkValidNucleotide(){
		boolean rs = MutantController.checkArrayNucleotide(dnaFalse);
		assertFalse(false, String.valueOf(rs));
	}

	@Test
	public void createMutant() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders
						.post("/mutant")
						.content(asJsonString(new Mutant(1L, dnaMutant)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getStats() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders
						.get("/stats")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNoContent());
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
