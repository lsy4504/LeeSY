package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadReaquestWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.URIMapping.HttpMethod;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
@CommandHandler
public class ProdInsertController {

	IOtherDAO other=OtherDAOImpl.getInstance();
	
	@URIMapping(value="/prod/prodInsert.do",method=HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		List<Map<String, Object>> lprodList=other.selectLprodList();
		req.setAttribute("lprodList", lprodList);					
		return "prod/prodForm";
	}
	@URIMapping(value="/prod/prodInsert.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		ProdVO prod= new ProdVO();
		Map<String, String> errors=new HashMap<>();
		
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (Exception e) {
			throw new CommonException(e);
		}
		String message="";
		req.setAttribute("errors", errors);
		req.setAttribute("prod", prod);
		String view="prod/prodForm";
		//검증
		boolean res=valid(prod,errors);
		if(res) {
			if(req instanceof FileUploadReaquestWrapper) {
				String prodImagesUrl="/prodImages";
				
				String prodImagesPath= req.getServletContext().getRealPath(prodImagesUrl);
				
				File prodImagesFolder=new File(prodImagesPath);
				
				FileItem fileItem= ((FileUploadReaquestWrapper)req).getFileItem("prod_img");
				if(fileItem!=null) {
					String savename=UUID.randomUUID().toString();
					File saveFile= new File(prodImagesFolder,savename);
					try (
						InputStream in= fileItem.getInputStream();
					){
						FileUtils.copyInputStreamToFile(in, saveFile);
						prod.setProd_img(savename);
					} 
				}
			}
			IProdService service=ProdServiceImpl.getInstance();
			ServiceResult result= service.createProd(prod);
			switch (result) {
			case OK:
				view="redirect:/prod/prodView.do?what="+prod.getProd_id();
				break;
			case PKDUPLICATED:
				view="prod/prodForm";
				break;
			}
			
			
		}else {
			
		}
		req.setAttribute("message", message);
		return view;
	}
	private boolean valid(ProdVO prod, Map<String, String> errors) {
		boolean valid=true;
		if(StringUtils.isBlank(prod.getProd_name())){
		valid=false;
		errors.put("prod_name", ">상품명 미입력 .... <");
		}
		
		if(StringUtils.isBlank(prod.getProd_buyer())){
		valid=false;
		errors.put("prod_buyer", ">판매자 미입력 .... <");
		}
		if(prod.getProd_cost()==null){
		valid=false;
		errors.put("prod_cost", ">매입가 미입력 .... <");
		}
		if(prod.getProd_price()==null){
		valid=false;
		errors.put("prod_price", ">판매가 미입력 .... <");
		}
		if(prod.getProd_sale()==null){
		valid=false;
		errors.put("prod_sale", ">특가 미입력 .... <");
		}
		if(StringUtils.isBlank(prod.getProd_outline())){
		valid=false;
		errors.put("prod_outline", ">상품정보 미입력 .... <");
		}
		if(prod.getProd_totalstock()==null){
		valid=false;
		errors.put("prod_totalstock", ">토탈스톡 미입력 .... <");
		}
		if(prod.getProd_properstock()==null){
		valid=false;
		errors.put("prod_properstock", ">프롭스톡 미입력 .... <");
		}
		if(StringUtils.isNotBlank(prod.getProd_insdate())){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//formatting : 특정타입의 데이터를 일정 형식의 문자열로 변환.
			//parsing: 일정한 형식의 문자열을 특정 타입의 데이터로 변환.
			try{
			formatter.parse(prod.getProd_insdate());
			}catch(ParseException e){
				valid=false;
				errors.put("prod_insdate", ">날짜형식 확인<");
			}
		}
		
		
		return valid;
	}

	
}
