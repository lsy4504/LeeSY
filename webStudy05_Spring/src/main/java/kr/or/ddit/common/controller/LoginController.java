package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginController {
	@Inject
	IAuthenticateService service;
	
	@RequestMapping(value="/common/login.do",method=RequestMethod.POST)
	public String login(
			@RequestParam(required=true) String mem_id,
			@RequestParam(required=true)String mem_pass, 
			@RequestParam(required=false)String idChecked,
			HttpServletRequest req, RedirectAttributes redirectAttributes
			,HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session=req.getSession(); 
			String goPage= null;
//		
			
				Object result=service.authenticate(new MemberVO(mem_id,mem_pass));
				if(result instanceof MemberVO){
					goPage="redirect:/";
					session.setAttribute("authMember", result);
					Cookie cookie=null;
					int maxAge=0;
					if(StringUtils.isNotBlank(idChecked)){
						maxAge=60*60*24*7;
					}
						cookie = CookieUtil.createCookie("id",mem_id,req.getContextPath(),TextType.PATH,maxAge);
					
						resp.addCookie(cookie);
				}else if(result == ServiceResult.PKNOTFOUND){
				goPage="redirect:/?commend=login";
				redirectAttributes.addFlashAttribute("message", "노존재!");
				}else{
				goPage="redirect:/?commend=login";
				redirectAttributes.addFlashAttribute("message", "비번 다름");
					
				}
		System.out.println(goPage);
		return goPage;
		
	}
	@RequestMapping(value="/common/login.do",method=RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		HttpSession session =req.getSession();
		session.invalidate();
		//세션스코프안에있는 모 든 객체 삭제
		//요청이 새로들어오면 새로운 세션생성
		//이동(index.jsp)
		return "redirect:/";
	}
}
