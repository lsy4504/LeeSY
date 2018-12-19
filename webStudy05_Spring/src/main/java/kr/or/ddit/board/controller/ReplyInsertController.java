package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.vo.ReplyVO;
@Controller
public class ReplyInsertController  {
	@Inject
	IReplyService service;
	@RequestMapping(value="/reply/replyInsert.do" , method=RequestMethod.POST)
	public String process(@ModelAttribute("reply")ReplyVO reply,Model model) {
		//ip폼에 추가해줄것..
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(errors, reply);
		if(valid) {
			ServiceResult res= service.createReply(reply);
			switch (res) {
			case OK:
				return "redirect:/reply/replyList.do?bo_no="+reply.getBo_no()+"&page=1";

			default:
				errors.put("code", "true");
				errors.put("message", "서버오류");
			}
		}else {
			errors.put("code", "true");
			errors.put("message", "검증실패");
			
		}
		model.addAttribute("errors",errors);


		return "jsonView";
	}

	private boolean validate(Map<String, String> errors, ReplyVO reply) {
		boolean valid = true;

		
		if (StringUtils.isBlank(reply.getRep_writer())) {
			valid = false;
			errors.put("rep_writer", "> 미입력 .... <");
		}
		
		if (StringUtils.isBlank(reply.getRep_pass())) {
			valid = false;
			errors.put("rep_pass", "> 미입력 .... <");
		}
		if(reply.getRep_content()!=null&& reply.getRep_content().length()>100) {
			valid = false;
			errors.put("rep_content", "길이는 100글자 미만");
		}
		
		return valid;
	}
}
