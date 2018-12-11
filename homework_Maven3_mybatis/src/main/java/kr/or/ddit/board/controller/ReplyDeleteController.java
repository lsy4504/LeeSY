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
import kr.or.ddit.web.calculate.MimeType;
@CommandHandler
public class ReplyDeleteController  {
	@URIMapping(value="/reply/replyDelete.do" , method=HttpMethod.GET)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ReplyVO reply= new ReplyVO();
		System.out.println("제발 여기까지만 와라 ..");
		resp.setContentType(MimeType.JSON.getMimeType());
		Map<String, String> errors=new HashMap<String, String>();
		try {
			BeanUtils.populate(reply, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		boolean valid=validate(errors,reply);
		if(valid) {
			IReplyService service= new ReplyServiceImpl();
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
		if(valid) {
		ObjectMapper mapper =new ObjectMapper();
		mapper.writeValue(resp.getWriter(), errors);
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
