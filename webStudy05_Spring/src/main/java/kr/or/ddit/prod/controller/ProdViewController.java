package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
@Controller
public class ProdViewController{
	@Inject
	IProdService service;
	@RequestMapping("/prod/prodView.do")
	public String process(@RequestParam(name="what",required=true) String prod_id,Model model) {
		ProdVO prodVO= service.retriveProd(prod_id);
		model.addAttribute("prod", prodVO);
		 
		return "prod/prodView";
	}
}
