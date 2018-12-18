package kr.or.ddit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;

public class ImageServiceServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imgName = req.getParameter("kk");
		if (imgName == null || imgName.trim().length() == 0) {
			resp.sendError(400, "얌마 에러자나");
			return;// doGet메서드 자체가 호출이기 때문에 리턴시 메서드 종료
		} // 오류 걸러내기 부분
		System.out.println(imgName);
		File folder = (File) getServletContext().getAttribute("contentFolder");
		File img = new File(folder + "/" + imgName);
		if (!img.exists()) {

			resp.sendError(404, "이미지 없음..;");
			return;// doGet메서드 자체가 호출이기 때문에 리턴시 메서드 종료
		}
//		Cookie cookie = CookieUtil.createCookie(imgName, folder + "/" + imgName, 60 * 60 * 24 * 3);
//		resp.addCookie(cookie);
		//쿠키값 : A,B
		String imgCookieValue= new CookieUtil(req).getCookieValue("imageCookie");
		String[] cookieValues=null;
		ObjectMapper mapper= new ObjectMapper();
		if(StringUtils.isBlank(imgCookieValue)) {
			cookieValues= new String[] {
					img.getName()
			};
		}else {
			String[] cValues= mapper.readValue(imgCookieValue, String[].class);
			cookieValues=new String[cValues.length+1];
			System.arraycopy(cValues, 0, cookieValues, 0, cValues.length);
			cookieValues[cookieValues.length-1]=img.getName();
		}
		
//		imgCookieValue= Arrays.toString(cookieValues);
//		imgCookieValue= imgCookieValue.replaceAll("[\\[\\]\\s]", "");
		//marshalling
		imgCookieValue= mapper.writeValueAsString(cookieValues);
		System.out.println(imgCookieValue);
		Cookie imageCookie = CookieUtil.createCookie("imageCookie",imgCookieValue ,req.getContextPath(),TextType.PATH ,60 * 60 * 24 * 3);
		resp.addCookie(imageCookie);
		ServletContext context = req.getServletContext();// 어떤 파일이 넘어 올지 모르므로 클라이언트가 선택한 마임타입을
		// 가져오기위한 방법

		System.out.println("오니");
//		Cookie[] cookies = req.getCookies();
//		for (Cookie cookie2 : cookies) {
//			if (cookie2.getName().contains(".")) {
//				resp.setContentType(context.getMimeType(cookie2.getName()));
//				FileInputStream fis = new FileInputStream(img);
//				OutputStream out = resp.getOutputStream();
//				byte[] buffer = new byte[1024];
//				int pointer = -1;
//				while ((pointer = fis.read(buffer)) != -1) {// -1 : EOF문자
//					out.write(buffer, 0, pointer);// 1.5kb 일경우 읽어들인 만큼만 복사됨
//				}
//				
//			}
//		}
		resp.setContentType(context.getMimeType(imgName));
		FileInputStream fis = new FileInputStream(img);
		OutputStream out = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while ((pointer = fis.read(buffer)) != -1) {// -1 : EOF문자
			out.write(buffer, 0, pointer);// 1.5kb 일경우 읽어들인 만큼만 복사됨
		}
		fis.close();
		out.close();

		// 파라미터 받기...
		// 이미지 스트링 서비스 제공...
	}
}
