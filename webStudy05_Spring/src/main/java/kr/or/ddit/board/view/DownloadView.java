package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.PdsVO;

public class DownloadView extends AbstractView {
	@Value("#{appInfo.pdsPath}")
	File saveFolder;
	@PostConstruct
	public void init()
	{
	if (!saveFolder.exists())
		saveFolder.mkdirs();
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		PdsVO pds=(PdsVO) model.get("pds");
		
		String fileName= pds.getPds_filename();
		String agent=req.getHeader("User-Agent");
		if(StringUtils.containsIgnoreCase(agent, "msie")||StringUtils.containsIgnoreCase(agent, "trident")) {
			
			fileName=URLEncoder.encode(fileName, "UTF-8");
		}else {
			fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			
		}
		
		File downloadFile= new File(saveFolder,pds.getPds_savename ());
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Length", String.valueOf(pds.getPds_size()) );
		resp.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		try(
		OutputStream out=resp.getOutputStream();
				){
			FileUtils.copyFile(downloadFile, out);
		}

	}

}
