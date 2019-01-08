package kr.or.ddit.member.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.ddit.AbstractControllerTestCase;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.vo.MemberVO;

public class MyPageControllerTest extends AbstractControllerTestCase{
	@Mock
	IMemberSerivce mockService;
	
	@InjectMocks
	MyPageController controller;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		verifyZeroInteractions(mockService);
	}

	@Test
	public void testProcess() throws Exception {
		mockMvc.perform(get("/member/mypage.do"))
		.andExpect(status().isBadRequest())
		.andDo(log())
		.andReturn();
		MockHttpSession mockSession= new MockHttpSession();
		mockSession.setNew(false);
		MemberVO mockMember= mock(MemberVO.class);
		when(mockMember.getMem_id()).thenReturn("a001");
		mockSession.setAttribute("authMember", mockMember);
		when(mockService.retrieveMember(isA(String.class))).thenReturn(mock(MemberVO.class));
		mockMvc.perform(get("/member/mypage.do")
				.session(mockSession)
				).andExpect(status().isOk())
		.andExpect(view().name("member/memberView"))
		.andExpect(request().attribute("member", Matchers.notNullValue()))
		.andDo(log())
		.andReturn();
		
	}

}
