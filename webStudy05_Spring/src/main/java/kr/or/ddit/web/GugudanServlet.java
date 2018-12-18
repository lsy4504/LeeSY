package kr.or.ddit.web;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.annotation.*;

@WebServlet(value="/gugudan.do",loadOnStartup=2)

public class GugudanServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int min=2;
		int max=9;
	
		String minStr=(req.getParameter("minDan"));
		String maxStr=(req.getParameter("maxDan"));
		if(minStr!=null &&minStr.matches("\\d")) {
			
			min=Integer.parseInt(minStr);
		}
		if(maxStr!=null&&maxStr.matches("[0-9]")) {
			
			max=Integer.parseInt(maxStr);
		}
		resp.setContentType("text/html;charset=UTF-8");
		InputStream in= this.getClass().getResourceAsStream("gugudan.205");
		InputStreamReader isr= new InputStreamReader(in,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer html= new StringBuffer();
		String temp=null;
		while((temp=br.readLine())!=null){
			html.append(temp);
		}
		StringBuffer tag = new StringBuffer();
		for(int i=min; i<=max;i++){
			tag.append("<tr class='success'>");
			for(int j=1; j<=9; j++){
				tag.append(String.format("<td>%d * %d = %d</td>",i,j,i*j));
			}
			tag.append("</tr>");
		}
		int start= html.indexOf("@gugudan");
		int end =start + "@gugudan".length();
		String replace= tag.toString();
		html.replace(start,end,replace);
		
		PrintWriter out=resp.getWriter();
		out.println(html.toString());
//		out.close();
	}
	
}