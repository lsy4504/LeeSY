package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.PdsVO;
@Controller
public class DownloadController {
	@Inject
	IBoardService service;
	@RequestMapping("/board/download.do")
	public String process(@RequestParam(name="what",required=true)long pds_no,HttpServletResponse resp,@RequestHeader("user-agent")String agent,
			Model model) throws IOException{
		System.out.println("여기는옵니까??");

		PdsVO pds=	service.downLoadPds(pds_no);
		model.addAttribute("pds", pds);
		
		return "downloadView";
	}

}
