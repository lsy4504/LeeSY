package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class ConnetionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static String driverClassName;
	private static DataSource dataSource;

	static {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo");
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			driverClassName=bundle.getString("driverClassName");
			int initialSize=Integer.parseInt( bundle.getString("initialSize"));
			int maxActive=Integer.parseInt( bundle.getString("maxActive"));
			long maxWait=Long.parseLong( bundle.getString("maxWait"));
//			DriverManager 와 Datasource(Pooling) 의 차이
//			Simple JDBC 방식 :Conneㅊtion 이 필요할떄 그 즉시 생성
//			Polling 방식 : 미리 일정 갯수의 Connection 을 생성하고,
//							Pool 을 통해 관리 ,필요할때 배분해서 사용.
//			OracleDataSource oracleDS = new OracleDataSource();
//			oracleDS.setURL(url);
//			oracleDS.setUser(user);
//			oracleDS.setPassword(password);
//			dataSource = oracleDS;
			//DBMS에 종속되지 않는 폴링 시스템
			BasicDataSource basicDataSource= new BasicDataSource();
			basicDataSource.setDriverClassName(driverClassName);
			basicDataSource.setUrl(url);
			basicDataSource.setUsername(user);
			basicDataSource.setPassword(password);
			basicDataSource.setInitialSize(initialSize);// 초기 생성 세팅(풀링의 개수)
			basicDataSource.setMaxActive(maxActive);//후에 더필요하다면 10개까지 
			basicDataSource.setMaxWait(maxWait);// 커넥션의 3초이내에 반납되지않고 5개 이상 이면 생성 후 -> 10 개까지 되면 에러 발생
			dataSource=basicDataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnetion() throws SQLException {
//		Connection conn= DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}

}
