package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
@CommandHandler
public class BoardListController {
	@URIMapping(value="/board/boardList.do",method=HttpMethod.GET)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PagingInfoVO<BoardVO> pagingVO=new PagingInfoVO<>();
		IBoardService service= new BoardServiceImpl();
		String searchWord=req.getParameter("searchWord");
		String searchType=req.getParameter("searchType");
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		long totalRecord=service.retriveBoardCount(pagingVO);
		int currentPage=1;
		String page= req.getParameter("page");
		if(StringUtils.isNumeric(page)) {
			currentPage=Integer.parseInt(page);
			
		}
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> boardList= service.retriveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		String accept= req.getHeader("Accept");
		if(accept.contains("json")) {
			System.out.println("혹시옵닊라?/");
			ObjectMapper mapper=new ObjectMapper();
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out=resp.getWriter();
			mapper.writeValue(out, pagingVO);
			return null;
		}
		req.setAttribute("pagingVO", pagingVO);
		
		
		return "board/boardList";
	}
	@URIMapping(value="/board/boardList.do", method=HttpMethod.POST)
	public String postProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		return process(req, resp);
	}
	

}
