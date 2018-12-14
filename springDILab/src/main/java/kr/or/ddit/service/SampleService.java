package kr.or.ddit.service;

import kr.or.ddit.dao.ISampleDAO;
import kr.or.ddit.dao.SampleDAOFactory;
import kr.or.ddit.dao.SampleMysqlDAO;
import kr.or.ddit.dao.SampleOracleDAO;

public class SampleService {
//	SampleOracleDAO sampleDAO= new SampleOracleDAO();
//	ISampleDAO sampleDAO=new SampleOracleDAO();
//	1. Factory Method pattern
//	ISampleDAO sampleDAO=new SampleDAOFactory().getSampleDAO();
//	2. Strategy Pattern;
	ISampleDAO sampleDAO;
	public void setSampleDAO(ISampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	public String retrieveSampleContent(String pk){
		CharSequence rawData= sampleDAO.selectSampleData(pk);
		return rawData+"  를 다시 재가공함.";
	}
}
