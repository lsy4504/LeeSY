package kr.or.ddit.member.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.CommonException;
import kr.or.ddit.testCon;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
/**
 * 테스트더블: 테스트 대상이 되는 객체를 가장하고 있는 가짜 객체(Mockito/EasyMock 등의 프레임워크 사용)
 * 1) Mock: 행위 검증을 목적으로 어떤 메소드를 몇번이나 호출했는지 등에 대한 정보만을 설정할때 사용하는 가장 객체
 * 			@mock, mock()
 * 2) Stub : 상태 검증을 목적으로 어떤 상황에서 어떤 상태값을 가정하겠다라는 가정 정보만을 설정할때 사용하는 객체
 * 			when(mock.methodCall()).then[Return|Throw]
 * 3) spy  : 실제 테스트 대상 객체를 wrapping 해서 만드는 mock 객체
 * 				@Spy, spy()
 * 테스트 하네스: 테스트 자체만을 목적으로 만들어진 코드나 데이터
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@testCon
public class MemberRetrieveControllerTest {
	@Mock
	IMemberSerivce mockService;
	@InjectMocks
	MemberRetrieveController controller;
//	@Inject
//	WebApplicationContext container;
	MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//		mockMvc=MockMvcBuilders.webAppContextSetup(container).build();
	}

	@Test
	public void testMemberList() throws Exception {
		//상태검증
		when(mockService.retribeMemberCount(isA(PagingInfoVO.class))).thenReturn((long)100);
		when(mockService.retrieveMemberList(isA(PagingInfoVO.class))).thenReturn(mock(List.class));
		MvcResult mvcResult= mockMvc.perform(get("/member/memberList.do")
				.param("page", "500")
				)
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("pagingVO"))
		.andExpect(view().name("member/memberList"))
		.andDo(log())
		.andReturn()
		;
		ModelAndView mav= mvcResult.getModelAndView();
		PagingInfoVO<MemberVO> pagingVO= (PagingInfoVO<MemberVO>) mav.getModel().get("pagingVO");
		//상태검증
		assertNotNull(pagingVO);
		assertNotNull(pagingVO.getDataList());
		assertEquals(0, pagingVO.getDataList().size());
		//행위검증
		verify(mockService,times(1)).retribeMemberCount(isA(PagingInfoVO.class));
		verify(mockService,times(1)).retrieveMemberList(isA(PagingInfoVO.class));
	}

	@Test
	public void testMemberView() throws Exception {
		
		//who 파라미터가 없었을때 상황
		mockMvc.perform(get("/member/memberView.do"))
		.andExpect(status().isBadRequest())
		.andDo(log())
		.andReturn();
		verifyNoMoreInteractions(mockService);
		//who 파라미터 있을떄
		when(mockService.retrieveMember(isA(String.class))).thenReturn(isA(MemberVO.class));
		mockMvc.perform(get("/member/memberView.do").param("who", "a001"))
		.andExpect(status().isOk())
		.andDo(log())
		.andReturn();
		verify(mockService,times(1)).retrieveMember(isA(String.class));
		//who는 있는뎁 해당 회원 노존재
		when(mockService.retrieveMember(isA(String.class))).thenThrow(CommonException.class);
		MvcResult mvcResult= (MvcResult) mockMvc.perform(get("/member/memberView.do").param("who", "aa001"))
		.andDo(log())
		.andExpect(status().isInternalServerError())
		.andReturn();
		
//		ModelAndView mav=mvcResult.getModelAndView();
//		MemberVO member= (MemberVO) mav.getModel().get("member");
//		assertNull(member);
		verify(mockService,times(2)).retrieveMember(isA(String.class));
		
		
	}

}
