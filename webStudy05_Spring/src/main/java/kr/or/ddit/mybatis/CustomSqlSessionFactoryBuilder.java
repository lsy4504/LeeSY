package kr.or.ddit.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try(
				Reader reader= Resources.getResourceAsReader("kr/or/ddit/db/mybatis/Config.xml");
			) {
				sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
				//빌드에 파라미터로 리더와, 인바이어먼트 값을 넘겨주면 인바이어먼트를 쓰겠다는 의미 디펄트는 설정됨 dev
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
