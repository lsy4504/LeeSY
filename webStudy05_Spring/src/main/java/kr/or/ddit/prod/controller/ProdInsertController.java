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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;
@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	@Inject
	IProdService service;
	@Inject
	WebApplicationContext container;
	@Inject
	IOtherDAO other;
	@Value("#{appInfo['prodImages']}")
	String prodImagesUrl;
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
	public String doPost(@Validated(InsertGroup.class) @ModelAttribute("prod")ProdVO prod
			,Errors errors,@RequestPart(required=false)MultipartFile prod_image,Model model) throws IOException, ServletException{
		
		String message="";
		String view="prod/prodForm";
		//검증
		boolean res=!errors.hasErrors();
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
	

	
}
