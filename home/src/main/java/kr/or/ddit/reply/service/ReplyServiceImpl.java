package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.reply.dao.IReplyDAO;
import kr.or.ddit.reply.dao.ReplyDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyBookVO;

public class ReplyServiceImpl implements IReplyService {
	IReplyDAO dao=new ReplyDAOImpl();
	
	@Override
	public List<ReplyBookVO> listReplyBook(PagingVO<ReplyBookVO> paging) {
		return dao.selectReplyList(paging);
	}

	@Override
	public ReplyBookVO selectReplyBook(Long rep_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult insertReplyBook(ReplyBookVO replyBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult deleteReplyBook(ReplyBookVO rep_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult updatereplyBook(ReplyBookVO replyBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long retriveReplyCount(PagingVO<ReplyBookVO> pagingVO) {
		
		return dao.selectTotalRecord(pagingVO);
	}

}
