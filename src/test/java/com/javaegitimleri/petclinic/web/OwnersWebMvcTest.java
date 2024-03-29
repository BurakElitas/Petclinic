package com.javaegitimleri.petclinic.web;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.boolex.Matcher;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@WithMockUser(username="user1",password="secret",authorities = "USER")
public class OwnersWebMvcTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testOwners() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilders= MockMvcRequestBuilders.get("/owners");
		ResultActions resultActions=mockMvc.perform(requestBuilders);
		
		MvcResult mvcResult= resultActions.andReturn();
		
		ModelAndView mav=mvcResult.getModelAndView();
		MatcherAssert.assertThat(mav.getViewName(), Matchers.equalTo("owners"));
		MatcherAssert.assertThat(mav.getModel().containsKey("owners"), Matchers.is(true));
		
	}
}
