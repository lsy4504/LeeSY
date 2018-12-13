package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyBookVO;

public interface IReplyService {
	public long retriveReplyCount(PagingVO<ReplyBookVO> pagingVO);
	
	public List<ReplyBookVO> listReplyBook(PagingVO<ReplyBookVO> paging);

	public ReplyBookVO selectReplyBook(Long rep_no);

	public ServiceResult insertReplyBook(ReplyBookVO replyBook);

	public ServiceResult deleteReplyBook(ReplyBookVO rep_no);

	public ServiceResult updatereplyBook(ReplyBookVO replyBook);

}
