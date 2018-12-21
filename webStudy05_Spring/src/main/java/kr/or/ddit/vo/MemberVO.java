package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Alias("memberVO")
@Data
@ToString(exclude= {"mem_img"})
@NoArgsConstructor
@EqualsAndHashCode(of={"mem_id","mem_regno1","mem_regno2"})
public class MemberVO  implements Serializable, HttpSessionBindingListener{
	@NotBlank()
	private String mem_id;
	@NotBlank
	@Length(min=4,max=8)
	private String mem_pass;
	@NotBlank
	private String mem_name;
	@NotBlank(groups=InsertGroup.class)
	@Length(min=6,max=6,groups=InsertGroup.class)
	private String mem_regno1;
	@NotBlank(groups=InsertGroup.class)
	@Length(min=7,max=7,groups=InsertGroup.class)
	private String mem_regno2;
	private String mem_bir;
	@NotBlank
	@Pattern(regexp="\\d{3}-[0-9]{3}")
	private String mem_zip;
	@NotBlank
	private String mem_add1;
	@NotBlank
	private String mem_add2;
	@NotBlank
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	@Email
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Long mem_mileage;
	private String mem_delete;
	private String mem_auth;
	//구매상품목록
	private	List<ProdVO> prodList;
	private byte[] mem_img;
	private MultipartFile mem_image;
	public void setMem_image(MultipartFile mem_image) throws IOException {
		this.mem_image = mem_image;
		if(mem_image!=null && StringUtils.isNotBlank(mem_image.getOriginalFilename())){
			this.mem_img=mem_image.getBytes();
		}
	}
	
	
	public String getMem_imgToBase64(){
		if(mem_img==null) {
			return null;
		}else {
			return Base64.encodeBase64String(mem_img);
		}
		
	}
	public String getAddress() {
		return Objects.toString(mem_add1, "")+" "+Objects.toString(mem_add2, "");
	}
	
	public String getMem_delete() {
		return mem_delete;
	}

	
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		if("authMember".equals(event.getName())) {
			ServletContext application= event.getSession().getServletContext();
			Set<MemberVO> applicationUsers=(Set<MemberVO>) application.getAttribute("applicationUsers");
			applicationUsers.add(this);
		}
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		if("authMember".equals(event.getName())) {
			ServletContext application= event.getSession().getServletContext();
			Set<MemberVO> applicationUsers=(Set<MemberVO>) application.getAttribute("applicationUsers");
			applicationUsers.remove(this);
		}
		
	}
	

	
	
	
	
}
