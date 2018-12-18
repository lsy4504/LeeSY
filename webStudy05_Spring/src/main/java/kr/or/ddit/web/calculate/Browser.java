package kr.or.ddit.web.calculate;

import java.util.Iterator;

import kr.or.ddit.web.Service;

public enum Browser {
	WOW("당신은 익스플로어네?"),
	FIREFOX("당신은 불여우.."),
	CHROME("당신은 크롬..."),
	OTHER("기타.");
	private String bro;
	Browser(String bro) {
		this.bro=bro;
	}
	public  String getBro() {
		return bro;
	}
	public static Browser getBrowser(String user) {
		Browser result=Browser.OTHER;
		for(Browser b: Browser.values()) {
			if(user.toUpperCase().contains(b.name())) {
				return b;
			};
		}
		return result;
	}
	
}
