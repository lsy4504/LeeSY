package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Object;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.controller.MyPageController;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.web.calculate.MimeType;

public class getBuyerList implements ICommandHandler {

	@Override
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
