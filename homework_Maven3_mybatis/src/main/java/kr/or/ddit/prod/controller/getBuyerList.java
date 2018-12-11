package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.vo.BuyerVO;
@CommandHandler
public class getBuyerList  {

	@URIMapping("/prod/getBuyerList.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String buyer_lgu = req.getParameter("prod_lgu");
		
		IOtherDAO other = OtherDAOImpl.getInstance();

		List<BuyerVO> buyerList = other.selectBuyerList(buyer_lgu);
		ObjectMapper mapper = new ObjectMapper();
		
		resp.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = resp.getWriter();) {

			mapper.writeValue(out, buyerList);
		} catch (Exception e) {
		}
		return null;
	}

}
