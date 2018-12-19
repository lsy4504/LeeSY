package kr.or.ddit.buyer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBuyerService {
	public ServiceResult registBuyer(BuyerVO buyer);

	
	public long retribeBuyerCount(PagingInfoVO pagingVO);
	 
	public List<BuyerVO> retrieveBuyerList(PagingInfoVO pagingVO);

	
	public BuyerVO retrieveBuyer(String buyer_id);

	
	public ServiceResult modifyBuyer(BuyerVO buyer);


	public ServiceResult removeBuyer(BuyerVO buyer);
}
