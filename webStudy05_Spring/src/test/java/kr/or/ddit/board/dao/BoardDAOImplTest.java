package kr.or.ddit.board.dao;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BoardDAOImplTest {
	static Logger logger= LoggerFactory.getLogger(BoardDAOImplTest.class);
	IBoardDAO boardDao= new BoardDAOImpl();
	PagingInfoVO<BoardVO> paging;
	BoardVO board;
	SqlSession	session;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.printf("%s 테스트 클래스 로딩 \n",BoardDAOImplTest.class.getSimpleName());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.printf("%s 테스트 클래스 로딩 \n",BoardDAOImplTest.class.getSimpleName());
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("단위테스트 모듈 수행 전!!");
		paging=new PagingInfoVO<>();
		paging.setSearchType("content");
		paging.setSearchWord("은대");
		board=new BoardVO();
		board.setBo_no(new Long(171));
		board.setBo_title("수정 수정 크리스탈");
		board.setBo_content("크리스탈 수정 수정");
		session=CustomSqlSessionFactoryBuilder.getSqlSessionFactory().openSession(false);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("단위테스트 모듈 수행 후!!");
		session.close();
	}

	@Test
	public void testInsertBoard() {
		board.setBoard_writer("아잉이");
		board.setBo_pass("123");
		board.setBo_ip("123.123.123.123");
		board.setBo_mail("asd");
		int cnt=boardDao.insertBoard(board, session);
		assertEquals(1, cnt);
	}

	@Test
	public void testSelectTotalRecord() {
		long totalRecord= boardDao.selectTotalRecord(paging);
		assertNotSame(0, totalRecord);
	}

	@Test
	public void testSelectBoardList() {
		paging.setCurrentPage(1);
	List<BoardVO> boardList=boardDao.selectBoardList(paging);
		assertNotNull(boardList);
		assertNotSame(0, boardList.size());
		assertThat(boardList.size(), greaterThan(0));
		BoardVO test= new BoardVO();
		test.setBo_no((long)171);
		test.setBoard_writer("김은대");
		assertThat(boardList, hasItem(test));
	}
	@Test
	public void testSelectBoard() {
		BoardVO board= boardDao.selectBoard(171);
		assertNotNull(board);
//		assertNull("조회된 글은 null 이 아닌거같은데?",board);
		assertThat(board, instanceOf(BoardVO.class));
		assertThat(board, notNullValue());
	}

//	@Test(timeout=1000, expected=SQLException.class)
	@Test
	public void testIncrementHit() {
		boardDao.incrementHit(171);
	}

	@Test
	public void testIncrementRcmd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBoard() {
		int rowCnt=boardDao.updateBoard(board, session);
		assertEquals(1, rowCnt);
		
	}

	@Test
	public void testDeleteBoard() {
		int rowCnt=boardDao.deleteBoard(board.getBo_no(), session);
		assertEquals(1, rowCnt);
	}

}
