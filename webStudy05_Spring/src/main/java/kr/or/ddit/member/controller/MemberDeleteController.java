package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
@Controller
public class MemberDeleteController {
	@Inject
	IMemberSerivce serivce;
	@RequestMapping("/member/memberDelete.do")
	public ModelAndView process(@RequestParam(required=true) String mem_id,
			@RequestParam(required=true) String mem_pass,HttpServletRequest req,RedirectAttributes redirectAttributes
			) {
		ModelAndView mav =new ModelAndView();
		String message=null;
		String gopage="member/memberView";
		ServiceResult result=serivce.removeMember(new MemberVO(mem_id, mem_pass));
			switch(result){
			case PKDUPLICATED:
				message="노존재 아이디..., 바꿔용";
				break;
			case FAILED:
				message="서버 오류입니다..";
				gopage="redirect:/member/mypage.do";
				break;
			case INVALIDPASSWORD:
				System.out.println("나오라..");
				message="비밀번호 틀림..";
				gopage="redirect:/member/mypage.do";
				break;
			case OK:
				gopage="redirect:/common/message.jsp";
				message="탈퇴 약관 : 일 주일 내에서 같은아이디 가입불가능..";
				req.getSession().setAttribute("goLink", "/");
				req.getSession().setAttribute("isRemoved", new Boolean(true));
				break;
			}
			mav.setViewName(gopage);
			redirectAttributes.addFlashAttribute("message", message);
			return mav;
			
			
	}
	private boolean validate(MemberVO member, Map<String,String> errors){
		boolean valid= true;
		System.out.print("비번검증");
		
		//검증 룰..
		if(StringUtils.isBlank(member.getMem_id())){
		valid=false;
		errors.put("mem_id", ">회원아이디 미입력 .... <");
		}
		if(StringUtils.isBlank(member.getMem_pass())){
		valid=false;
		System.out.print("비번검증");
		errors.put("mem_pass", ">비밀번호 미입력 .... <");
		}
		
		
		
		return valid;
	
	}
}
