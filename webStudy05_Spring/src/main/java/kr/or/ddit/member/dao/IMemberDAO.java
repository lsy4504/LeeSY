package kr.or.ddit.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author lsy
 * @since 2018. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 11. 21.      작성자명       최초작성 회원관리를 위한 Presistence Lauer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IMemberDAO {
	/**
	 * 	회원정보 상세 조회
	 * @param mem_id 조회할 회원의 아이디
	 * @return 존재하지 않는다면 null
	 */
	public MemberVO selectMember(String mem_id);
	
	/**
	 * 전체회원 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않는다면 .size() ==0;
	 */
	public List<MemberVO> selectMemberList( PagingInfoVO pagingVO);
		
	/**
	 * 	회원 신규 등록
	 * @param member
	 * @return 성공 1이상  0실패
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원 수정
	 * @param member
	 * @return 성공 1이상  0실패
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원삭제
	 * @param member 삭제할 회원의 아이디 기본
	 * @return 성공 1이상  0실패
	 */
	public int deleteMember(String mem_id);
	
	public long selectTotalRecord(PagingInfoVO pagingVO);
}
