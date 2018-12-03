package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtil;

public class ImagesFormServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		String contentFolder = context.getInitParameter("contentFolder");
		File folder = (File) context.getAttribute("contentFolder");
		String[] filenames = folder.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				String mime = context.getMimeType(name);
				if (mime != null) {

					return mime.startsWith("image/");
				}
				return false;
			}
		});
		// form의 method=get
		StringBuffer sb = new StringBuffer();
		resp.setContentType("text/html;charset=UTF-8");

		
		String pattern = "<option>%s</option>\n";
		for (int i = 0; i < filenames.length; i++) {
			sb.append(String.format(pattern, filenames[i]));
		}
		req.setAttribute("optionsAttr", sb.toString());
//		StringBuffer ab= new StringBuffer();
//		String pattern1="<img src=\"img?kk=%s\"/>";
//		Cookie[] cookies = req.getCookies();
//		for (Cookie cookie2 : cookies) {
//			if (cookie2.getName().contains(".")) {
//				System.out.println("타니?");
//				System.out.println(new CookieUtil(req).getCookieValue(cookie2.getName()));
//				System.out.println("타니?");
//				ab.append(String.format(pattern1, cookie2.getName()));
//			}
//		}
//		int start = html.indexOf("@img");
//		int end = start + "@img".length();
//		String replace = sb.toString();
//		html.replace(start, end, replace);
		String imgCookieValue= new CookieUtil(req).getCookieValue("imageCookie");
		StringBuffer imgTags= new StringBuffer();
		if(imgCookieValue!=null) {
			//unmarshalling
			ObjectMapper mapper= new ObjectMapper();
			
			String[] imgNames=mapper.readValue(imgCookieValue, String[].class);//이미지쿠키벨류에있는 제이슨형태를 스트링배열형태로 반환해줌
			String imgpattern="<img src='img?kk=%s'/>";
			for (String string : imgNames) {
				imgTags.append(String.format(imgpattern, string));
			}
		}
		req.setAttribute("imgTags", imgTags);
//		start = html.indexOf("@ag");
//		end = start + "@ag".length();
//		html.replace(start, end, imgTags.toString());
//		int start1 = html.indexOf("@ag");
//		int end1 = start1 + "@ag".length();
//		String replace1 = ab.toString();
//		html.replace(start1, end1, replace1);

//		PrintWriter out = resp.getWriter();// 출력해주기위한 타입
//		out.println(html.toString());
//		out.close();
		String view= "/WEB-INF/views/imageView.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.include(req, resp);

	}

}
