package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;
@CommandHandler
public class BoardViewController {
	@URIMapping("/board/boardView.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method=req.getMethod();
		
		if("get".equalsIgnoreCase(method)) {
			System.out.println("????");
			return doGet(req,resp);
			
		}
		return null;
	}

	private String doGet(HttpServletRequest req, HttpServletResponse resp) {
		IBoardService service= new BoardServiceImpl();
		String what=req.getParameter("what");
		String goPage="";
		if(StringUtils.isNotBlank(what)) {
			System.out.println("뭐야 설마");
			long bo_no=Long.parseLong(what);
			BoardVO board= service.retriveBoard(bo_no);
			req.setAttribute("board", board);
			goPage="board/boardView";
			PagingInfoVO<ReplyVO> pagingVO=new PagingInfoVO<>();
			pagingVO.setCurrentPage(1);
			IReplyService replyService=new ReplyServiceImpl();
			ReplyVO searchVO=new ReplyVO();
			searchVO.setBo_no(bo_no);
			pagingVO.setSearchVO(searchVO);
			long totalRecored=replyService.retriveReplyCount(pagingVO);
			pagingVO.setTotalRecord(totalRecored);
			pagingVO.setDataList(replyService.retriveReplyList(pagingVO));
			req.setAttribute("pagingVO", pagingVO);
			return goPage;
		}
		return null;
		
	}
	
	
}
