package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
@Service
public class AuthenticateServiceImpl implements IAuthenticateService{
	IMemberDAO memeberDAO;
	
	@Inject
	public AuthenticateServiceImpl(IMemberDAO memeberDAO) {
		super();
		this.memeberDAO = memeberDAO;
	}

	@Override
	public Object authenticate(MemberVO member) {
		Object result=null;
		MemberVO savedMember= memeberDAO.selectMember(member.getMem_id());
		if (savedMember!=null) {
			if(savedMember.getMem_pass().equals(member.getMem_pass())) {
				result=savedMember;
			}else {
				result=ServiceResult.INVALIDPASSWORD;
			}
		}else {
			
			result=ServiceResult.PKNOTFOUND;
		}
		return result;
	}
}

