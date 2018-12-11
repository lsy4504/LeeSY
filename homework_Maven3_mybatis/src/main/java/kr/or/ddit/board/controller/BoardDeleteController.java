package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.SynchronizedSet;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
@CommandHandler
public class BoardDeleteController  {
	IBoardService service = new BoardServiceImpl();
	@URIMapping(value="/board/boardDelete.do",method=HttpMethod.POST)	
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		BoardVO board= new BoardVO();
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(board.getBo_no()+"++++++++++++++");
		HttpSession session =req.getSession();
		
			ServiceResult res=	service.removeBoard(board);
			System.out.println(res.name());
			switch (res) {
			case OK:
				return "redirect:/board/boardList.do";
			case INVALIDPASSWORD:
				session.setAttribute("message","비번얼휴");
				return "redirect:/board/boardView.do?what="+board.getBo_no();
				
			default:
				session.setAttribute("message","서버얼휴");
				return "redirect:/board/boardView.do?what="+board.getBo_no();
			}
			
		
	}
}
