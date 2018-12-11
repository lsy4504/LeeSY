package kr.or.ddit.board.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BoardVO;

@CommandHandler
public class BoardInsertController  {
	IBoardService service=new BoardServiceImpl();
	@URIMapping(value="/board/boardInsert.do", method=HttpMethod.GET)
	public String getProcess(HttpServletRequest req, HttpServletResponse resp) {
		return "board/boardForm";
	}
	@URIMapping(value="/board/boardInsert.do", method=HttpMethod.POST)
	public String postProcess(HttpServletRequest req, HttpServletResponse resp) {
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
