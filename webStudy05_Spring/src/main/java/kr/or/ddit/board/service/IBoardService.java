package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PdsVO;

/**
 * @author lsy
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       게시글 관리를 위환 Business logic layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IBoardService {
	/**
	 * 새글작성
	 * @param board
	 * @return ok failed
	 */
	public ServiceResult createBoard(BoardVO board);
	/**
	 * 검색 조건에 맞는 게시글 수 
	 * @param pagingVO 검색 조건을 가진 VO
	 * @return 없다면 0 반환
	 */
	public long retriveBoardCount(PagingInfoVO<BoardVO> pagingVO);
	/**
	 * 검색 조건에맞는 게시글 목록
	 * @param pagingVO 검색조건ㅇ과 페이징 속성을 가진 VO
	 * @return size==0(없다면)
	 */
	public List<BoardVO> retriveBoardList(PagingInfoVO<BoardVO> pagingVO);
	/**
	 * 글 조회
	 * @param bo_no
	 * @return 없다면 없다면 없다면 없다면 없다면 없다면 없다면 boardException(unchecked exception) 발생 
	 */
	public BoardVO retriveBoard(long bo_no);
	/**
	 * 글수정
	 * @param board
	 * @return BoardException , INVALIDPASSWORD, ok failed 
	 */
	public ServiceResult modifyBoard(BoardVO board);
	/**
	 * 글 삭제 글 삭제 글 삭제 글 삭ㅈ ㅔ삭제 삭제 삭제 삭제 삭제 삭제 삭제 삭제 삭제 ㅅ각제ㅐ
	 * @param board
	 * @return BoardException , INVALIDPASSWORD, ok failed
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	/**
	 * 첨푸파일 다운르도용 메소드
	 * @param pds_no
	 * @return 없다면 BoardException 발생
	 */
	public PdsVO downLoadPds(long pds_no);
	
	public ServiceResult boomUp(long bo_no) ;

	
}
