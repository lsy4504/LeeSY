package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
@Controller
public class BoardDeleteController  {
	@Inject
	IBoardService service;
	
	@RequestMapping("/board/boardDelete.do")	
	public String process(@ModelAttribute("board") BoardVO board,RedirectAttributes redirectAttributes,HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
			ServiceResult res=	service.removeBoard(board);
			System.out.println(res.name());
			switch (res) {
			case OK:
				return "redirect:/board/boardList.do";
			case INVALIDPASSWORD:
				redirectAttributes.addFlashAttribute("message","비번얼휴");
				return "redirect:/board/boardView.do?what="+board.getBo_no();
				
			default:
				redirectAttributes.addFlashAttribute("message","서버얼휴");
				return "redirect:/board/boardView.do?what="+board.getBo_no();
			}
			
		
	}
}
