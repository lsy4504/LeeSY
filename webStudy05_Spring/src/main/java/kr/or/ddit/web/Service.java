package kr.or.ddit.web;

import org.apache.commons.lang3.StringUtils;

public enum Service {
	GUGUDAN(
		"/01/gugudanform.html"
	),
	LURICS(
		"/02/musicForm.jsp"
	),
	CALENDER(
		"/03/CalendarTest.jsp"
	),
	IMAGE(
			"/imageForm"),
	GUGUDANDO("/gugudan.do");
	private  String commend;

	public  String getCommend() {
		return commend;
	}
	private Service(String commend) {
		this.commend=commend;
	}
	public static Service getService(String commend) {
		Service res=null;
		for (Service a : Service.values()) {
			if(commend.toUpperCase().equals(a.name())) {
				res=a;
			}
		}
		return res;
				
	}
	
	
	
	
}
