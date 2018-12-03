package kr.or.ddit.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.filter.wrapper.TestRequestWrapper;

public class FileUploadCheckFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터 초기화");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 요청 필터링~ 필 토 링 필 털링 필트링 필토링
		String contentType = request.getContentType();
		if(contentType!=null && contentType.startsWith("multipart/")) {
			HttpServletRequest req=(HttpServletRequest)request;
			int sizeThreshold=10240;
			File repository=new File("d:/temp");
			
			FileUploadReaquestWrapper wrapper= new FileUploadReaquestWrapper(req,sizeThreshold,repository);
			logger.info("{}에서 multipart request가 wrapper로 변경굄. ",getClass().getSimpleName(),wrapper.getClass().getSimpleName());
			chain.doFilter(wrapper, response);
		}else {
			chain.doFilter(request, response);
		}

		// servletRequest는 http서블릿 리퀘스트보다 상위 개념이므로 다운캐스팅이 필요함~
	}

	@Override
	public void destroy() {
		logger.info("{}필터 소멸");

	}

}
