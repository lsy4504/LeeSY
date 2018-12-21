package kr.or.ddit.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SampleInterceptor implements HandlerInterceptor {
	private static final Logger logger= LoggerFactory.getLogger(SampleInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("{} 호출 전",handler.getClass().getSimpleName());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.setHeader("Cashe-Control", "no-cache");
		logger.info("{} 호출 후 {} 뷰 사용 결정",handler.getClass().getSimpleName(),modelAndView.getViewName());

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("{} 호출 종료 {} 예외 발생",handler.getClass().getSimpleName(),Objects.toString(ex));

	}

}
