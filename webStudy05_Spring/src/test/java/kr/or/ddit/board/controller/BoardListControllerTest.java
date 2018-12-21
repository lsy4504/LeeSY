package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.apache.velocity.io.VelocityWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml"),
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@WebAppConfiguration
@Transactional

public class BoardListControllerTest {
	 	@Autowired
	    private WebApplicationContext wac;

	    private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		   this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testProcess() throws Exception {
//		/board/boardList.do,page,searchType,searchwrod
		mockMvc.perform(get("/board/boardList.do").param("page", "2"))
			.andExpect(status().isOk())
			.andExpect(view().name("board/boardList"))
			.andExpect(model().attributeExists("pagingVO"))
			.andDo(log())
			.andReturn();
	}

	@Test
	public void testPostProcess() throws Exception {
		mockMvc.perform(get("/board/boardList.do")
				.param("page", "2")
				.accept(MediaType.APPLICATION_JSON)
				
				).andDo(log())
		
		.andExpect(status().isOk())
		.andReturn();
	}

}
