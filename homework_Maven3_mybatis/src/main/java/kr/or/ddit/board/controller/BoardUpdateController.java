package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

public class BoardUpdateController implements ICommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method=req.getMethod();
		if ("get".equalsIgnoreCase(method)) {
			String bo_noStr=req.getParameter("what");
			long bo_no=0;
			if(StringUtils.isNotBlank(bo_noStr)) {
				bo_no=Long.parseLong(bo_noStr);
			}
			IBoardService service=new BoardServiceImpl();
			
			BoardVO board= service.retriveBoard(bo_no);
			req.setAttribute("board", board);
			return "board/boardForm";
		}else if("post".equalsIgnoreCase(method)) {
			return doPost(req,resp);
		}
		return null;
	}

	private String doPost(HttpServletRequest req, HttpServletResponse resp) {
		BoardVO board= new BoardVO();
		
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		Map<String, List<CharSequence>> errors=new HashMap<>();
		GeneralValidator validator=new GeneralValidator();
		boolean valid=validator.validate(board, errors, UpdateGroup.class);
		String view="board/boardForm";
		if(valid) {
			if(req instanceof FileUploadReaquestWrapper) {
				List<FileItem> fileItems= ((FileUploadReaquestWrapper) req).getFileItems("bo_file");
				board.setItemList(fileItems);
			}
			IBoardService service=new BoardServiceImpl();
			ServiceResult res= service.modifyBoard(board);
			switch (res) {
			case OK:
				//PRG패턴 포스트- 리다이렉트 - 겟요청 
				view="redirect:/board/boardView.do?what="+board.getBo_no();
				break;
			case FAILED:
				req.setAttribute("message", "서버오류랑");
				break;
			case INVALIDPASSWORD:
				req.setAttribute("message", "비번틀렸당");
				
				break;
			}
			
		}
		req.setAttribute("errors",errors );
		req.setAttribute("board", board);
		return view;
	}
}
