package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.vo.BuyerVO;
@CommandHandler
public class BuyerUpdateController{
	IOtherDAO other=OtherDAOImpl.getInstance();
	

	@URIMapping(value="/buyer/buyerUpdate.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) {
		IBuyerService service = BuyerServiceImpl.getInstance();
		BuyerVO buyer = new BuyerVO();
		Map<String,String> errors=new HashMap<>();
		req.setAttribute("errors", errors);
		req.setAttribute("buyer", buyer);
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (Exception e) {
			throw new CommonException(e);
		}
		boolean res=valid(buyer,errors);
		String view="/buyer/buyerView";
		String message=null;
		if(res) {
			ServiceResult result= service.modifyBuyer(buyer);
			System.out.println(result);
			switch (result) {
			
			case OK:
				view="redirect:/buyer/buyerView.do?who="+buyer.getBuyer_id();
				break;
			case FAILED:
				message ="서버 자체문제입니다";
				view="/buyer/buyerForm";
				break;
			}
			req.setAttribute("message", message);
		}else {
			
		}
		return view;
	}

	@URIMapping(value="/buyer/buyerUpdate.do",method=HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Map<String, Object>> lprodList=other.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		System.out.println("으잉?");
		String buyer_id = req.getParameter("who");
		if(StringUtils.isBlank(buyer_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		IBuyerService service=BuyerServiceImpl.getInstance();
		BuyerVO buyer =service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);

		
		return "buyer/buyerForm";
	}

	public boolean valid(BuyerVO buyer,Map<String, String> errors) {
		boolean valid= true;
	
		if (StringUtils.isBlank(buyer.getBuyer_name())) {
			valid = false;
			errors.put("buyer_name", "판매처명 누락");
		}

		if (StringUtils.isBlank(buyer.getBuyer_comtel())) {
			valid = false;
			errors.put("buyer_comtel", "전번 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_fax())) {
			valid = false;
			errors.put("buyer_fax", "팩스 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_mail())) {
			valid = false;
			errors.put("buyer_mail", "이멜 누락");
		}
	
		return valid;
	}

}
