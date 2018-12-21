package kr.or.ddit.member.preparer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.vo.MenuVO;

public class MemberViewPreparer implements ViewPreparer{

	@Override
	public void execute(Request req, AttributeContext context) {
		System.out.println("아니 왜 ");
		List<MenuVO> menuList=
				Arrays.asList(
		new MenuVO("/member/memberList.do","회원목록조회"),
		new MenuVO("/member/memberInsert.do","신규회원등록"));
		Map<String, Object> requestScope=req.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
		
	}

}
