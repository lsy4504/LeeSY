package kr.or.ddit.web.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.FileVO;

//@WebServlet("/upload")
//@MultipartConfig //전처리기 역할을 수행함. .ㅎ
public class FileUploadServlet_3_0 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
//		File saveFolder=new File("D:\\saveFiles");
		// /seaveFiles에 저장..
		String saveUrl="/seaveFiles";
		String savePath =getServletContext().getRealPath(saveUrl);
		File saveFolder= new File(savePath);
		if(!saveFolder.exists()){
			saveFolder.mkdirs();//폴더가 존재하지않으면 생성해줌 
		}
		String uploader=req.getParameter("uploader");
		Map<String,String[]> parameterMap= req.getParameterMap();
		System.out.println(uploader);
		System.out.println(parameterMap.size());
//		System.out.println(parameterMap.get("uploadFile"));
//		Part uploader= req.getPart("uploader");
		Part uploadFile= req.getPart("selectFile");
		String header =uploadFile.getHeader("Content-Disposition");
		String filemime=uploadFile.getContentType();
		if(!StringUtils.startsWithIgnoreCase(filemime, "image/")) {
			resp.sendError(400);
			return;
		}
		int idx=header.lastIndexOf("filename=")+"filename=".length();
		String originalFilename=header.substring(idx).replace("\"", "");
		System.out.println(originalFilename);
		
		System.out.println(idx);
		//middle tier 의 파일에 body를 저장
		String savename=UUID.randomUUID().toString();
		//새로운 문자열을 랜덤으로 생성하여 기존의 파일 이름을 변경하고 확장자도 없애줌 
		//그런 효과로 해킹의 위험에서 벗어남<
		File saveFile= new File(saveFolder,savename);
		try (
				InputStream in= uploadFile.getInputStream();
				FileOutputStream fos= new FileOutputStream(saveFile);
				
				)
		
		{
			byte[] buffer= new byte[1024];
			int pointer=-1;
			while ((pointer=in.read(buffer))!=-1) {
				fos.write(buffer, 0, pointer);
			}
			
		} 
		
		Collection<Part> parts= req.getParts();
		System.out.println(parts.size());
		//db 에 파일의 메타데이터를저장
		
		long filesize=uploadFile.getSize();
		System.out.printf("데이터베이스에 저장할 메타데이터 : 업로더(%s) 원본명(%s),\n 파일크기(%d) 파일종류(%s) 저장위치(%s),저장URL(%s) ",uploader,originalFilename,filesize, filemime, saveFile.getAbsolutePath(),saveUrl+"/"+savename);
		FileVO fileVO= new FileVO();
		fileVO.setUploader(uploader);
		fileVO.setFilemime(filemime);
		fileVO.setFilesize(filesize);
		fileVO.setOriginalFilename(originalFilename);
		fileVO.setSaveFilename(savename);
		fileVO.setSaveFilePath(saveFile.getAbsolutePath());
		fileVO.setSaveFileUrl(saveUrl+"/"+savename);
		req.getSession().setAttribute("fileVO", fileVO);
		Map<String, String> imgMeta=new HashMap<String, String>();
		
		req.setAttribute("imgMeta", imgMeta);
		imgMeta.put("uploader", uploader);
		imgMeta.put("originalFilename", originalFilename);
		imgMeta.put("filesize", String.valueOf(filesize) );
		imgMeta.put("saveFileAb", saveFile.getAbsolutePath());
		imgMeta.put("Url", saveUrl+"/"+savename);
		RequestDispatcher rd=req.getRequestDispatcher("13/fileForm.jsp");
		rd.forward(req, resp);
		
		
		
	}
}
