package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.BuyerVO;

public interface IBuyerDAO {
	public BuyerVO selectBuyer(String buyer_id);
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO);
	public int insertBuyer(BuyerVO buyer);
	public int updateBuyer(BuyerVO buyer);
	public int deleteBuyer(String buyer_id);
	public long	selectTotalRecord(PagingInfoVO pagingVO);
}
