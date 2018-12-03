package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.MemberVO;
public class MemberDeleteController implements ICommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		IMemberSerivce serivce=MemberServiceImpl.getInstance();
		MemberVO member= new MemberVO();
		req.setAttribute("member", member);
/*		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CommonException(e);
		}
		*///속도면에서 빈유틸즈는 비효율적임..
		String mem_id=req.getParameter("mem_id");
		String mem_pass=req.getParameter("mem_pass");
		if(StringUtils.isBlank(mem_id)||StringUtils.isBlank(mem_pass)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
//		member.setMem_id(mem_id);
//		member.setMem_pass(mem_pass);
		String message=null;
		String gopage="member/memberView";
//		Map<String,String> errors=new HashMap<>();
//		req.setAttribute("errors", errors);
//		boolean valid= validate(member,errors);
//		System.err.println(errors.size());
//		if(valid){
			ServiceResult result=serivce.removeMember(new MemberVO(mem_id, mem_pass));
			switch(result){
			case PKDUPLICATED:
				message="노존재 아이디..., 바꿔용";
				break;
			case FAILED:
				message="서버 오류입니다..";
//				gopage=req.getContextPath()+"/member/memberView.do?who="+mem_id;
				gopage="redirect:/member/mypage.do";
				break;
			case INVALIDPASSWORD:
				System.out.println("나오라..");
				message="비밀번호 틀림..";
//				gopage=req.getContextPath()+"/member/memberView.do?who="+mem_id;
				gopage="redirect:/member/mypage.do";
				break;
			case OK:
//				gopage=req.getContextPath()+"/member/memberList.do";
				gopage="redirect:/common/message.jsp";
				message="탈퇴 약관 : 일 주일 내에서 같은아이디 가입불가능..";
				req.getSession().setAttribute("goLink", "/");
				req.getSession().setAttribute("isRemoved", new Boolean(true));
//				req.getSession().invalidate(); 모든세션종료
				break;
			}
			req.getSession().setAttribute("message", message);
//		}else{
			
//		}

			return gopage;
			
			
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
