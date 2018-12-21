package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of= {"bo_no","board_writer"})
@Alias("boardVO")
public class BoardVO implements Serializable {
	private Long rowNo;
	@NotBlank(groups=UpdateGroup.class)
	private Long bo_no;
	@NotBlank()
	private String board_writer;
	@NotBlank()
	private String bo_pass;
	@NotBlank()
	private String bo_ip;
	private String bo_mail;
	@NotBlank()
	private String bo_title;
	private String bo_content;
	@Pattern(regexp="[0-9]{4}-([0][1-9]|[1][0-2])-([0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])")
	private String bo_date;
	private Long bo_hit;
	private Long bo_rcmd;
	private List<PdsVO> pdsList;
	private List<ReplyVO> replyList;
	private Long[] delFiles;
	private Integer  bo_level;
	private Long bo_parent;
	

	private List<FileItem> itemList;
	private MultipartFile[] bo_file;
	

	
	public void setBo_file(MultipartFile[] bo_file) {
		this.bo_file = bo_file;
		List<PdsVO> pdsList = null;
		if(bo_file!=null){
			pdsList = new ArrayList<>();
			for (MultipartFile item : bo_file) {
				if(StringUtils.isBlank(item.getOriginalFilename()))continue;
				pdsList.add(new PdsVO(item));
			}
		}
		if(pdsList.size()>0)
		this.pdsList = pdsList;
		
	}
	public void setItemList(List<FileItem> fileItems) {
//		this.itemList = fileItems;
//		if (fileItems != null) {
//
//			List<PdsVO> pdsList = null;
//			pdsList = new ArrayList<PdsVO>();
//			for (FileItem fileItem : fileItems) {
//
//				
//				
//				pdsList.add(new PdsVO(fileItem));
//
//			}
//			this.pdsList = pdsList;
//
//		}

	}




}
