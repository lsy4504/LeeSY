package kr.or.ddit.view;

import java.applet.AppletContext;
import java.awt.image.SampleModel;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.dao.ISampleDAO;
import kr.or.ddit.dao.SampleOracleDAO;
import kr.or.ddit.service.SampleService;

public class SampleView {
	public static void main(String[] args) {
		ApplicationContext container= new ClassPathXmlApplicationContext("kr/or/ddit/conf/firstDI.xml");
		String pk= "a001";
		
		SampleService service=(SampleService) container.getBean("sampleService");
		
		String content=service.retrieveSampleContent(pk);
		System.out.println(content);
	}
}
