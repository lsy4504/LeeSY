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
import org.apache.ibatis.annotations.Insert;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardBookServiceImpl;
import kr.or.ddit.board.service.IBoardBookService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.BoardBookVO;

@CommandHandler
public class BoardBookInsertController {
	IBoardBookService service=new BoardBookServiceImpl();
	@URIMapping(value="/boardBook/boardBookInsert.do",method=HttpMethod.POST)
	public String insert(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("아잉");
		BoardBookVO boardBookVO=new BoardBookVO();
		try {
			BeanUtils.populate(boardBookVO, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		Map<String, List<CharSequence>> errors= new HashMap<>();
		Map<String, String> messege =new HashMap<>();
		GeneralValidator validator = new GeneralValidator();
		validator.validate(boardBookVO, errors, Insert.class);
		ServiceResult res= service.insertBoardBook(boardBookVO);
		switch (res) {
		case OK:
			messege.put("flag", "true");
			break;
		case FAILED:
			messege.put("flag", "false");
			break;

		}
		ObjectMapper mapper=new ObjectMapper();
		try(
				PrintWriter out = resp.getWriter();
				){
			mapper.writeValue(out, messege);
		}
		return null;
		
	}
}
