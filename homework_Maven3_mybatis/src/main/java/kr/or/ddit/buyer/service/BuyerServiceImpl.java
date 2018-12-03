package kr.or.ddit.buyer.service;

import java.util.List;
import java.util.regex.Pattern;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerServiceImpl implements IBuyerService{
	private IBuyerDAO buyerDAO;
	static IBuyerService service;
	
	private BuyerServiceImpl() {
		buyerDAO=BuyerDAOImpl.getInstance();
	}
	
	public static IBuyerService getInstance() {
		if(service==null) {
			service=new BuyerServiceImpl();
		}
		return service;
	}

	@Override
	public ServiceResult registBuyer(BuyerVO buyer) {
		ServiceResult res=null;
			int buyer_code=buyerDAO.insertBuyer(buyer);
			if(buyer_code>0) {
				res=ServiceResult.OK;
			}else {
				res=ServiceResult.FAILED;
			}
			
		
		return res;
	}

	@Override
	public long retribeBuyerCount(PagingInfoVO pagingVO) {
		return buyerDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingInfoVO pagingVO) {
		
		return buyerDAO.selectBuyerList(pagingVO);
	}

	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO buyerVO= buyerDAO.selectBuyer(buyer_id);
		
		if(buyerVO==null) {
			throw new CommonException("에러입니다");
		}
		return buyerVO;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		int cnt= buyerDAO.updateBuyer(buyer);
		ServiceResult result=null;
		if(cnt>0) {
			result=ServiceResult.OK;
		}else {
			result=ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO buyer) {
		BuyerVO buyerVO= retrieveBuyer(buyer.getBuyer_id());
		ServiceResult result=null;
		if(buyerVO!=null) {
			int check =buyerDAO.deleteBuyer(buyerVO.getBuyer_id());
			if(check>0) {
				result=ServiceResult.OK;
			}else {
				result=ServiceResult.FAILED;
			}
			
		}else {
			result=ServiceResult.PKNOTFOUND;
		}
		
		return result;
	}
	
}
