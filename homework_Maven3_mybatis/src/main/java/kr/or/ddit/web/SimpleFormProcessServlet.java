package kr.or.ddit.web;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.AlbasengVO;
@WebServlet(value="/albamon", loadOnStartup=1)
public class SimpleFormProcessServlet extends HttpServlet {
	public  Map<String, String> gradeMap;
	public  Map<String, String> licenseMap;
	{
		gradeMap = new HashMap<>();
		licenseMap= new LinkedHashMap<>();
		gradeMap.put("G001","고졸");
		gradeMap.put("G002","대졸");
		gradeMap.put("G003","석사");
		gradeMap.put("G004","박사");
		licenseMap.put("L001","정산기");
		licenseMap.put("L002","정처기");
		licenseMap.put("L003","정보산");
		licenseMap.put("L004","정보기");
		licenseMap.put("L005","SQLD");
		licenseMap.put("L006","SQLP");
		
	}
	public  Map<String,AlbasengVO> albasengs = new LinkedHashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context= config.getServletContext();
		context.setAttribute("licenseMap", licenseMap);
		context.setAttribute("gradeMap", gradeMap);
		context.setAttribute("albasengs", albasengs);
		System.out.println(getClass().getSimpleName()+"초기화");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				req.setCharacterEncoding("UTF-8");// 클라이언트에서 보내준 정보를 디코딩(해석해주기위한 메서드) ->get방식에서는 동작 x
		AlbasengVO albasengVO= new AlbasengVO();
		Map<String,String[]>map=req.getParameterMap();
		RequestDispatcher rd= null;
		albasengVO.setName(req.getParameter("name"));
		albasengVO.setAddr(req.getParameter("addr"));
		albasengVO.setTel(req.getParameter("tel"));
		albasengVO.setCareer(req.getParameter("career"));
		albasengVO.setGender(req.getParameter("gender"));
		albasengVO.setGrade(req.getParameter("greade"));
		albasengVO.setLicense(req.getParameterValues("license"));
		req.setAttribute("albaVO", albasengVO);
		if((req.getParameter("age")).matches("\\d{2}")&&req.getParameter("age")!=null) {
			albasengVO.setAge(Integer.valueOf(req.getParameter("age")));
		}
		
//		vo 객체 생성 ,파리미터 할당
//		vo를 대상으로 검증
		Map<String, String> erros= new LinkedHashMap<>();
		req.setAttribute("errors", erros);
		if( StringUtils.isBlank(albasengVO.getName())||StringUtils.isBlank(albasengVO.getAddr())||StringUtils.isBlank(albasengVO.getTel())) {
			erros.put("name"," 이름 누락임 ㅋㅋ ");
			erros.put("tel"," 전번 누락임 ㅋㅋ ");
			erros.put("addr","주소 누락임 ㅋㅋ ");
			rd= req.getRequestDispatcher("/01/simpleForm.jsp");
			rd.forward(req, resp);
		}else {
			albasengVO.setCode(String.format("alba_%03d",albasengs.size()+1));
			albasengs.put(albasengVO.getCode(), albasengVO );
			
			
			resp.sendRedirect(req.getContextPath()+"/05/albaList.jsp");
			
		}
		
//		(이름,전화번호,주소)//검증
//		1)통과
//		code 생성("alba_001")
//		map.put(code,vo)
//		이동(/05/albaList.jsp/ 요청 처리 이후 완료 리다이렉트)
//		2)불통
//		/01/심플폼.jsp
				
		
//		Enumeration<String> name= req.getParameterNames();
//		Map<String,String[]>map=req.getParameterMap();
		
		
//		for (Entry<String, String[]> ent  : map.entrySet()) {
//			String keyName=ent.getKey();
//			String[] valueNames=ent.getValue();
//			System.out.printf("%s:%s\n",keyName,Arrays.toString(valueNames));
//		}//foreach 문을 이용한 로직 맵을 foreach문을 돌리기 위해서는 entryset 이용하기
//		StringBuffer sb= new StringBuffer();
//		String partten="name:%s value:%s\n";
//		while (name.hasMoreElements()) {
//			String parmName=name.nextElement();
//			sb.append(String.format(partten, parmName,Arrays.toString(map.get(parmName))));
//		}
//		System.out.println(sb.toString());
	}
}
