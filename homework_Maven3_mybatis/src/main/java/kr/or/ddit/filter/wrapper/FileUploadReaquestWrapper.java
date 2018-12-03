package kr.or.ddit.filter.wrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

public class FileUploadReaquestWrapper extends HttpServletRequestWrapper {
	private Map<String,String[]> parameterMap;//문자열이 아닌 파라미터맵을 담아주기위한 맵
	
	private Map<String,List<FileItem>> fileItemMap;//파트로나눈것중에 input태그의 타입이 file일 경우 요기에 담아줌
	public FileUploadReaquestWrapper(HttpServletRequest request) throws IOException {
		this(request, -1, null);
	}
	public FileUploadReaquestWrapper(HttpServletRequest request,int sizeThreshold, File repository) throws IOException {
		
		super(request);
		parameterMap=new LinkedHashMap<>();//생성
		parameterMap.putAll(request.getParameterMap());//input태그의 모든 파라미터를 가지고옴
		fileItemMap=new LinkedHashMap<>();//생성
		parseRequest(request,sizeThreshold,repository);//parseRequest 메소드 호출
	}
	
	private void parseRequest(HttpServletRequest req,int sizeThreshold, File repository) throws IOException {
//		문자 part는 parameterMap에 누 적  
//		파일 part는 fileItemMap 에 누 적 
		//1. commons-fileupload 라이브러리 추가
				//2. 파일 업로드 핸들러 객체 생성
		DiskFileItemFactory fileItemFactory=new DiskFileItemFactory();//필터 2.5버전의 경우 따로 파트객체를 지원해주지 않으므로
	//라이브러리에서 객체 를 추가
				if(sizeThreshold!=-1) {//크기 기정
					fileItemFactory.setSizeThreshold(sizeThreshold);
				}
				if(repository!=null) {//경로 지정
					fileItemFactory.setRepository(repository);
				}
				ServletFileUpload handler= new ServletFileUpload(fileItemFactory);//세팅된 파일 정보를 ServletFileUpload
				//객체에 담아줌 즉 여기서는 크기와 경로를 담아줌
				//3. 핸들러 객체를 이용해 현재 요청 파싱ㅁ(Part->FileItem)
				req.setCharacterEncoding("UTF-8");//한글이 포함된 파일이 올수도 있으니까 인코딩
				try {
					List<FileItem> fileItems= handler.parseRequest(req);//req즉 요청받은 파라미터를 Fileitem으로 파싱해줌.
					
					//파일 기반의 fileItem에 대한 처리를 수행
					if(fileItems!=null) {
						for (FileItem item : fileItems) {
							String partname=item.getFieldName();//파라미터명
							if(item.isFormField()) {//파트에 담긴 input태그의 타입이 file이면 펄스, 아니면 트루
								//5. 일반 문자열 기반의 FileItem 에대 한 처리와
								String parameterValue=item.getString("UTF-8");//인코딩 방식 담아주기
								String[] alreadyValues=parameterMap.get(partname);//파리미터 맵에 담긴 값을 가지고와서 스트링배열에 저장
								String[] values=null;
								if(alreadyValues==null) {//1회 반복일 경우 null이므로 무조건 들어옴
									values=new String[1];//즉 values의 배열 생성 크기는 1
								}else {//2회차부터 들어옴~
									values=new String[alreadyValues.length+1];
									System.arraycopy(alreadyValues, 0, values, 0, alreadyValues.length);
								}
								values[values.length-1]=parameterValue;//1회차일경우 values의 0번째 방에 utf-8담아줌 
								parameterMap.put(partname, values);//담아줌..ㅎㅎ
							}else {
								//파일을 선택하지 않은 비어있는 part skip
								if(StringUtils.isBlank(item.getName())) continue;
								List<FileItem> alreadyItems= fileItemMap.get(partname);
								if(alreadyItems==null) {//파일의 경우 일로옵니다 ㅎ
									alreadyItems=new ArrayList<FileItem>();
								}
								alreadyItems.add(item);
								fileItemMap.put(partname,alreadyItems );
							}
					}//for end
				}// if end
					
				}catch (FileUploadException e) {
					throw new IOException(e);
				}
		
		
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}//컨트롤러에서 리퀘스트.getParamterMap하면 이곳의 맵이 호출되서 값을주는거임
	@Override
	public String getParameter(String name) {
		String[] values=parameterMap.get(name);
		if(values==null) {
			return null;
		}else {
			return values[0];
		}
	}//키값을 입력하면 0번째의 값 줌 즉 prod_name에 김정희 바보라는 값을 폼에서 입력하면 
	//컨트롤러에서 get("prod_name") 하면 김정희 바보라는 값 반환  
	@Override
	public String[] getParameterValues(String name) {
		
		return parameterMap.get(name);
	}//만약 반환해주는게 스트링 배열이라면 이메소드를 탐
	@Override
	public Enumeration<String> getParameterNames() {
		final Iterator<String> it= parameterMap.keySet().iterator();
		return new Enumeration<String>() {
			
			@Override
			public String nextElement() {
				return it.next();
			}
			
			@Override
			public boolean hasMoreElements() {
				return it.hasNext();
						
			}
		};
	}//모든 파라미터명 즉 prod_id, prod_name .... 등을 주는 메소드
	public FileItem getFileItem(String partname) {
		List<FileItem> itemList = fileItemMap.get(partname);
		if(itemList!=null && itemList.size()>0) {
			return itemList.get(0);
		}else {
			return null;
		}
	}//이곳에서는 이미지태그의 타입이 file이므로 아마 이거 탈거야 연구필요함
	public List<FileItem> getFileItems(String partname){
		return fileItemMap.get(partname);
	}//마찬가지
	public Map<String, List<FileItem>> getFileItemMap() {
		return fileItemMap;
	}//마찬가지
	public Enumeration<String> getFileItemNames(){
		final Iterator<String> it= fileItemMap.keySet().iterator();
		return new Enumeration<String>() {
			
			@Override
			public String nextElement() {
				return it.next();
			}
			
			@Override
			public boolean hasMoreElements() {
				return it.hasNext();
						
			}
		};
		
	}//마찬가지
	
	public void deleteAllTempFile(){
		for (Entry<String, List<FileItem>> entry : fileItemMap.entrySet()) {
			for(FileItem tmp :entry.getValue()) {
				tmp.delete();
			}
		}
	}//삭ㅈ데부분
}
