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
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.val;

public class ProdUpdateController implements ICommandHandler {
		@Override
		public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
			String method=req.getMethod();
			IOtherDAO other=OtherDAOImpl.getInstance();
			List<Map<String, Object>> lprodList=other.selectLprodList();
			req.setAttribute("lprodList", lprodList);	
			
			if("get".equalsIgnoreCase(method)) {
				
				return	doGet(req, resp);
			}else if ("post".equalsIgnoreCase(method)) {
				return
						doPost(req,resp);
			}
			
			return null;
		}

		private String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			ProdVO prod=new ProdVO();
			Map<String, String> errors=new HashMap<>();
			req.setAttribute("errors", errors);
			req.setAttribute("prod", prod);
			String message=null;
			String view=null;
			try {
				BeanUtils.populate(prod, req.getParameterMap());
			} catch (Exception e) {
				throw new CommonException(e);
				
			}
			boolean res=valid(prod, errors);
			if(res) {
				if(req instanceof FileUploadReaquestWrapper) {
					String prodImagesUrl="/prodImages";
					String prodImagesPath=req.getServletContext().getRealPath(prodImagesUrl);
					File prodImagesfolder=new File(prodImagesPath);
					if(!prodImagesfolder.exists()) {
						prodImagesfolder.mkdirs();//상위폴더까지 생성해줌.
					}
					FileItem fileItem =((FileUploadReaquestWrapper)req).getFileItem("prod_img");
					 if(fileItem!=null) {
						 String savename=UUID.randomUUID().toString();
						 File saveFile= new File(prodImagesfolder,savename);
						 try(
							InputStream in =fileItem.getInputStream();
								 ){
							 FileUtils.copyInputStreamToFile(in, saveFile);
						 }
						 prod.setProd_img(savename);
					 }
				}//객채가 맞는지 확인
				IProdService service=ProdServiceImpl.getInstance();
				ServiceResult result= service.modifyProd(prod);
				switch (result) {
				case OK:
					view="redirect:/prod/prodView.do?what="+prod.getProd_id();
					break;

				default:
					view="/prod/prodForm";
					break;
				}
				
			}
			
			req.setAttribute("message", message);
			
			
			return view;
		}

		private String doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			String prod_id=req.getParameter("what");
			String view=null;
			
			System.out.println(prod_id);
			if(StringUtils.isBlank(prod_id)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
			IProdService service = ProdServiceImpl.getInstance();
			ProdVO prod=service.retriveProd(prod_id);
			req.setAttribute("prod", prod);
	
			
			return "prod/prodForm";
		}
		private boolean valid(ProdVO prod, Map<String, String> errors) {
			boolean valid=true;
			if(StringUtils.isBlank(prod.getProd_id())) {
				valid=false;
			}
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
	
			
			
			return valid;
		}
		
}
