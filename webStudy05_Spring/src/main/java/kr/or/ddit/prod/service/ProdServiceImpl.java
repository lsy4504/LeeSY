package kr.or.ddit.prod.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;
@Service
public class ProdServiceImpl implements IProdService {
	IProdDAO dao;
	@Required
	@Inject
	public void setDao(IProdDAO dao) {
		this.dao = dao;
	}
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt=dao.insertProd(prod);
		ServiceResult res=null;
		if(cnt>0) {
			res=ServiceResult.OK;
		}else {
			res=ServiceResult.PKDUPLICATED;
		}
		System.out.println(res);
		return res;
	}
	@Override
	public ProdVO retriveProd(String pord_id) {
		
		ProdVO prod=dao.selectProd(pord_id);
		if( prod==null)
		throw new CommonException(pord_id+"해당 상품 없!");
		return prod;
	}
	@Override
	public long retrieveProdCount(PagingInfoVO<ProdVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}
	@Override
	public List<ProdVO> retrieveProdList(PagingInfoVO<ProdVO> paginVO) {
		return dao.selectProdList(paginVO);
	}
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult res=null;
		System.out.println("셀렉트는?");
		if(retriveProd(prod.getProd_id())==null) {
			System.out.println("셀렉트는?");
			res=ServiceResult.PKNOTFOUND;
		}else {
			System.out.println("셀렉트는? 이곳은 엘즈");
			int cnt=dao.updatePord(prod);
			if(cnt>0) {
				res=ServiceResult.OK;
			}else {
				res=ServiceResult.FAILED;
			}
			
		}
		return res;
	}
}
