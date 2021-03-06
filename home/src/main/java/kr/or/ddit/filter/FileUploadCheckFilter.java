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
		String contentType = request.getContentType();//요청의 컨텐트타입을 가져옴
		//컨텐트타입이 필요한이유-> prodForm 의  enctype="multipart/form-data"을 설졍하여 form의 input 태그의
		//입력된 값들을 파라미터가 아닌 파트로 가져오기 때문에 파트를 구분해주기위 컨텐트 타입이필요함
		//contentType에 multipart/form-data~~~~  이런식으로 옴 
		if(contentType!=null && contentType.startsWith("multipart/")) {//컨텐트타입이 널이아니고,시작이 multipart로 오면
			//파트를 나누었다는 의미미로 이프문으로 들어옴
			HttpServletRequest req=(HttpServletRequest)request;//요청을 받은 request는 ServletRequst타입이므로 
//			HttpServletRequest로 다운 캐스팅을 해줌
			
			int sizeThreshold=10240;//파일의 크기를 10240바이트까지
			File repository=new File("d:/temp");//파일이 생성될 경로 설정
			
			FileUploadReaquestWrapper wrapper= new FileUploadReaquestWrapper(req,sizeThreshold,repository);//
			//랩퍼 객체가 생성될때 요청,크기,파일경로를 받아야하므로 3개의 값을 넘겨줌
			logger.info("{}에서 multipart request가 wrapper로 변경굄. ",getClass().getSimpleName(),wrapper.getClass().getSimpleName());
			chain.doFilter(wrapper, response);
		}else {
			//multipart로 시작안하면 frontController로 감 ..
			chain.doFilter(request, response);
		}

		// servletRequest는 http서블릿 리퀘스트보다 상위 개념이므로 다운캐스팅이 필요함~
	}

	@Override
	public void destroy() {
		logger.info("{}필터 소멸");

	}

}
