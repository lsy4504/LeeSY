package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Alias("memberVO")
@Data
@ToString(exclude= {"mem_img"})
@NoArgsConstructor
@EqualsAndHashCode(of={"mem_id","mem_regno1","mem_regno2"})
public class MemberVO  implements Serializable{

	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
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
	

	
	
	
	
}
