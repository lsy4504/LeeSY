package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MusicLyricsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context= req.getServletContext();
		File folder= new File(getServletContext().getInitParameter("contentFolder"));
		String[] filenames=folder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime =context.getMimeType(name);
				if(mime!=null) {
					
					return mime.startsWith("text/");
				}
				return false;
			}
		});
		StringBuffer sb= new StringBuffer();
		resp.setContentType("text/html;charset=UTF-8");
		
		InputStream in= this.getClass().getResourceAsStream("music.html");//char
		InputStreamReader isr= new InputStreamReader(in,"UTF-8");//cahr
		BufferedReader br = new BufferedReader(isr);//효율성을 위한 버퍼
		StringBuffer html= new StringBuffer();
		String temp=null;
		while((temp=br.readLine())!=null){
			html.append(temp+"\n");
		}
		String pattern="<option>%s</option>\n";
		for (int i = 0; i < filenames.length; i++) {
			sb.append(String.format(pattern, filenames[i]));
		}
		int start= html.indexOf("@img");
		int end =start + "@img".length();
		String replace= sb.toString();
		html.replace(start,end,replace);
		
		PrintWriter out=resp.getWriter();//출력해주기위한 타입
		out.println(html.toString());
		out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name=req.getParameter("kk");
		if(name==null ||name.trim().length()==0) {
			resp.sendError(400, "얌마 에러자나");
			return;//doGet메서드 자체가 호출이기 때문에 리턴시 메서드 종료 
		}//오류 걸러내기 부분 
		File img=new File(getServletContext().getInitParameter("contentFolder")+"/"+name);
		if(!img.exists()) {
			resp.sendError(404, "이미지 없음..;");
			return;//doGet메서드 자체가 호출이기 때문에 리턴시 메서드 종료 
		}
		//html 읽어 오는 인풋 스트림
		InputStream in= this.getClass().getResourceAsStream("musicText.html");//res에 있는 경로를 찾아서 가져오는거
		InputStreamReader isr= new InputStreamReader(in,"UTF-8");//cahr
		BufferedReader br = new BufferedReader(isr);//효율성을 위한 버퍼
		StringBuffer htmltext= new StringBuffer();
		String temptext=null;
		while((temptext=br.readLine())!=null){
			htmltext.append(temptext+"\n");
		}
		
		
		ServletContext context=req.getServletContext();//어떤 파일이 넘어 올지 모르므로 클라이언트가 선택한 마임타입을
		//가져오기위한 방법
		//파일 읽어 오는 인풋스트림
//		FileReader fileReader= new FileReader(img);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(img), "euc-kr"));
		resp.setContentType(context.getMimeType(name));
		StringBuffer html= new StringBuffer();
		String temp=null;
		while((temp=reader.readLine())!=null){
			html.append(temp+"<br>");
		}
		reader.close();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		int start= htmltext.indexOf("@text");
		int end =start + "@text".length();
		String replace= html.toString();
		htmltext.replace(start,end,replace);
		PrintWriter out=resp.getWriter();//출력해주기위한 타입
		out.println(htmltext.toString());
		out.close();
	}
}
