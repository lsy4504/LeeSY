package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerDAOImpl implements IBuyerDAO {

	private static IBuyerDAO buyerDAO;
	private SqlSessionFactory sqlSessionFactory=null;
//
	private BuyerDAOImpl() {
		sqlSessionFactory=CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
//
	public static IBuyerDAO getInstance() {
		if (buyerDAO == null) {
			buyerDAO = new BuyerDAOImpl();
		}
		return buyerDAO;
	}
	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer_id);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerList(pagingVO);
		}
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			int cnt =mapper.insertBuyer(buyer);
			if(cnt>0) session.commit();
			return cnt;
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			int cnt =mapper.updateBuyer(buyer);
			if(cnt>0) session.commit();
			return cnt;
		}
	}

	@Override
	public int deleteBuyer(String buyer_id) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			int cnt=mapper.deleteBuyer(buyer_id);
			if(cnt>0) session.commit();
			return cnt;
		}
	}

	@Override
	public long selectTotalRecord(PagingInfoVO pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

}
