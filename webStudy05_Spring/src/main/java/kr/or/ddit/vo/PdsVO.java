package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Alias("pdsVO")
public class PdsVO implements Serializable {
	
	public PdsVO(MultipartFile fileItem) {
		super();
		this.item = fileItem;
		String saveName = UUID.randomUUID().toString();// 파일 가 이름 생성
		
		setPds_mime(fileItem.getContentType());
		setPds_size(fileItem.getSize());
		setPds_filename(fileItem.getOriginalFilename());
		setPds_savename(saveName);
		setPds_fancysize(FileUtils.byteCountToDisplaySize(fileItem.getSize()));
	}
	private Long pds_no;
	private Long bo_no;
	private String pds_filename;
	private String pds_savename;
	private String pds_mime;
	private Long pds_size;
	private String pds_fancysize;
	private MultipartFile item;
	
}
