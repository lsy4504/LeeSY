package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
public class BuyerInsertController{
	@URIMapping(value="/buyer/buyerInsert.do" , method=HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IOtherDAO otherDAO= OtherDAOImpl.getInstance();
		List<Map<String, Object>> lprodList= otherDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		String view="buyer/buyerForm";
		return view;
	}
	@URIMapping(value="/buyer/buyerInsert.do" , method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BuyerVO buyer = new BuyerVO();
		Map<String, String> errors=new HashMap<>();
		//넘어온값 가져오기
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (Exception e) {
			throw new CommonException();
		}
		String message="";
		req.setAttribute("errors",errors );
		req.setAttribute("buyer", buyer);
		String view="";
		boolean res=validate(buyer, errors);
		if(res) {
			IBuyerService service=BuyerServiceImpl.getInstance();
			ServiceResult result= service.registBuyer(buyer);
			switch (result) {
			case OK:
				view="redirect:/buyer/buyerList.do";
				break;
			case FAILED:
				view="/buyer/buyerForm";
				message="서버 오류..";
				break;
		

			default:
				message="이미 존재함..";
				view="/buyer/buyerForm";
				break;
			}
		}
		return view;
		
	
	}
	private boolean validate(BuyerVO buyer, Map<String,String> errors){
		boolean valid= true;
		if(StringUtils.isBlank(buyer.getBuyer_id())){
		valid=false;
		errors.put("buyer_id", "> 미입력 .... <");
		}
		if(StringUtils.isBlank(buyer.getBuyer_name())){
		valid=false;
		errors.put("buyer_name", "> 미입력 .... <");
		}
		if(StringUtils.isBlank(buyer.getBuyer_lgu())){
		valid=false;
		errors.put("buyer_lgu", "> 미입력 .... <");
		}
		if(StringUtils.isBlank(buyer.getBuyer_comtel())){
		valid=false;
		errors.put("buyer_comtel", "> 미입력 .... <");
		}
		if(StringUtils.isBlank(buyer.getBuyer_fax())){
		valid=false;
		errors.put("buyer_fax", "> 미입력 .... <");
		}
		if(StringUtils.isBlank(buyer.getBuyer_mail())){
		valid=false;
		errors.put("buyer_mail", "> 미입력 .... <");
		}
		
		return valid;
	}

}
