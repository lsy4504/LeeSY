package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyBookVO;

@CommandHandler
public class ReplyListController {
	@URIMapping(value="/replyBook/replyBookList.do",method=HttpMethod.POST)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		IReplyService service =new ReplyServiceImpl();
		int currentPage=1;
		String pageStr=req.getParameter("page");
		String bo_noStr=req.getParameter("bo_no");
		System.out.println(bo_noStr);
		long bo_no=0;
		if(StringUtils.isNumeric(pageStr)) {
			currentPage=Integer.parseInt(pageStr);
		}
		if(StringUtils.isNumeric(bo_noStr)) {
			bo_no=Long.parseLong(bo_noStr);
		}
		PagingVO<ReplyBookVO> paging=new PagingVO<>();
		paging.setCurrentPage(currentPage);
		ReplyBookVO reply=new ReplyBookVO();
		reply.setBo_no(bo_no);
		paging.setSearchVO(reply);
		paging.getSearchVO().setBo_no(bo_no);
		long totalRecord=service.retriveReplyCount(paging);
		paging.setTotalRecord(totalRecord);
		List<ReplyBookVO> replyList=service.listReplyBook(paging);
		paging.setDataList(replyList);
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper= new ObjectMapper();
		try (
				PrintWriter out= resp.getWriter();
				){
			mapper.writeValue(out, paging);
		} 
		
		
		return null;
		
	}
}
