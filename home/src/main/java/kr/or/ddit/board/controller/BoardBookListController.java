package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardBookServiceImpl;
import kr.or.ddit.board.service.IBoardBookService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.vo.BoardBookVO;
import kr.or.ddit.vo.PagingVO;

@CommandHandler
public class BoardBookListController {
	IBoardBookService service= new BoardBookServiceImpl();
	@URIMapping(value="/boardBook/boardBookList.do",method=HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String pageStr=req.getParameter("page");
		int currentPage=1;
		if(!StringUtils.isNumeric(pageStr)) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}else {
			currentPage=Integer.parseInt(pageStr);
		}
		PagingVO<BoardBookVO> paging=new PagingVO<>();
		long totalCount=service.totalRecordCount();
		paging.setTotalRecord(totalCount);
		paging.setCurrentPage(currentPage);
		List<BoardBookVO> boardBookList=service.listBoardBook(paging);
		paging.setDataList(boardBookList);
		String accept=req.getHeader("accept");
		if(!StringUtils.containsIgnoreCase(accept, "json")) {
			req.setAttribute("paging", paging);
			return "boardBook/boardBook";
		}
		System.out.println("우야야야야");
		ObjectMapper mapper=new ObjectMapper();
		try(
				PrintWriter out =resp.getWriter();
				)
		{
			mapper.writeValue(out, paging);
			
		}
		
		return null;
	}
	@URIMapping(value="/boardBook/boardBookList.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return doGet(req, resp);
	}
}
