package kr.or.ddit.member.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.TestConfiguration;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
@RunWith(SpringJUnit4ClassRunner.class)
@TestConfiguration
public class AuthenticateServiceImplTest {
	//test double: 테스트 대상 객체를 가장하고 있는 객체
	// mock(행위 검증) ,stub(상태 검증)
	@Mock
	IMemberDAO mockDAO;  
	@InjectMocks
	AuthenticateServiceImpl service;
	//테스트 하네스: 테스트 대상 객체를 테스트하기 위해 필요한 코드로 생성된 데이터
	MemberVO mockMember;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMember=mock(MemberVO.class);
		when(mockMember.getMem_id()).thenReturn("a001");
		when(mockMember.getMem_pass()).thenReturn("1234");
		verifyZeroInteractions(mockDAO);
	}

	@Test
	public void testAuthenticate() {
		//상태 검증을 위한준비
		when(mockDAO.selectMember(isA(String.class))).thenReturn(null);
		Object result= service.authenticate(mockMember);
		//상태검증
		assertThat(result,Matchers.equalTo(ServiceResult.PKNOTFOUND));
		MemberVO savedMock= mock(MemberVO.class);
		when(savedMock.getMem_pass()).thenReturn("1234");
		when(mockDAO.selectMember(isA(String.class))).thenReturn(savedMock);
		result= service.authenticate(mockMember);
		assertThat(result, Matchers.instanceOf(MemberVO.class));
		//행위 검증
		verify(mockDAO, times(2)).selectMember(isA(String.class));
		
	}

}
