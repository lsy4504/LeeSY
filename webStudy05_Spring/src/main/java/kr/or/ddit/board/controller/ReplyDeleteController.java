package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.vo.ReplyVO;
import kr.or.ddit.web.calculate.MimeType;
@Controller
public class ReplyDeleteController  {
	@Inject
	IReplyService service;
	@RequestMapping(value="/reply/replyDelete.do" , method=RequestMethod.POST)
	public String process(ReplyVO reply,Model model) {
		Map<String, String> errors=new HashMap<String, String>();
		boolean valid=validate(errors,reply);
		String view= "jsonView";
		if(valid) {
			ServiceResult res= service.removeReply(reply);
			switch (res) {
			case OK:
				return "redirect:/reply/replyList.do?bo_no="+reply.getBo_no()+"&page=1";
			case INVALIDPASSWORD:
				errors.put("code", "true");
				errors.put("message", "비번틀림..");
			}
		}else {
			errors.put("code", "true");
			errors.put("message", "검증실패");
			
		}
		model.addAttribute("errors", errors);
		return view;
	}

	private boolean validate(Map<String, String> errors, ReplyVO reply) {
		boolean valid=true;
		if(StringUtils.isBlank(reply.getRep_pass())){
		valid=false;
		errors.put("rep_pass", "> 미입력 .... <");
		}
			
		return valid;
	}
	
}
