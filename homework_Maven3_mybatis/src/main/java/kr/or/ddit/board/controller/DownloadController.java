package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.vo.PdsVO;
@CommandHandler
public class DownloadController {
	@URIMapping(value="/board/download.do",method=HttpMethod.GET)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("여기는옵니까??");
		String pds_noStr=req.getParameter("what");
		long pds_no;
		if(StringUtils.isNumeric(pds_noStr)) {
			pds_no= Long.parseLong(pds_noStr);
		}else {
			resp.sendError(404);
			return null;
		}

		IBoardService	service = new BoardServiceImpl();
		
		PdsVO pds=	service.downLoadPds(pds_no);
		if (pds==null) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		String fileName= pds.getPds_filename();
		String agent=req.getHeader("User-Agent");
		if(StringUtils.containsIgnoreCase(agent, "msie")||StringUtils.containsIgnoreCase(agent, "trident")) {
			
			fileName=URLEncoder.encode(fileName, "UTF-8");
		}else {
			fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			
		}
		
		String filepath="D:/boardFiles/";
		File downloadFile= new File(filepath,pds.getPds_savename());
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Length", String.valueOf(pds.getPds_size()) );
		resp.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		try(
		OutputStream out=resp.getOutputStream();
				){
			FileUtils.copyFile(downloadFile, out);
		}
		return null;
	}

}
