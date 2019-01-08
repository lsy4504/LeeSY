package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import org.hamcrest.Matchers;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.ddit.AbstractControllerTestCase;
import kr.or.ddit.testCon;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdListControllerTest extends AbstractControllerTestCase {
	@Mock
	IProdService mockService;
	@Mock
	IOtherDAO mockOtherDAO;

	@InjectMocks
	ProdListController controller;
	
	@Override
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testListAsync() throws Exception {
		List<ProdVO> prodList=mock(ArrayList.class);
//		when(prodList.get(0)).thenReturn(mock(ProdVO.class));
//		when(prodList.size()).thenReturn(1);
		when(mockService.retrieveProdList(isA(PagingInfoVO.class))).thenReturn(prodList);
		mockMvc.perform(get("/prod/prodList.do")
				.accept(MediaType.APPLICATION_JSON).param("page", "1")
				).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.dataList", Matchers.notNullValue()))
				.andExpect(jsonPath("$.dataList").isArray())
				.andExpect(jsonPath("$.dataList").isEmpty())
				.andExpect(jsonPath("$.totalRecord").value(0))
				.andDo(log())
				.andReturn();
	}

	@Test
	public void testProcess() throws Exception {
		when(mockService.retrieveProdCount(isA(PagingInfoVO.class))).thenReturn((long)10);
		when(mockService.retrieveProdList(isA(PagingInfoVO.class))).thenReturn(mock(List.class));
		when(mockOtherDAO.selectBuyerList(isA(String.class))).thenReturn(null);
		mockMvc.perform(get("/prod/prodList.do")
				.param("prod_name", "컴퓨터")
				).andExpect(status().isOk())
				.andExpect(model().attributeExists("lprodList","buyerList","pagingVO"))
				.andExpect(view().name("prod/prodList"))
				.andDo(log())
				.andReturn();
		verify(mockService,atLeast(1)).retrieveProdCount(isA(PagingInfoVO.class));
		verify(mockService,times(1)).retrieveProdList(isA(PagingInfoVO.class));
		verify(mockOtherDAO,atLeastOnce()).selectLprodList();
		verify(mockOtherDAO,atLeastOnce()).selectBuyerList(null);
	}

}
