package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.dao.IOutMemberDAO;
@Service
public class outMemberServiceImpl  implements IOutMemberService{
	@Inject
	IOutMemberDAO outmemberDAO;
	@Transactional
	@Override
	public ServiceResult registOutMember() {
		int rowCnt=outmemberDAO.prodel();
		System.out.println(rowCnt+"몇이나오려나");
//				outmemberDAO.deleteCartByDeleteMember();
//		rowCnt+= outmemberDAO.deleteMemberByDelFlag();
		ServiceResult result= ServiceResult.FAILED;
		if (rowCnt>0||rowCnt==-1) {
			result=ServiceResult.OK;
		}
		return result;
	}
	
}
