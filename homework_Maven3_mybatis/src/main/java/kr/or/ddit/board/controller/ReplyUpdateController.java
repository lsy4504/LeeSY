package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.vo.ReplyVO;
@CommandHandler
public class ReplyUpdateController {
	@URIMapping(value="/reply/replyUpdate.do", method=HttpMethod.GET)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ReplyVO reply=new ReplyVO();
		Map<String, String> errors=new HashMap<>();
		try {
			BeanUtils.populate(reply, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		boolean valid=validate(errors, reply);
		if(valid) {
			IReplyService service=new ReplyServiceImpl();
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
		ObjectMapper mapper= new ObjectMapper();
		mapper.writeValue(resp.getWriter(), errors);
		
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
