package kr.or.ddit.batch;

import javax.inject.Inject;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IOutMemberService;

public class RegisterOutMemberTasklet implements Tasklet{
	@Inject
	IOutMemberService service;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ServiceResult result= service.registOutMember();
		if(ServiceResult.OK!=result){
			contribution.setExitStatus(ExitStatus.FAILED);
		}
		return RepeatStatus.FINISHED;
	}
}
