package kr.or.ddit.member.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface IOutMemberDAO {
	public int deleteCartByDeleteMember();
	public int deleteMemberByDelFlag();
	public int prodel();
}
