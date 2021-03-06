package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardBookVO;
import kr.or.ddit.vo.PagingVO;

public class BoardBookDAOImpl implements IBoardBookDAO{
	private static SqlSessionFactory sqlSessionFactory;
	static{
		sqlSessionFactory=CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}

	@Override
	public long totalRecordCount() {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBoardBookDAO mapper = session.getMapper(IBoardBookDAO.class);
			return mapper.totalRecordCount();
		}
	}

	@Override
	public List<BoardBookVO> listBoardBook(PagingVO paging) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBoardBookDAO mapper = session.getMapper(IBoardBookDAO.class);
			return mapper.listBoardBook(paging);
		}
	}

	@Override
	public BoardBookVO selectBoardBook(Long bo_no) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBoardBookDAO mapper = session.getMapper(IBoardBookDAO.class);
			return mapper.selectBoardBook(bo_no);
		}
	}

	@Override
	public int insertBoardBook(BoardBookVO board) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBoardBookDAO mapper = session.getMapper(IBoardBookDAO.class);
			int cnt = mapper.insertBoardBook(board);
			if(cnt>0) session.commit();
			return  cnt;
		}
	}

	@Override
	public int deleteBoardBook(Long bo_no) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBoardBookDAO mapper = session.getMapper(IBoardBookDAO.class);
			int cnt=mapper.deleteBoardBook(bo_no);
			if(cnt>0)session.commit();
			return cnt;
		}
	}

	@Override
	public int updateBoardBook(BoardBookVO board) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			System.out.println("????");
			IBoardBookDAO mapper = session.getMapper(IBoardBookDAO.class);
			int cnt=mapper.updateBoardBook(board);
			if(cnt>0) session.commit();
			return cnt;
		}
	}

}
