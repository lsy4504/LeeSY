package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
@CommandHandler
public class MemberListController{
	
	@URIMapping("/member/memberList.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*1. 요청과의 매핑설정
		2. 요청 분석(주소, 파리미터,메소드 ,헤더들)/...
		3.bll와의 의존관게형성
		4.로직선택
		5.컨텐츠(모델) 확보
		6.v.l선택
		7. scope를 통해 모델 공유
		8. 이동 방식 결정 . 이동~
		*/
		int currentPage=1;
		String page =req.getParameter("page");
		String searchWord=req.getParameter("searchWord");
		String searchType=req.getParameter("searchType");
		if(StringUtils.isNumeric(page)) {
			currentPage=Integer.parseInt(page);
		}
		PagingInfoVO<MemberVO> pagingVO= new PagingInfoVO<MemberVO>(5,2);
		pagingVO.setSearchWord(searchWord);
		pagingVO.setSearchType(searchType);
		pagingVO.setCurrentPage(currentPage);
		IMemberSerivce serivce=MemberServiceImpl.getInstance();
		long totalRecord=serivce.retribeMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberList= serivce.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		String view="member/memberList";
		System.err.println(view);
		req.setAttribute("pagingVO", pagingVO);
		return view;
	}
}
