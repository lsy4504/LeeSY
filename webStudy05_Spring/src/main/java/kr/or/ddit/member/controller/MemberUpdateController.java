package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
@Controller
public class MemberUpdateController {
	@Inject
	IMemberSerivce serivce;
	
	@RequestMapping(value="/member/memberUpdate.do",method=RequestMethod.POST)
	public String process(@Validated(UpdateGroup.class) @ModelAttribute("member")MemberVO member,
			BindingResult errors,
			Model model
			 ) throws ServletException, IOException {

		
		
		
		String message=null;
		String gopage="member/memberView";
		
		boolean valid=!errors.hasErrors();
		if(valid){
				//경로, 이름,
			
		ServiceResult result=serivce.modifyMember(member);
			switch(result){
			case PKDUPLICATED:
				message="노존재 아이디..., 바꿔용";
				break;
			case FAILED:
				message="서버 오류입니다..";
				break;
			case INVALIDPASSWORD:
				System.out.println("나오라..");
				message="비밀번호 틀림..";
				break;
			case OK:
//				gopage=req.getContextPath()+"/member/memberView.do?who="+member.getMem_id();
				gopage="redirect:/member/mypage.do";
				break;
			}
			model.addAttribute("message", message);
		}
		return gopage;
		
	}

}
