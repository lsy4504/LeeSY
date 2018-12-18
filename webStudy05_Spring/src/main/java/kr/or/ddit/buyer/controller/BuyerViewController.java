package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.vo.BuyerVO;
@CommandHandler
public class BuyerViewController  {

	@URIMapping("/buyer/buyerView.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String buyer_id= req.getParameter("who");
		if(StringUtils.isBlank(buyer_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		IOtherDAO otherDAO= OtherDAOImpl.getInstance();
		List<Map<String, Object>> lprodList= otherDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		IBuyerService service = BuyerServiceImpl.getInstance();
		BuyerVO buyer= service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		String view="buyer/buyerView";
		return view;
	}
	
}
