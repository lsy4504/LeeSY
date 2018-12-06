package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDAOImpl implements IReplyDAO{
	SqlSessionFactory sqlSessionFactory=CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertReply(ReplyVO reply) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			int cnt = mapper.insertReply(reply);
			if(cnt>0) session.commit();
			return cnt;
		}
	}

	@Override
	public long selectTotalRecord(PagingInfoVO<ReplyVO> pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}

	}

	@Override
	public List<ReplyVO> selectReplyList(PagingInfoVO<ReplyVO> pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyList(pagingVO);
		}
	}

	@Override
	public ReplyVO selectReply(long req_no) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReply(req_no);
		}
	}

	@Override
	public int updateReply(ReplyVO reply) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			int cnt=mapper.updateReply(reply);
			if(cnt>0)session.commit();
			return cnt;
		}
	}
	

	@Override
	public int deleteReply(long rep_no) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			int cnt=mapper.deleteReply(rep_no);
			if(cnt>0) session.commit();
			return  cnt;
		}
	}
}
