package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * @author lsy
 * @since 2018. 11. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 11. 26.      작성자명       상품관리를 위한 레이어
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IProdDAO {
	/**
	 * 신규 상품 등록
	 * @param prod 등록할 상품의 정보를 가진 VO
	 * @return 신규 등록된 상품코드
	 */
	public int insertProd(ProdVO prod);
	/**
	 * 상품 정보 상세조회
	 * @param prod_id 조회할 상품 코드
	 * @return 노존재는 널
	 */
	public ProdVO selectProd(String prod_id);
	/**
	 * 페이징 처리된 상품 목록 조회
	 * @param pagingVO
	 * @return 노존재시 0
	 */
	public long selectTotalRecord(PagingInfoVO<ProdVO> pagingVO);
	
	/**
	 * 페이징 처리된 상품 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingInfoVO<ProdVO> pagingVO);
	
	/**
	 * 상품 수정...
	 * @param prod
	 * @return 실패시 0
	 */
	public int updatePord(ProdVO prod);
}
