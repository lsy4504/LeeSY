package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.BoardException;
import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;
@Service
public class ReplyServiceImpl implements IReplyService{
	@Inject
	IReplyDAO replyDAO;
	@Override
	public ServiceResult createReply(ReplyVO reply) {
		int cnt=replyDAO.insertReply(reply);
		ServiceResult res=ServiceResult.FAILED;
		if(cnt>0) {
			res=ServiceResult.OK;
		}
		return res;
	}
	@Override
	public long retriveReplyCount(PagingInfoVO<ReplyVO> pagingVO) {
		return replyDAO.selectTotalRecord(pagingVO);
	}
	@Override
	public List<ReplyVO> retriveReplyList(PagingInfoVO<ReplyVO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}
	@Override
	public ServiceResult modifyReply(ReplyVO reply) {
		ReplyVO replyVO= replyDAO.selectReply(reply.getRep_no());
		ServiceResult result= ServiceResult.FAILED;
		if(replyVO==null) {
			throw new BoardException();
		}else if(replyVO.getRep_pass().equals(reply.getRep_pass())) {
			replyVO.setRep_content(reply.getRep_content());
			int res=replyDAO.updateReply(replyVO);
			if(res>0) {
				result=ServiceResult.OK;
			}
		}else {
			result=ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}
	@Override
	public ServiceResult removeReply(ReplyVO reply) {
		 ReplyVO replyVO=replyDAO.selectReply(reply.getRep_no());
		 ServiceResult result=ServiceResult.FAILED;
		 if(replyVO==null) {
			 throw new CommonException();
		 }else if(replyVO.getRep_pass().equals(reply.getRep_pass())){
			 int res=replyDAO.deleteReply(reply.getRep_no());
			 if(res>0) {
				 result=ServiceResult.OK;
			 }
		 }else {
			 result=ServiceResult.INVALIDPASSWORD;
		 }
			 
		return result;
		
	}
}
