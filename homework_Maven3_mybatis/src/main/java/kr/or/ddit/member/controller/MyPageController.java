package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.MemberVO;
public class MyPageController implements ICommandHandler{
	
	
	//지렁이 .do 가아님 jsp로감..
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getSession();
		
		//최초의요청이면 jsessionID가 없음
		//그러면 가상의 아이디를 넘겨줌
		
		HttpSession session= req.getSession(false);
		//최초의 요청인경우 세션 생성 ㄴ
		if(session==null || session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "로그인 한 이후에만 요청 가능. ");
			return null;
		}
		MemberVO authMember=(MemberVO) session.getAttribute("authMember");
		// 마이페이지 컨트롤러가 안전하게 동작하기 위해서는 
		// 전단계에서 로그인 여부를 미리 확인해야함 ( 필터?)........--------filter 활용
		String mem_id=authMember.getMem_id();
		IMemberSerivce serivce= MemberServiceImpl.getInstance();
		MemberVO member=serivce.retrieveMember(mem_id);
		String view="member/memberView";
		req.setAttribute("member", member);
		return view;
	
	}
}
