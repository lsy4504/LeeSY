package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import kr.or.ddit.TestConfiguration;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
@RunWith(SpringJUnit4ClassRunner.class)
@TestConfiguration
public class IMemberDAOTest {
	@Inject
	IMemberDAO memberDAO;
	PagingInfoVO<MemberVO> pagingVO;
	@Mock
	MemberVO mockMember;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pagingVO= mock(PagingInfoVO.class);
		when(pagingVO.getSearchType()).thenReturn("name");
		when(pagingVO.getSearchWord()).thenReturn("은대");
		when(pagingVO.getCurrentPage()).thenReturn((long)1);
		when(pagingVO.getStartRow()).thenReturn((long)1);
		when(pagingVO.getEndRow()).thenReturn((long)10);
		when(mockMember.getMem_id()).thenReturn("a001");
		when(mockMember.getMem_add1()).thenReturn("대전 대흥동");
	}

	@Test
	public void testSelectMember() {
		MemberVO memberVO= memberDAO.selectMember("a001");
		assertNotNull(memberVO);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList=memberDAO.selectMemberList(pagingVO);
		assertEquals(1, memberList.size());
	}

	@Test(expected=Exception.class)
	public void testInsertMember() {
		int rowCnt=memberDAO.insertMember(mockMember);
		
	}

	@Test
	public void testUpdateMember() {
		int rowCnt=memberDAO.updateMember(mockMember);
		assertEquals(1, rowCnt);
	}
	@Test(expected=Exception.class)
	public void testUpdateMemberWithException() {
		when(mockMember.getMem_add1()).thenReturn(null);
		int rowCnt=memberDAO.updateMember(mockMember);
	}

	@Test
	public void testDeleteMember() {
		int rowCnt=memberDAO.deleteMember("a001");
		assertEquals(1, rowCnt);
	}

	@Test
	public void testSelectTotalRecord() {
		long totalRecord=memberDAO.selectTotalRecord(pagingVO);
		assertThat(totalRecord, Matchers.greaterThan((long)0));
	}

}
