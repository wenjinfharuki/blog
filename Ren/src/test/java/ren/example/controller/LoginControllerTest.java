package ren.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ren.example.model.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void prepareData() {
		when(userService.createAccount(any(),any())).thenReturn(false);
		when(userService.createAccount("haruki","haruki098")).thenReturn(true);
	}
	
	@Test
	public void testGetLoginpaga_Successed() throws Exception{
		RequestBuilder request=MockMvcRequestBuilders
				.get("/login");
		
		mockMvc.perform(request)
		.andExpect(view().name("login.html"))
		.andExpect(model().attributeDoesNotExist("error"));
	}

}
