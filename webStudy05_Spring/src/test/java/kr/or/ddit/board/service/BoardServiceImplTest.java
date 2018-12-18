package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.BoardException;
import kr.or.ddit.board.dao.BoardDAOImplTest;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BoardServiceImplTest {
	IBoardService service= new BoardServiceImpl();
	BoardVO board;
	PagingInfoVO<BoardVO> paging;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.printf("%s 테스트 클래스 로딩 \n",BoardServiceImplTest.class.getSimpleName());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.printf("%s 테스트 클래스 소멸 \n",BoardServiceImplTest.class.getSimpleName());
	}

	@Before
	public void setUp() throws Exception {
		board=new BoardVO();
		paging=new PagingInfoVO<>();
		paging.setSearchType("content");
		paging.setSearchWord("은대");
		board=new BoardVO();
		board.setBo_no(new Long(171));
		board.setBo_title("수정 수정 크리스탈");
		board.setBo_content("크리스탈 수정 수정");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateBoard() {
		board.setBoard_writer("아잉이");
		board.setBo_pass("123");
		board.setBo_ip("123.123.123.123");
		board.setBo_mail("asd");
		service.createBoard(board);
	}

	@Test
	public void testRetriveBoardCount() {
		long totalRecord= service.retriveBoardCount(paging);
		assertNotSame(0, totalRecord);
	}

	@Test
	public void testRetriveBoardList() {
		paging.setCurrentPage(1);
		List<BoardVO> boardList=service.retriveBoardList(paging);
			assertNotNull(boardList);
			assertNotSame(0, boardList.size());
			assertThat(boardList.size(), greaterThan(0));
			BoardVO test= new BoardVO();
			test.setBo_no((long)171);
			test.setBoard_writer("김은대");
			assertThat(boardList, hasItem(test));
	}

	@Test
	public void testRetriveBoard() {
		BoardVO board= service.retriveBoard(171);
		assertNotNull(board);
//		assertNull("조회된 글은 null 이 아닌거같은데?",board);
		assertThat(board, instanceOf(BoardVO.class));
		assertThat(board, notNullValue());
	}

	@Test
	public void testModifyBoard() {
		board.setBo_no((long)171);
		ServiceResult res=service.modifyBoard(board);
		assertThat(res, equalTo(ServiceResult.INVALIDPASSWORD));
	}
	@Test(expected=BoardException.class)
	public void testModifyBoardException() {
		board.setBo_no((long)444444);
		ServiceResult res=service.modifyBoard(board);
		
	}

	@Test
	public void testRemoveBoard() {
		board.setBo_no((long)160);
		board.setBo_pass("3ngvoasbn");
		ServiceResult res=service.removeBoard(board);
		System.out.println("뭐야"+board.getBo_no());
		assertEquals(res, ServiceResult.INVALIDPASSWORD);
	}

	@Test(expected=BoardException.class)
	public void testDownLoadPds() {
		service.downLoadPds((long)177);
	}

	@Test
	public void testBoomUp() {
		fail("Not yet implemented");
	}

}
