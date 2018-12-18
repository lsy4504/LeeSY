package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class MemberServiceImpl  implements IMemberSerivce{
	IMemberDAO memeberDAO;
	static IMemberSerivce memberSerivce;
	private MemberServiceImpl() {
		memeberDAO=MemberDAOImpl.getInstance();
	}
	public static IMemberSerivce getInstance() {
		if(memberSerivce==null) {
			memberSerivce=new MemberServiceImpl();
		}
		return memberSerivce;
	}
	
	@Override
	public ServiceResult registMember(MemberVO member) {
		ServiceResult result = null;
		if(memeberDAO.selectMember(member.getMem_id())==null) {
			int res =memeberDAO.insertMember(member);
			if(res>0) {
				result=ServiceResult.OK;
			}else {
				result=ServiceResult.FAILED;
			}
		}else {
		result=ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingInfoVO pagingVO) {
		List<MemberVO> memberList= memeberDAO.selectMemberList(pagingVO);
		return memberList;
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO member=memeberDAO.selectMember(mem_id);
		if(member==null) {
			throw new CommonException("에러에러해...");
		}
		return member; 
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result= null;
		MemberVO getMember=retrieveMember(member.getMem_id()); 
		if(getMember==null) {
			result=ServiceResult.PKNOTFOUND;
		}else {
			if(member.getMem_pass().equals(getMember.getMem_pass())) {
				int res=memeberDAO.updateMember(member);
				if(res>0) {
					result=ServiceResult.OK;
				}else {
					result=ServiceResult.FAILED;
				}
			}else {
				result=ServiceResult.INVALIDPASSWORD;
			}
		}
		
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result= null;
		MemberVO getMember=retrieveMember(member.getMem_id()); 
	
			if(member.getMem_pass().equals(getMember.getMem_pass())) {
				int res=memeberDAO.deleteMember(member.getMem_id());
				if(res>0) {
					result=ServiceResult.OK;
				}else {
					result=ServiceResult.FAILED;
				}
			}else {
				result=ServiceResult.INVALIDPASSWORD;
			}
		
		
		return result;
	}
	@Override
	public long retribeMemberCount(PagingInfoVO pagingVO) {
		return memeberDAO.selectTotalRecord(pagingVO);
	}
	
	
}
