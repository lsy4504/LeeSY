package kr.or.ddit.dao;

public class SampleMysqlDAO implements ISampleDAO {
	public StringBuffer selectSampleData(String pk){
		StringBuffer result= new StringBuffer();
		result.append(String.format("%s를 받아 %s에서 조회한 데이터.", pk,getClass().getSimpleName()));
		return result;
	}
}
