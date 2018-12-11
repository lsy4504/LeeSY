package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class LoginController {
	@URIMapping(value="/common/login.do",method=HttpMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session=req.getSession(); 
			String mem_id=req.getParameter("mem_id");
			String mem_pass=req.getParameter("mem_pass");
			String idChecked=req.getParameter("idChecked");
			System.out.println("응???");
			RequestDispatcher rd= null;
			String goPage= null;
//		
			if(mem_id==null || mem_id.trim().length()==0 || mem_pass==null||mem_pass.trim().length()==0){
				goPage="redirect:/?commend=login";
				session.setAttribute("message", "아이디나 비밀번호가 누락됨 ㅎ");
			}else{
				IAuthenticateService service = new AuthenticateServiceImpl();
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
				session.setAttribute("message", "노존재!");
				}else{
				goPage="redirect:/?commend=login";
				session.setAttribute("message", "비번 다름");
					
				}
			}
		return goPage;
		
	}
	@URIMapping(value="/common/login.do",method=HttpMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session =req.getSession();
		session.invalidate();
		//세션스코프안에있는 모 든 객체 삭제
		//요청이 새로들어오면 새로운 세션생성
		//이동(index.jsp)
		return "redirect:/";
	}
}
