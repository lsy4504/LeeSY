package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;
@Controller
public class BoardViewController {
	@Inject
	IBoardService service;
	@Inject
	IReplyService replyService;
	@RequestMapping("/board/boardView.do")
	private String doGet(@RequestParam(required=true,name="what")long bo_no,Model model) {
		String goPage="";
			System.out.println("뭐야 설마");
			BoardVO board= service.retriveBoard(bo_no);
			model.addAttribute("board", board);
			goPage="board/boardView";
			PagingInfoVO<ReplyVO> pagingVO=new PagingInfoVO<>();
			pagingVO.setCurrentPage(1);
			ReplyVO searchVO=new ReplyVO();
			searchVO.setBo_no(bo_no);
			pagingVO.setSearchVO(searchVO);
			long totalRecored=replyService.retriveReplyCount(pagingVO);
			pagingVO.setTotalRecord(totalRecored);
			pagingVO.setDataList(replyService.retriveReplyList(pagingVO));
			model.addAttribute("pagingVO", pagingVO);
			return goPage;
		
	}
	
	
}
