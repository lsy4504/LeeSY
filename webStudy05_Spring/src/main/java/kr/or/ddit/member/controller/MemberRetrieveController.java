package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.service.IMemberSerivce;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
@Controller
@RequestMapping("/member")
public class MemberRetrieveController{
	@Inject
	IMemberSerivce serivce;
	
	@RequestMapping("memberList.do")
	public String MemberList(@RequestParam(required=false) String searchWord,@RequestParam(required=false)String searchType
			,@RequestParam(name="page",required=false,defaultValue="1")int currentPage,Model model
			) {
		
		/*1. 요청과의 매핑설정
		2. 요청 분석(주소, 파리미터,메소드 ,헤더들)/...
		3.bll와의 의존관게형성
		4.로직선택
		5.컨텐츠(모델) 확보
		6.v.l선택
		7. scope를 통해 모델 공유
		8. 이동 방식 결정 . 이동~
		*/
		PagingInfoVO<MemberVO> pagingVO= new PagingInfoVO<MemberVO>(5,2);
		pagingVO.setSearchWord(searchWord);
		pagingVO.setSearchType(searchType);
		pagingVO.setCurrentPage(currentPage);
		long totalRecord=serivce.retribeMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberList= serivce.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		String view="member/memberList";
		model.addAttribute("pagingVO", pagingVO);
		return view;
	}
	@RequestMapping("memberView.do")
	public ModelAndView memberView(@RequestParam(name="who",required=true) String mem_id) {
		String view = "member/memberView";
		 MemberVO member = serivce.retrieveMember(mem_id);
		ModelAndView mav= new ModelAndView();
		mav.addObject("member", member );
		mav.setViewName(view);

		return mav;

	}
}
