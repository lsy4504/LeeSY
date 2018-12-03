package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public interface IProdService {
	/**
	 * 상품 신규 등록
	 * @param prod 등록할 상품 정보
	 * @return 성공 실패  ok 
	 */
	public ServiceResult createProd(ProdVO prod);
	
	/**
	 * 상품 상세 조회
	 * @param pord_id 조회할 상품 코드
	 * @return 존재하지 않는 경우, CommonExceoption 발생 시키래 .. 
	 */
	public ProdVO retriveProd(String pord_id);
	
	/**
	 * 레코드 수
	 * @param pagingVO
	 * @return
	 */
	public long retrieveProdCount(PagingInfoVO<ProdVO> pagingVO);
	
	/**
	 * 전체 상품 조회 페이징되서
	 * @param paginVO
	 * @return
	 */
	public List<ProdVO> retrieveProdList(PagingInfoVO<ProdVO> paginVO);
	
	/**
	 *  상품 정보 수정..
	 * @param prod
	 * @return commonexception OK failed
	 */
	public ServiceResult modifyProd(ProdVO prod);
}
