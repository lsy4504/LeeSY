package kr.or.ddit.web.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

public class FileUploadServlet_2_5 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> parameterMap= new LinkedHashMap<>();
		Map<String, List<FileItem>> fileItemMap=new LinkedHashMap<>();
		//1. commons-fileupload 라이브러리 추가
		//2. 파일 업로드 핸들러 객체 생성
		DiskFileItemFactory fileItemFactory=new DiskFileItemFactory(10240, new File("d:/temp"));
		ServletFileUpload handler= new ServletFileUpload(fileItemFactory);
		//3. 핸들러 객체를 이용해 현재 요청 파싱ㅁ(Part->FileItem)
		req.setCharacterEncoding("UTF-8");
		try {
			List<FileItem> fileItems= handler.parseRequest(req);
			//파일 기반의 fileItem에 대한 처리를 수행
			if(fileItems!=null) {
				for (FileItem item : fileItems) {
					String partname=item.getFieldName();//파라미터명
					if(item.isFormField()) {
						//5. 일반 문자열 기반의 FileItem 에대 한 처리와
						String parameterValue=item.getString("UTF-8");
						String[] alreadyValues=parameterMap.get(partname);
						String[] values=null;
						if(alreadyValues==null) {
							values=new String[1];
						}else {
							values=new String[alreadyValues.length+1];
							System.arraycopy(alreadyValues, 0, values, 0, alreadyValues.length);
						}
						values[values.length-1]=parameterValue;
						parameterMap.put(partname, values);
					}else {
						//파일을 선택하지 않은 비어있는 part skip
						if(StringUtils.isBlank(item.getName())) continue;
						List<FileItem> alreadyItems= fileItemMap.get(partname);
						if(alreadyItems==null) {
							alreadyItems=new ArrayList<FileItem>();
						}
						alreadyItems.add(item);
						fileItemMap.put(partname,alreadyItems );
					}
			}//for end
		}// if end
			System.out.println(parameterMap.get("uploader")[0]);
			System.out.println(fileItemMap.get("selectFile").get(0));
			
		}catch (FileUploadException e) {
			e.printStackTrace();
		}
		//4. FileItem들을 대상으로 반복실행
		//파일 기반의 FileItem에 대한 처리를 수행
		InputStream in= req.getInputStream();
		
	}
}
