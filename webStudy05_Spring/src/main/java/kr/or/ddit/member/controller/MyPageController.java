package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MyPageController {
	@Inject
	IMemberSerivce serivce;
	
	//지렁이 .do 가아님 jsp로감..
	@RequestMapping("/member/mypage.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		MemberVO member=serivce.retrieveMember(mem_id);
		String view="member/memberView";
		req.setAttribute("member", member);
		return view;
	
	}
}
