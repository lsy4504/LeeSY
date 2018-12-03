package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author lsy
 * @since 2018. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 11. 21.      작성자명       회원관리를 위한 Business Logic layer
 * Copyright (c) 2018 by DDIT All right reserved
 *      </pre>
 */
public interface IMemberSerivce {
	/**
	 * 회원 신규 등록
	 * 
	 * @param member
	 * @return PKDUPICATED , OK , FAILED
	 */
	public ServiceResult registMember(MemberVO member);

	/**
	 * 회원 목록 조회
	 * 
	 * @return 존재하지않으면 리스트의 사이즈 0
	 */
	public long retribeMemberCount(PagingInfoVO pagingVO);
	 
	public List<MemberVO> retrieveMemberList(PagingInfoVO pagingVO);

	/**
	 * 회원 정보 상세 조회
	 * 
	 * @param mem_id 조회할 회원아이디
	 * @return 존재하지않으면 CommonException 발생
	 */
	public MemberVO retrieveMember(String mem_id);

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return  CommonExceoption, INVALIDPASSWORD, OK ,FAILE
	 */
	public ServiceResult modifyMember(MemberVO member);

	/**
	 * @param member
	 * @return  CommonExceoption, INVALIDPASSWORD, OK ,FAILE
	 */
	public ServiceResult removeMember(MemberVO member);
	
	
}
