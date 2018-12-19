package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
@Controller
public class MemberUpdateController {
	@Inject
	IMemberSerivce serivce;
	
	@RequestMapping(value="/member/memberUpdate.do",method=RequestMethod.POST)
	public String process(@ModelAttribute("member")MemberVO member,Model model
			 ) throws ServletException, IOException {

		model.addAttribute("member", member);
		
		
		String message=null;
		String gopage="member/memberView";
		Map<String,List<CharSequence>> errors=new HashMap<>();
		model.addAttribute("errors", errors);
		GeneralValidator validator= new GeneralValidator();
		boolean valid= validator.validate(member, errors, UpdateGroup.class);
		System.err.println(errors.size());
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
