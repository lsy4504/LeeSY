package kr.or.ddit.task;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.ddit.member.service.IOutMemberService;

/**
 * 
 * 수요일 하루동안 매 3분 마다 서버의 콘솔에 현재 시각을 출력하시오.. ㅠㅠ 
 * 
 */
@Component
public class PrintCurrentTime {
	@Inject
	IOutMemberService service;
	//매주 월요이ㅏㄹ 새벽 3시 정각
//	@Scheduled(cron="0 0 3 ? * MON")
	public void print(){
		// 탈퇴 회원의 실제 레코드를 삭제하는 작업.
		System.out.println(new Date());
		service.registOutMember();
	}
}
