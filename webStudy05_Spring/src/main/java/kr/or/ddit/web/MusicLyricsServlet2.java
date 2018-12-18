package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/song2")
public class MusicLyricsServlet2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String song=req.getParameter("song");
		int status=0;//에러코드를 넣어주기 위한 변수
		String messge=null;//에러코드별로 메시지 출력해줄 변수
		if (song==null || song.trim().length()==0) {
			status=HttpServletResponse.SC_BAD_REQUEST;
			messge="가사파일을 선택해주십쇼..";
		}
		File folder = (File)getServletContext().getAttribute("contentFolder");
		File songFile= new File(folder,song);
		if(!songFile.exists()) {
			status=HttpServletResponse.SC_NOT_FOUND;
			messge="해당 곡은 가사가 없습니다.";
		}
		//해당 파일 존재 유무 체크
		if(status!=0) {
			resp.sendError(status,messge);
		}
		// 검증 부분 통과
		resp.setContentType("text/html;charset=UTF-8");
//		java 1.7 : try with resource 구문
		try (
//				Closable 객체를 할당
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(songFile),"euc-kr"));
				PrintWriter out=resp.getWriter();
				//예외가 유뮤에 상관없이 클로즈해줌
				
				){
			
			String temp=null;
			StringBuffer html = new StringBuffer();
			while ((temp=bufferedReader.readLine())!=null) {
				html.append("<p>"+temp+"</p>");
			}
			out.println(html);
		} /*finally {
			if(bufferedReader!=null) {
				
				bufferedReader.close();
			}
			if (out!=null) {
				
				out.close();
			}*/
			
		
		
		
	}
}
