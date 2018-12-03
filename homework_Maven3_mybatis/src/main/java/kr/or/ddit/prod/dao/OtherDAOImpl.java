package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OtherDAOImpl  implements IOtherDAO{
	private SqlSessionFactory sqlSessionFactory;
	private static IOtherDAO dao;
	private OtherDAOImpl() {
		sqlSessionFactory=CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
	public static IOtherDAO getInstance() {
		if(dao==null) {
			dao=new OtherDAOImpl();
		}
		return dao;
	}
	
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IOtherDAO mapper = session.getMapper(IOtherDAO.class);
//			session.selectMap("kr.or.ddit.member.dao.IOtherDAO.selectLprodList","LPRDO_GU","LPROD_NM");
//			Map map=session.selectMap("kr.or.ddit.prod.dao.IOtherDAO.selectLprodList","LPROD_GU");
			return mapper.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList( String buyer_lgu) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			IOtherDAO mapper = session.getMapper(IOtherDAO.class);
			return mapper.selectBuyerList(buyer_lgu);
		}
	}

}
