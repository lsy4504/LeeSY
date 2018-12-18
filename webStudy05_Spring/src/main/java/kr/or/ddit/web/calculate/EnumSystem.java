package kr.or.ddit.web.calculate;

public enum EnumSystem {
//	ANDROID("안드로이드~"),
//	IPHONE("아이폰"),
//	OTHER("데스크탑"),
	DESKTOP(new String[] {"windows nt","linux"},"데스크탑"),
	MOBILE(new String[] {"android","iphone"},"모바일"),
	;
	private String[] keywords;
	
	private String system;
	
	EnumSystem(String[] keywords, String system) {
		this.keywords = keywords;
		this.system = system;
	}
	EnumSystem(String system) {
		this.system=system;
	}
	public String getSystem() {
		return system;
	}
	public boolean matches(String user){
		boolean res= false;
		user= user.toLowerCase();
		System.out.println(user);
		for(String s:keywords) {
			System.out.println(s);
			if (user.contains(s)) {
				res= true;
				break;
			}
		}
		return res;
	}
	public static EnumSystem getEnumSyutem(String user) {
//		EnumSystem result=EnumSystem.OTHER;
		System.out.println(user);
		for(EnumSystem s: EnumSystem.values()) {
			if(s.matches(user)) {
				return s;
			}
		}
		return null;
	}
	
}
