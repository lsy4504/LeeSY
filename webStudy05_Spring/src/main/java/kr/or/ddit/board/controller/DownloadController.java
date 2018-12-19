package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.PdsVO;
@Controller
public class DownloadController {
	@Inject
	IBoardService service;
	String filepath="D:/boardFiles/";
	File downloadFile;
	@PostConstruct
	public void init(){
		
	}
	
	
	@RequestMapping(value="/board/download.do",method=RequestMethod.GET)
	public String process(@RequestParam(name="what",required=true)long pds_no){
		System.out.println("여기는옵니까??");

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
		
		File downloadFile= newFile(filepath,pds.getPds_savename ());
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
