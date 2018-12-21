package kr.or.ddit.board.dao;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.TestConfiguration;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
//@Transactional
@TestConfiguration
public class BoardDAOImplTest {
	static Logger logger= LoggerFactory.getLogger(BoardDAOImplTest.class);
	@Inject
	IBoardDAO boardDao;
	PagingInfoVO<BoardVO> paging;
	BoardVO board;
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
//		paging.setSearchType("content");
//		paging.setSearchWord("은대");
		board=new BoardVO();
		board.setBo_no(new Long(1087));
		board.setBo_title("수정 수정 크리스탈");
		board.setBo_content("크리스탈 수정 수정");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("단위테스트 모듈 수행 후!!");
	}

	@Test
	public void testInsertBoard() {
		board.setBoard_writer("아잉이");
		board.setBo_pass("123");
		board.setBo_ip("123.123.123.123");
		board.setBo_mail("asd");
		int cnt=boardDao.insertBoard(board);
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
		test.setBo_no((long)1087);
		test.setBoard_writer("아잉이");
		assertThat(boardList, hasItem(test));
	}
	@Test
	public void testSelectBoard() {
		BoardVO board= boardDao.selectBoard(1087);
		assertNotNull(board);
//		assertNull("조회된 글은 null 이 아닌거같은데?",board);
		assertThat(board, instanceOf(BoardVO.class));
		assertThat(board, notNullValue());
	}

//	@Test(timeout=1000, expected=SQLException.class)
	@Test
	public void testIncrementHit() {
		boardDao.incrementHit(1087);
	}


	@Test
	public void testUpdateBoard() {
		int rowCnt=boardDao.updateBoard(board);
		assertEquals(1, rowCnt);
		
	}

	@Test
	public void testDeleteBoard() {
		int rowCnt=boardDao.deleteBoard(board.getBo_no());
		assertEquals(1, rowCnt);
	}

}
