package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.vo.ReplyVO;
@Controller
public class ReplyUpdateController {
	@Inject
	IReplyService service;
	
	@RequestMapping(value="/reply/replyUpdate.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String process(ReplyVO reply){
		Map<String, String> errors=new HashMap<>();
		boolean valid=validate(errors, reply);
		if(valid) {
			ServiceResult result= service.modifyReply(reply);
			switch (result) {
			case OK:
				return "redirect:/reply/replyList.do?bo_no="+reply.getBo_no();
			case FAILED:
				errors.put("code", "true");
				errors.put("message", "서버오류");
			default:
				errors.put("code", "true");
				errors.put("message", "비밀번호 실패 ㅠㅠ");
			}
		}
		return null;
		
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
