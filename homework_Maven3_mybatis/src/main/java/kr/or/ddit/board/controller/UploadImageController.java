package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.FileUploadVO;
import kr.or.ddit.web.calculate.MimeType;

public class UploadImageController implements ICommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req instanceof FileUploadReaquestWrapper) {
			List<FileUploadVO> fileList;
			FileItem fileItem= ((FileUploadReaquestWrapper) req).getFileItem("upload");
			String folderURL="boardImages";
			FileUploadVO file=new FileUploadVO();
			if(fileItem!=null) {
				fileList=new ArrayList<>();
				resp.setContentType(MimeType.JSON.getMimeType());
					File folder=new File(folderURL);
					File savaFile=new File(folder,fileItem.getName());
					file.setFileNmae(fileItem.getName());
					file.setUploaded(1);
					file.setUrl(req.getContextPath()+"/boardImages/"+savaFile.getName());
					System.out.println(savaFile.getAbsolutePath());
					try(
						InputStream in= fileItem.getInputStream();
							) {
						FileUtils.copyInputStreamToFile(in, savaFile);
						
				}
			}
			ObjectMapper mapper=new ObjectMapper();
			mapper.writeValue(resp.getWriter(), file);
		}
		return null;
	}
}
