package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Insert;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.ReplyBookVO;
import lombok.experimental.UtilityClass;

@CommandHandler
public class ReplyinsertController {
	@URIMapping(value="/replybook/replyInsert.do",method=HttpMethod.POST)
	public String process(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		ReplyBookVO replyBookVO=new ReplyBookVO();
		IReplyService service=new ReplyServiceImpl();
		try {
			BeanUtils.populate(replyBookVO, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		GeneralValidator validator=new GeneralValidator();
		Map<String, List<CharSequence>> errors=new HashMap<>();
		boolean validate=validator.validate(replyBookVO, errors, Insert.class);
		ServiceResult res= service.insertReplyBook(replyBookVO);
		Map<String, String> messege=new HashMap<>();
		switch (res) {
		case OK:
			messege.put("flag", "true");
			break;
		case FAILED:
			messege.put("flag", "false");
			messege.put("content", "서버 오류....");
			break;
		}
		ObjectMapper mapper=new ObjectMapper();
		try(
				PrintWriter out =resp.getWriter();
			){
			mapper.writeValue(out, messege);
			
		}
		return null;
		
	}
}
