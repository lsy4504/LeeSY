package kr.or.ddit.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyBookVO;

public class ReplyDAOImpl implements IReplyDAO {
	SqlSessionFactory sqlSessionFactory=CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public int insertReply(ReplyBookVO reply) {
		return 0;
	}

	@Override
	public long selectTotalRecord(PagingVO<ReplyBookVO> pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<ReplyBookVO> selectReplyList(PagingVO<ReplyBookVO> pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyList(pagingVO);
		}
	}

	@Override
	public ReplyBookVO selectReply(long req_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReply(ReplyBookVO reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(long rep_no) {
		// TODO Auto-generated method stub
		return 0;
	}
}
