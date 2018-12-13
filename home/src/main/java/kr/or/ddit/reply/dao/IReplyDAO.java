package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyBookVO;

public interface IReplyDAO {
	public int insertReply(ReplyBookVO reply);

	public long selectTotalRecord(PagingVO<ReplyBookVO> pagingVO);

	public List<ReplyBookVO> selectReplyList(PagingVO<ReplyBookVO> pagingVO);

	public ReplyBookVO selectReply(long req_no);

	public int updateReply(ReplyBookVO reply);

	public int deleteReply(long rep_no);
}
