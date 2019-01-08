package kr.or.ddit.task;

import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IOutMemberService;

@Component("registOut")
public class MemberRegisterOutJob {
	private static final Logger logger= LoggerFactory.getLogger(MemberRegisterOutJob.class);
//	@Inject
//	IOutMemberService service;
	@Inject
	JobLauncher jobLauncher;
	@Resource(name="registOutMemberJob")
	Job job;
	@Inject
	JobOperator jobOperator;
	public void executeJobWithEveryWeek() throws Exception{
//		ServiceResult result= service.registOutMember();
		JobParameters jobParameters= new JobParametersBuilder().toJobParameters();
		JobExecution jobExecution= jobLauncher.run(job, jobParameters);
		
		if(BatchStatus.FAILED!=jobExecution.getStatus()){
			logger.error("배치 작업 실패 ... {}",new Date());
			long jobId=jobExecution.getId();
			jobOperator.restart(jobId);
		}
	}
}
