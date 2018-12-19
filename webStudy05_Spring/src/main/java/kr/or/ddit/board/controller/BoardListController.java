package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
@Controller
@RequestMapping(value="/board/boardList.do")
public class BoardListController {
	@Inject
	IBoardService service;
	@RequestMapping()
	public String process(@ModelAttribute("pagingVO")PagingInfoVO<BoardVO> pagingVO,@RequestParam(required=true,defaultValue="1",name="page")int currentPage
			,Model model
			) throws IOException, ServletException {
		long totalRecord=service.retriveBoardCount(pagingVO);
		System.out.println(totalRecord);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<BoardVO> boardList= service.retriveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		
		return "board/boardList";
	}
	@ResponseBody
	@RequestMapping(produces="application/json;charset=UTF-8")
	public PagingInfoVO<BoardVO> postProcess(@ModelAttribute("pagingVO")PagingInfoVO<BoardVO> pagingVO,@RequestParam(required=true,defaultValue="1",name="page")int currentPage
			,Model model) throws IOException, ServletException {
		long totalRecord=service.retriveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<BoardVO> boardList= service.retriveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		return pagingVO;
		
	}
	

}
