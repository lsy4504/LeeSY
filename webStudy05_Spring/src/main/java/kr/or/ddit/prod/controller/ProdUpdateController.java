package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;
@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController{
	@Inject
	WebApplicationContext container;
	@Inject
	IProdService service;
	@Inject
	IOtherDAO other;

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
		@RequestMapping(method=RequestMethod.POST)
		public String doPost(@ModelAttribute("prod")ProdVO prod,@RequestPart(required=false)MultipartFile prod_image,Model model) throws IOException {
			Map<String, String> errors=new HashMap<>();
			model.addAttribute("errors", errors);
			String message=null;
			String view=null;
				
			boolean res=valid(prod, errors);
			if(res) {
					 if(prod_image!=null && StringUtils.isNotBlank(prod_image.getOriginalFilename())) {
						 String savename=UUID.randomUUID().toString();
						 File saveFile= new File(prodImagesfolder,savename);
						 try(
							InputStream in =prod_image.getInputStream();
								 ){
							 FileUtils.copyInputStreamToFile(in, saveFile);
						 }
						 prod.setProd_img(savename);
				}//객채가 맞는지 확인
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
			
			model.addAttribute("message", message);
			
			
			return view;
		}

		@RequestMapping(method=RequestMethod.GET)
		public String doGet(@RequestParam(name="what",required=true) String prod_id,Model model){
			
			
			ProdVO prod=service.retriveProd(prod_id);
			model.addAttribute("prod", prod);
			
	
			
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
