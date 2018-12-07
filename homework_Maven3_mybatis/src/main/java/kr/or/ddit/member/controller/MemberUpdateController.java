package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
public class MemberUpdateController implements ICommandHandler{
	
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		IMemberSerivce serivce=MemberServiceImpl.getInstance();
		MemberVO member= new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CommonException(e);
		}
		
		String message=null;
		String gopage="member/memberView";
		Map<String,List<CharSequence>> errors=new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator= new GeneralValidator();
		boolean valid= validator.validate(member, errors, UpdateGroup.class);
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
			req.setAttribute("message", message);
		}else{
			
		}
		return gopage;
		
	}

}
