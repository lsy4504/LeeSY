package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.hamcrest.core.Is;
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
@testCon
public class BoardInsertControllerTest {
	@Inject
    private WebApplicationContext container;

    private MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		mockMvc=MockMvcBuilders.webAppContextSetup(container).build();
	}

	@Test
	public void testGetProcess() throws Exception {
		mockMvc.perform(get("/board/boardInsert.do"))
		.andExpect(status().isOk())
		.andDo(log())
		.andReturn();
	}

	@Test
	public void testPostProcess() throws Exception {
		mockMvc.perform(post("/board/boardInsert.do")
				.param("bo_writer", "작성자")
				.param("bo_title", "제목")
				)
		.andExpect(status().isOk())
		.andReturn();
	}

}
