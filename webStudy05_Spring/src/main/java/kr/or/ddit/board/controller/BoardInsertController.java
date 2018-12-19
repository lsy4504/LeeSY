package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping(value="/board/boardInsert.do")
public class BoardInsertController  {
	@Inject
	IBoardService service;
	@RequestMapping()
	public String getProcess() {
		return "board/boardForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String postProcess(BoardVO board,Model model
			
			) {
		model.addAttribute("board", board);
		Map<String, List<CharSequence>> errors= new HashMap<String, List<CharSequence>>();
		model.addAttribute("errors", errors);
		
		GeneralValidator validator = new GeneralValidator();
		
		boolean valid=validator.validate(board, errors, InsertGroup.class);
		if (valid) {
				
			
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
