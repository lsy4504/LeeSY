package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;

@CommandHandler
public class BoardBoomUpController  {
	IBoardService service= new BoardServiceImpl();
	Map<String, String> boomMap=new HashMap<>();
	@URIMapping(value="/board/boomUp.do", method=HttpMethod.POST)
	public String boomUp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String bo_noStr=req.getParameter("bo_no");
		if(!StringUtils.isNumeric(bo_noStr)) {
			resp.sendError(resp.SC_BAD_REQUEST);
		}
		ServiceResult res=service.boomUp(Long.parseLong(bo_noStr));
		
		switch (res) {
		case OK:
			HttpSession session= req.getSession();
			session.setAttribute("check", "true");
			boomMap.put("flag", "true");
			break;

		case FAILED:
			boomMap.put("flag", "false");
			break;
		}
		
		ObjectMapper mapper=new ObjectMapper();
		try(
				PrintWriter out = resp.getWriter();
				) {
			
			mapper.writeValue(out, boomMap);
		} 
		
		
		return null;
		
	}
}
