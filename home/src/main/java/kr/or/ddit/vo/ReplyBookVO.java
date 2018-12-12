package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias(value="replyBookVO")
public class ReplyBookVO {
	private Long rep_no;
	private Long bo_no;
	private String rep_writer;
	private String rep_ip;
	private String rep_pass;
	private String rep_content;
	private String rep_date;
}
