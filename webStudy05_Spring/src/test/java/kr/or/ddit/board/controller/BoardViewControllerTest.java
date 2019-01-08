package kr.or.ddit.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.testCon;
@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextHierarchy({
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml"),
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})*/
//@WebAppConfiguration
@testCon
public class BoardViewControllerTest {
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		  this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/board/boardView.do").param("what", "1087"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/boardView"))
		.andExpect(model().attributeExists("board"))
		.andDo(log())
		.andReturn();
		
	}
		@Test
		public void test1() throws Exception {
			mockMvc.perform(get("/board/boardView.do").param("what", "9999"))
			.andExpect(status().is(400))
			.andDo(log())
			.andReturn();
			
	}

}
