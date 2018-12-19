package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;
@Controller
@RequestMapping("/prod/prodList.do")
public class ProdListController  {
	@Inject
	IProdService service;
	@Inject
	IOtherDAO other;
	@ResponseBody
	@RequestMapping(produces="application/json;charset=UTF-8")
	public PagingInfoVO<ProdVO> ListAsync(ProdVO searchVO,@RequestParam(name="page",required=false,defaultValue="1") int currentPage){
		PagingInfoVO<ProdVO> pagingVO=new PagingInfoVO<ProdVO>(7,4);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.retrieveProdCount(pagingVO));
		List<ProdVO> prodList=service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		return pagingVO;
	}
	@RequestMapping()
	public String process(ProdVO searchVO,@RequestParam(name="page",required=false,defaultValue="1") int currentPage,Model model){
		PagingInfoVO<ProdVO> pagingVO=ListAsync(searchVO, currentPage);
		List<Map<String, Object>> lprodList= other.selectLprodList();
		List<BuyerVO> buyerList= other.selectBuyerList(null);
		model.addAttribute("pagingVO", pagingVO);
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("buyerList", buyerList);
		
		
		String view = "prod/prodList";
		return view;
	}

}
