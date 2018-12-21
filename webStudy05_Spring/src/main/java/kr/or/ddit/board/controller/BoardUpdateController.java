package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
@Controller
@RequestMapping(value="/board/boardUpdate.do")
public class BoardUpdateController {
	@Inject
	IBoardService service;
	@RequestMapping()
	public String getProcess(@RequestParam(name="what",required=true) long bo_no,Model model) {
		
		BoardVO board= service.retriveBoard(bo_no);
		model.addAttribute("board", board);
		return "board/boardForm";
		
	}
	@RequestMapping(method=RequestMethod.POST)
	public String postPrecess(BoardVO board,Model model,@RequestPart(name="bo_file",required=false) MultipartFile[] bo_file) {
		Map<String, List<CharSequence>> errors=new HashMap<>();
//		GeneralValidator validator=new GeneralValidator();
//		boolean valid=validator.validate(board, errors, UpdateGroup.class);
		boolean valid=true;
		String view="board/boardForm";
		if(valid) {
			ServiceResult res= service.modifyBoard(board);
			switch (res) {
			case OK:
				//PRG패턴 포스트- 리다이렉트 - 겟요청 
				view="redirect:/board/boardView.do?what="+board.getBo_no();
				break;
			case FAILED:
				model.addAttribute("message", "서버오류랑");
				break;
			case INVALIDPASSWORD:
				model.addAttribute("message", "비번틀렸당");
				
				break;
			}
			
		}
		model.addAttribute("errors",errors );
		model.addAttribute("board", board);
		return view;
	}

}
