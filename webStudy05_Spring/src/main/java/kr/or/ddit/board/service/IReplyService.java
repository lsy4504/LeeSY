package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * @author lsy
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       덧글 관리용 Buisiness Logic layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IReplyService {
	
	/**
	 * 덧글 작성
	 * @param reply
	 * @return OK,FAiled
	 */
	public ServiceResult createReply(ReplyVO reply);
	/**
	 * 특정게시글의 덧글 수
	 * @param pagingVO
	 * @return 없다ㅕㅁㄴ 0
	 */
	public long retriveReplyCount(PagingInfoVO<ReplyVO> pagingVO);
	/**
	 * 특정게시글의 덧글 목록
	 * @param pagingVO
	 * @return 없 size==00
	 */
	public List<ReplyVO> retriveReplyList(PagingInfoVO<ReplyVO> pagingVO);
	/**
	 * 수정
	 * @param reply
	 * @return boardException , invalidpassword ok failed
	 */
	public ServiceResult modifyReply(ReplyVO reply);
	/**
	 * 삭제
	 * @param reply
	 * @return boardException , invalidpassword ok failed
	 */
	public ServiceResult removeReply(ReplyVO reply);
}
