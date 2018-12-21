package kr.or.ddit.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
@Controller
@RequestMapping(value="/member/memberInsert.do")
public class MemberInsertController {
	@Inject
	IMemberSerivce serivce;
	
	@RequestMapping(method=RequestMethod.GET)
	public String doGet(){
		String view="member/memberForm";
		return view;
	}
/*	JSR-303방식의 객체 검증 모델
	1. javax.validation 의존성추가(hibernate-validator)
	2. 검증 대상이 되는 command object 의 타입(class) 에 검증 룰을 의미하는 어노테이션 적용.
		ex) memberVO: NotBlank, NotNull, Length...
	3. 핸들러 메소드의 아규먼트로 선언된 command object에 
		@Validated,@Valid 등을 사용하여, 객체 검증이 수행되도록 설정.
	4.**검증 결과를 캡슐화한 Errors(BindingResult)를 반드시, 검증 대상 커맨드 오브젝트 다음에 선언
	5.핸들러 메소드 내부에서 검증 결과를 확인
	6.뷰 레이어에서 해당 검ㅈ으 결과 메시지를 랜더링
		spring-form 태그 라이브러리 이용. ex)form:errors path="모델명.프로퍼티명"*/
	@RequestMapping(method=RequestMethod.POST)
	public String doPost(@Validated(value=InsertGroup.class) @ModelAttribute("member")MemberVO member,
			Errors errors,
			Model model,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String message=null;
		String gopage="member/memberForm";
//		Map<String,List<CharSequence>> errors=new HashMap<>();
//		model.addAttribute("errors", errors);
//		GeneralValidator validator=new GeneralValidator();
//		boolean valid= validator.validate(member, errors, InsertGroup.class);
		boolean valid=!errors.hasErrors();
		System.out.println(valid);
		if(valid){
			ServiceResult result=serivce.registMember(member);
			switch(result){
			case PKDUPLICATED:
				message="아이디 중복, 바꿔용";
				break;
			case FAILED:
				message="서버 오류입니다..";
				break;
			case OK:
				gopage="redirect:/member/memberList.do";
				break;
			}
			req.setAttribute("message", message);
		}
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
		if(StringUtils.isBlank(member.getMem_name())){
		valid=false;
		errors.put("mem_name", ">회원명 미입력 .... <");
		}
		if(StringUtils.isNotBlank(member.getMem_bir())){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//formatting : 특정타입의 데이터를 일정 형식의 문자열로 변환.
			//parsing: 일정한 형식의 문자열을 특정 타입의 데이터로 변환.
			try{
			formatter.parse(member.getMem_bir());
			}catch(ParseException e){
				valid=false;
				errors.put("mem_bir", ">날짜형식 확인<");
			}
		}
// 		if(StringUtils.isBlank(member.getMem_pass())){
// 			message="비밀번호 미입력.";
// 		}else if(StringUtils.isBlank(member.getMem_name())){
// 			message="이름 미입력..";
// 		}else{
// 			System.out.print("aa");
// 			ServiceResult serviceResult= serivce.registMember(member);
// 			if(ServiceResult.OK==serviceResult){
// 				redirect=true;
// 				gopage=request.getContextPath()+"/member/memberList.jsp";
				
// 			}else if(ServiceResult.INVALIDPASSWORD==serviceResult){
// 				message="아이디가 중복됩니다?";
// 			}
// 		}
		
		return valid;
	}
}
