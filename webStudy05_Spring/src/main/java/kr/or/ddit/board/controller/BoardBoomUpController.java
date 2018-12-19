package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@Controller
public class BoardBoomUpController  {
	@Inject
	IBoardService service;
	@ResponseBody
	@RequestMapping(value="/board/boomUp.do",produces="application/json;charset=UTF-8")
	public Map<String, String> boomUp(@RequestParam(name="bo_no",required=true) long bo_no,
			RedirectAttributes redirectAttributes) throws IOException {
		Map<String, String> boomMap=new HashMap<>();
		ServiceResult res=service.boomUp(bo_no);
		
		switch (res) {
		case OK:
			redirectAttributes.addFlashAttribute("check", "true");
			
			boomMap.put("flag", "true");
			break;

		case FAILED:
			boomMap.put("flag", "false");
			break;
		}
		return boomMap;
		
	}
}
