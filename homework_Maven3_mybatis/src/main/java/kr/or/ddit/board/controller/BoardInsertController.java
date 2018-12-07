package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PdsVO;

public class BoardInsertController implements ICommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method=req.getMethod();
		if("get".equalsIgnoreCase(method)) {
			return "board/boardForm";
		}else if ("post".equalsIgnoreCase(method)) {
			return doPost(req,resp);
		}
		
		return null;
	}

	private String doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("우야야야야야");
		BoardVO board=new BoardVO();
		req.setAttribute("board", board);
		Map<String, List<CharSequence>> errors= new HashMap<String, List<CharSequence>>();
		req.setAttribute("errors", errors);
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		GeneralValidator validator = new GeneralValidator();
		
		boolean valid=validator.validate(board, errors, InsertGroup.class);
		if (valid) {
			if(req instanceof FileUploadReaquestWrapper) {
				List<FileItem> fileItems= ((FileUploadReaquestWrapper) req).getFileItems("bo_file");
				board.setItemList(fileItems);
				
			}
			IBoardService service=new BoardServiceImpl();
			ServiceResult res=service.createBoard(board);
			switch (res) {
			case OK:
				return "redirect:/board/boardList.do";

			case FAILED:
				return	"/board/boardInsert";
			}
		}
		return null;
	}
}
