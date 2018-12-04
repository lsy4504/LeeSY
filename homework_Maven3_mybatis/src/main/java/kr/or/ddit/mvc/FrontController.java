package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontController extends HttpServlet {
	Logger logger= LoggerFactory.getLogger(this.getClass());
	private Map<String, ICommandHandler> handlerMap;
	private String mappingInfo;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mappingInfo=config.getInitParameter("mappingInfo");// 서블릿컨피그에서 초기 설정한 파람을 가지고옴 프로퍼티스 경로가 들어옴
		handlerMap =new HashMap<>();
		ResourceBundle bundle= ResourceBundle.getBundle(mappingInfo);//프로퍼티스 파일 읽어옴.
		Set<String> ketSet= bundle.keySet();//프로퍼티스 파일에서 읽어온 값을 셋에 세팅
		for (String uri : ketSet) {
			String qualfiedName= bundle.getString(uri);//경로가 들어옴...
			try {
				Class<ICommandHandler> handlerClz=(Class<ICommandHandler>) Class.forName(qualfiedName.trim());//클래스를 리턴받음
				ICommandHandler handler=handlerClz.newInstance();// 클래스명.bewInstance를할경우 객체 생성..
				handlerMap.put(uri.trim(),handler);
				logger.info(" {}에 대한 핸들렁... {}등록",uri,qualfiedName);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				logger.error("{}에 대한 핸들러: {} 에서 문제 발생 {}\n", uri,qualfiedName,e.getMessage());
				continue;
			}
		}
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.요청과의 매핑 
//		--web xml에 추가해줘서 작업 종료
//		2.요청 분석(주소)
		String uri = req.getRequestURI();// 주소 분석...
		int cpLength = req.getContextPath().length();
		System.out.println(uri);
//		member/memberList.do;jessionid=asdasd
		System.out.println(uri);
		uri = uri.substring(cpLength).split(";")[0];
		System.out.println(uri);
		ICommandHandler handler= handlerMap.get(uri);
		
		if(handler==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,"해당 서비스 노제공ㄴ");
			return;
		}
		//
//		7. 뷰선택 하지마세요
//		8. 뷰로 이동....하지마세요
		String view = handler.process(req, resp);
		
		System.out.println(view);
		String prefix="/WEB-INF/views/";
		String suffix=".jsp";
		if (view != null) {
			boolean redirect = view.startsWith("redirect:");
			if (redirect) {
				view =view.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath() + view);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher(prefix+view+suffix);
				rd.forward(req, resp);
			}
		} else {
			if(!resp.isCommitted()){
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"커멘드 핸들러에서 뷰가 선택되지 않았습니다.");
			}
		}

	}
}
