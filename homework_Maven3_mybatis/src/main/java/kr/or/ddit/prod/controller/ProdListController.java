package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.web.calculate.MimeType;
@CommandHandler
public class ProdListController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ProdVO searchVO= new ProdVO();
		searchVO.setProd_lgu(req.getParameter("prod_lug"));
		searchVO.setProd_buyer(req.getParameter("prod_buyer"));
		searchVO.setProd_name(req.getParameter("prod_name"));
		int currentPage=1;
		PagingInfoVO<ProdVO> pagingVO=new PagingInfoVO<ProdVO>(7,4);
		String page=req.getParameter("page");
		if(StringUtils.isNumeric(page)) {
			currentPage=Integer.parseInt(page) ;
		}
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		IProdService service= ProdServiceImpl.getInstance();
		IOtherDAO dao=OtherDAOImpl.getInstance();
		List<Map<String, Object>> lprodList= dao.selectLprodList();
		List<BuyerVO> buyerList= dao.selectBuyerList(null);
		req.setAttribute("pagingVO", pagingVO);
		String accept=req.getHeader("Accept");
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
		pagingVO.setTotalRecord(service.retrieveProdCount(pagingVO));
		List<ProdVO> prodList=service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		if(accept.contains("json")) {
			System.out.println("여기옴?");
			ObjectMapper mapper=new ObjectMapper();
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out=resp.getWriter();
			mapper.writeValue(out, pagingVO);
			return null;
		}
		
		String view = "prod/prodList";
		return view;
	}

}
