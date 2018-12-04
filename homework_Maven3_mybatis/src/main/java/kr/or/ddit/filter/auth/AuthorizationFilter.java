package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

/**
 * @author lsy
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명			보호자원에 접근하고 있는 유저에대해 유저에 부여된 권한과 자원에 설정된 권한 매칭 여부확인.
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public class AuthorizationFilter implements Filter {
	//보호자원 : 권한들
	private Map<String, String[]> securedResources;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		securedResources=(Map<String, String[]>) request.getServletContext().getAttribute(AuthenticationFilter.SECUREDRESURCEATTR);
		HttpServletRequest req=(HttpServletRequest)request;
		String uri=req.getRequestURI();
		System.out.println(uri);
		HttpSession session=req.getSession();
		int cpLength = req.getContextPath().length();
		uri = uri.substring(cpLength).split(";")[0];
		MemberVO authMem_id=(MemberVO) session.getAttribute("authMember");
		boolean pass=true;
		if(securedResources.containsKey(uri)) {
			String[] values=securedResources.get(uri);
			String mem_id=authMem_id.getMem_auth();
			System.out.println(Arrays.toString(values));
			System.out.println(authMem_id.getMem_auth());
//			if(Arrays.binarySearch(values,mem_id)<0) {
//				pass=false;
//				
//			}
		for(String auth:values) {
			System.out.println(auth);
			if(!authMem_id.getMem_auth().trim().equals(auth.trim())) {
					pass=false;
			}
				
		}
			
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			HttpServletResponse resp=(HttpServletResponse)response;
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"잘가라~~~~~~~~라아아아아~ 잘가라아아아아아아아~");
		}
	}

	@Override
	public void destroy() {

	}

}
