package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerListController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int currentPage=1;
		String page=req.getParameter("page");
		String view="buyer/buyerList";
		if(StringUtils.isNumeric(page)) {
			currentPage=Integer.parseInt(page);
		}
		
		PagingInfoVO<BuyerVO> pagingInfoVO = new PagingInfoVO<>(5,3);
		IBuyerService service = BuyerServiceImpl.getInstance();
		pagingInfoVO.setCurrentPage(currentPage);
		long totalRecord=service.retribeBuyerCount(pagingInfoVO);
		pagingInfoVO.setTotalRecord(totalRecord);
		pagingInfoVO.setDataList(service.retrieveBuyerList(pagingInfoVO));
		req.setAttribute("pagingInfoVO", pagingInfoVO);
		return view;
		
		
		
	}

}
