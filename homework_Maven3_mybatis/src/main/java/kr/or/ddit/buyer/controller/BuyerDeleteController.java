package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.vo.BuyerVO;
@CommandHandler
public class BuyerDeleteController  {
	@URIMapping(value="/buyer/buyerDelete.do" , method=HttpMethod.POST)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String buyer_id=req.getParameter("buyer_id");
		if (StringUtils.isBlank(buyer_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		BuyerVO buyer= new BuyerVO();
		buyer.setBuyer_id(buyer_id);
		IBuyerService service= BuyerServiceImpl.getInstance();
		String view="";
		String message="";
		ServiceResult res= service.removeBuyer(buyer);
		switch (res) {
		case OK:
			view="redirect:/ ";
			break;
		case PKNOTFOUND:
			message="아이디 없음..?";
			view="/buyer/buyerView";
			break;
		default:
			message="서버 에러..";
			view="/buyer/buyerView";
			break;
		}
		req.setAttribute("message", message);
		req.setAttribute("buyer_id", buyer_id);
		return view;
	}
}
