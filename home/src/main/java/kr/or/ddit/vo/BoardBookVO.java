package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias(value="boardBookVO")
public class BoardBookVO {
	private Long bo_no;
	private String bo_writer;
	private String bo_pass;
	private String bo_ip;
	private String bo_mail;
	private String bo_title;
	private String bo_content;
	private String bo_date;
	private Long bo_hit;
	private Long bo_rcmd;

}
