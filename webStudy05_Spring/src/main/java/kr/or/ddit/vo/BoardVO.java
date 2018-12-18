package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.type.Alias;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of= {"bo_no","board_writer"})
@Alias("boardVO")
public class BoardVO implements Serializable {
	private Long rowNo;
	private Long bo_no;
	@NotBlank(message = "작성자는 필수에요..")
	private String board_writer;
	@NotBlank(message = "비밀번호도 필수에요...")
	private String bo_pass;
	private String bo_ip;
	private String bo_mail;
	private String bo_title;
	private String bo_content;
	private String bo_date;
	private Long bo_hit;
	private Long bo_rcmd;
	private List<PdsVO> pdsList;
	private List<ReplyVO> replyList;
	private Long[] delFiles;
	private Integer  bo_level;
	private Long bo_parent;

	private List<FileItem> itemList;

	public void setItemList(List<FileItem> fileItems) {
		this.itemList = fileItems;
		if (fileItems != null) {

			List<PdsVO> pdsList = null;
			pdsList = new ArrayList<PdsVO>();
			for (FileItem fileItem : fileItems) {

				
				
				pdsList.add(new PdsVO(fileItem));

			}
			this.pdsList = pdsList;

		}

	}

}
