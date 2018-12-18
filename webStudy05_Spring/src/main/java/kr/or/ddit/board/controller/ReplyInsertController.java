package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
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
public class ReplyInsertController  {
	@URIMapping(value="/reply/replyInsert.do" , method=HttpMethod.GET)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ReplyVO reply = new ReplyVO();
		resp.setContentType(MimeType.JSON.getMimeType());

		try {
			BeanUtils.populate(reply, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		reply.setRep_ip(req.getRemoteAddr());
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(errors, reply);
		if(valid) {
			IReplyService service = new ReplyServiceImpl();
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
		ObjectMapper mapper =new ObjectMapper();
		mapper.writeValue(resp.getWriter(), errors);

		return null;
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
