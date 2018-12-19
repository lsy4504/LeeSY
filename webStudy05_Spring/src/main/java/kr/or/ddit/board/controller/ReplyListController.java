package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;
@RestController//자체적으로 responsbody 어노테이션 보유중...
public class ReplyListController {
	@Inject
	private IReplyService replyService;
	

	@RequestMapping(value="/reply/replyList.do",produces="application/json;charset=UTF-8")
	public PagingInfoVO<ReplyVO> process(@RequestParam(required=true)long bo_no,
			@RequestParam(required=false,defaultValue="1",name="page")int currentPage) {
		PagingInfoVO<ReplyVO> pagingVO=new PagingInfoVO<>();
		pagingVO.setCurrentPage(currentPage);
		ReplyVO searchVO=new ReplyVO();
		searchVO.setBo_no(bo_no);
		pagingVO.setSearchVO(searchVO);
		long totalRecored=replyService.retriveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecored);
		List<ReplyVO> replyList=replyService.retriveReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		
		return pagingVO;
	}

}
