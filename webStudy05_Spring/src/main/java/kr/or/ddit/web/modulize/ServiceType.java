package kr.or.ddit.web.modulize;

public enum ServiceType {
	GUGUDAN(
			"/01/gugudanform.html"
		),
		LURICS(
			"/02/musicForm.jsp"
		),
		CALENDER(
			"/04/Calendar.jsp"
		),
		IMAGE(
				"/imageForm"),
		GUGUDANDO("/gugudan.do"),
		LOGIN("/login/loginForm.jsp");
	private String  servicePage;

	private ServiceType(String servicePage) {
		this.servicePage = servicePage;
	}

	public String getServicePage() {
		return servicePage;
	}
	
	
	

}
