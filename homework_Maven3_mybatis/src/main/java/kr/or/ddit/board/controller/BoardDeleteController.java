package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ICommandHandler;

public class BoardDeleteController implements ICommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String bo_noStr= req.getParameter("bo_no");
		if(StringUtils.isNumeric(bo_noStr)) {
			Long bo_no=Long.parseLong(bo_noStr);
		}
		return null;
	}
}
