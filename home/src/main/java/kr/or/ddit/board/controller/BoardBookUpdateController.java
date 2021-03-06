package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Update;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.MimeType;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardBookServiceImpl;
import kr.or.ddit.board.service.IBoardBookService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BoardBookVO;

@CommandHandler
public class BoardBookUpdateController {
	@URIMapping(value="/boardBook/boardBookUpdate.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		IBoardBookService service =new BoardBookServiceImpl();
		BoardBookVO board=new BoardBookVO();
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		Map<String, List<CharSequence>> errors=new HashMap<String, List<CharSequence>>();
		GeneralValidator validator=new GeneralValidator();
		boolean valid=validator.validate(board, errors, Update.class);
		if (valid) {
			
		}
		ServiceResult res=  service.updateBoardBook(board);
		Map<String, String> messege=new HashMap<>();
		switch (res) {
		case OK:
			messege.put("flag", "true");
			break;
		case INVALIDPASSWORD:
			messege.put("flag", "false");
			messege.put("content", "비밀번호...");
			break;
		case FAILED:
			messege.put("flag", "false");
			messege.put("content", "서버오류....");
			break;
		}
		resp.setContentType(MimeType.JSON.getMimeType());
		ObjectMapper mapper = new ObjectMapper();
		try(
				PrintWriter out=resp.getWriter();
		){
			mapper.writeValue(out, messege);
		}
		
		return null;
		
	}
}
