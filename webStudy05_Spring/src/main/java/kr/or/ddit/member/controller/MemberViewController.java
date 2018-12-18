package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
@CommandHandler
public class MemberViewController {
	@URIMapping("/member/memberView.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("who");
		if (StringUtils.isBlank(mem_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		IMemberSerivce serivce = MemberServiceImpl.getInstance();
		String view = "member/memberView";
		System.out.println(mem_id);
		int status = 0;
		MemberVO member = null;
		member = serivce.retrieveMember(mem_id);
		req.setAttribute("member", member);

		return view;

	}
}
