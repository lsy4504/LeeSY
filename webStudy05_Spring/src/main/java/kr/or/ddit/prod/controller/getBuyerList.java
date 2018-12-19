package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.vo.BuyerVO;
//@Controller
//@ResponseBody
@RestController
public class getBuyerList  {
	@Inject
	IOtherDAO other;
	@RequestMapping(value="/prod/getBuyerList.do",produces="application/json;charset=UTF-8")
	public List<BuyerVO> process(@RequestParam(required=false)String prod_lgu) {
		

		List<BuyerVO> buyerList = other.selectBuyerList(prod_lgu);
		
		
		return buyerList;
	}

}
