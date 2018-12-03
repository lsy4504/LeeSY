package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	private static IProdDAO dao;
	private SqlSessionFactory sqlSessionFactory;
	private ProdDAOImpl() {
		sqlSessionFactory=CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
	public static IProdDAO getInstance() {
		if(dao==null) {
			dao= new ProdDAOImpl();
		}
		return dao;
	}
	@Override
	public int insertProd(ProdVO prod) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			int cnt=mapper.insertProd(prod);
			if(cnt>0) session.commit();
			return cnt;
		}
	}
	@Override
	public ProdVO selectProd(String prod_id) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);
		}
	}
	@Override
	public long selectTotalRecord(PagingInfoVO<ProdVO> pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}
	@Override
	public List<ProdVO> selectProdList(PagingInfoVO<ProdVO> pagingVO) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectProdList(pagingVO);
		}
	}
	@Override
	public int updatePord(ProdVO prod) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			int cnt=mapper.updatePord(prod);
			session.commit();
			return cnt;
		}
	}
	
	
			
}
