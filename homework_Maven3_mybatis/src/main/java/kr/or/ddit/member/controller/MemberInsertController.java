package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.MemberVO;
@CommandHandler
public class MemberInsertController {
	
	
	@URIMapping(value="/member/memberInsert.do",method=HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view="member/memberForm";
		return view;
	}
	@URIMapping(value="/member/memberInsert.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IMemberSerivce serivce=MemberServiceImpl.getInstance();
		MemberVO member= new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CommonException(e);
		}
		
		String message=null;
		String gopage="member/memberForm";
		Map<String,List<CharSequence>> errors=new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator=new GeneralValidator();
		boolean valid= validator.validate(member, errors, InsertGroup.class);
		System.err.println(errors.size());
		if(valid){
			
			if(req instanceof FileUploadReaquestWrapper ) {
				//경로, 이름,
				System.out.println("아니시밣 제발1");
			FileItem fileItem=  ((FileUploadReaquestWrapper) req).getFileItem("mem_image");
			System.out.println(fileItem.getSize());
			if(fileItem!=null) {
				member.setMem_img(fileItem.get()); 
				System.out.println("아니시밣 제발");
			}
				
				
				
				
			}
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
		}else{
			
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
