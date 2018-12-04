package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lsy
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       보호가 필요한 자원에 대해 접근을 시도하는 유저가 인증이 되어 있는지 여부를 확인.ㄲ
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public class AuthenticationFilter implements Filter{
	private Map<String, String[]> securedResources;
	private String securedResourceInfo;
	public static final String SECUREDRESURCEATTR="securedResources";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources=new LinkedHashMap<>();
		filterConfig.getServletContext().setAttribute(SECUREDRESURCEATTR,securedResources );
		securedResourceInfo= filterConfig.getInitParameter("securedResourceInfo");
		ResourceBundle bundle= ResourceBundle.getBundle(securedResourceInfo);
		Enumeration<String> key= bundle.getKeys();
		while (key.hasMoreElements()) {
			String uri = (String) key.nextElement();
			String valueString=bundle.getString(uri);
			String[] auth=valueString.split(",");
			Arrays.sort(auth);
			securedResources.put(uri.trim(),auth);
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest)request;
		String uri = req.getRequestURI();// 주소 분석...
		int cpLength = req.getContextPath().length();
		uri = uri.substring(cpLength).split(";")[0];
		boolean pass= true;
		if(securedResources.containsKey(uri)) {
			HttpSession session= req.getSession();
			if(session.getAttribute("authMember")==null) {
				pass=false;
			}
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			String goPage="/login/loginForm.jsp";
			HttpServletResponse res= (HttpServletResponse)response;
			res.sendRedirect(req.getContextPath()+goPage);
			
		}

		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
