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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	@Inject
	WebApplicationContext container;
	IOtherDAO other=OtherDAOImpl.getInstance();
	String prodImagesUrl="/prodImages";
	File prodImagesfolder;
	@PostConstruct
	public void init() throws IOException{
		Resource res =container.getResource(prodImagesUrl);
		prodImagesfolder=res.getFile();
		if(!prodImagesfolder.exists()) prodImagesfolder.mkdirs();
	}
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> makeLprodList(){
		List<Map<String, Object>> lprodList=other.selectLprodList();
		return lprodList;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String doGet() throws IOException, ServletException{
		return "prod/prodForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String doPost(@ModelAttribute("prod")ProdVO prod,@RequestPart(required=false)MultipartFile prod_image,Model model) throws IOException, ServletException{
		Map<String, String> errors=new HashMap<>();
		
		String message="";
		model.addAttribute("errors", errors);
		String view="prod/prodForm";
		//검증
		boolean res=valid(prod,errors);
		if(res) {
				if(prod_image!=null && StringUtils.isNotBlank(prod_image.getOriginalFilename()) ) {
					String savename=UUID.randomUUID().toString();
					File saveFile= new File(prodImagesfolder,savename);
					try (
						InputStream in= prod_image.getInputStream();
					){
						FileUtils.copyInputStreamToFile(in, saveFile);
						prod.setProd_img(savename);
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
		model.addAttribute("message", message);
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
