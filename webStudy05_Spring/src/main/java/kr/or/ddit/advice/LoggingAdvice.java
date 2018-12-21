package kr.or.ddit.advice;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
public class LoggingAdvice {
	private static final Logger logger= LoggerFactory.getLogger(LoggingAdvice.class);
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy(){}
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
//		호출전
		long startT=System.currentTimeMillis();
//		호출
		Object target= joinPoint.getTarget();
		Signature signature= joinPoint.getSignature();
		String tagetName=target.getClass().getSimpleName();
		String methodName=signature.getName();
		Object[] args= joinPoint.getArgs();
		Object retValue= joinPoint.proceed(args);
		long endT=System.currentTimeMillis();
		logger.info("{}.{} 실행 소요 시간  : {}ms",tagetName,methodName,endT-startT );
		return retValue;
//		호출후
	}
}
