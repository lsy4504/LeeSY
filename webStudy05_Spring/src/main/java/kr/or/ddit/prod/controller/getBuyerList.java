package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.vo.BuyerVO;
//@Controller
//@ResponseBody
@RestController
public class getBuyerList  {
	IOtherDAO other = OtherDAOImpl.getInstance();
	@RequestMapping(value="/prod/getBuyerList.do",produces="application/json;charset=UTF-8")
	public List<BuyerVO> process(@RequestParam(required=false)String prod_lgu) {
		

		List<BuyerVO> buyerList = other.selectBuyerList(prod_lgu);
		
		
		return buyerList;
	}

}
