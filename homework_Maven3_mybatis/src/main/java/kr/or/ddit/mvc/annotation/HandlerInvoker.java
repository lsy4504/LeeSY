package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.InvokeHandler;

public class HandlerInvoker {
	/**
	 * 커맨드 핸들러가 가진 핸들러 메소드를 대신 호출하는 역할을 담당하는 일진 
	 * 	String 핸들러메소드(req,resp); 시그니처에 맞게 구현됨.
	 * 
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 */
	public  String InvokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException
	{
		Object handler= mappingInfo.getCommandHandler();
		Method handlerMethod= mappingInfo.getHandlerMethod();
		try {
			return (String)handlerMethod.invoke(handler, req,resp);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("핸들러 호출 도중 예외 발생",e);
		}
	}
}
