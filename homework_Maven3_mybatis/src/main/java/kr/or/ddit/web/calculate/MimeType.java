package kr.or.ddit.web.calculate;


public enum MimeType {
	PLAIN("text/plain;charset=UTF-8"
	), 
	HTML(
		 "text/html;charset=UTF-8"
	), JAVASCTIPT("text/javascript;charset=UTF-8"
	), JSON("application/json;charset=UTF-8"
	);
	private String mimeType;

	MimeType(String userMime){
		this.mimeType=userMime;
	}

	public  MimeType getMimeType(String mime) {
		for(MimeType mimeType:MimeType.values()) {
			if(mime.toUpperCase().contains(mimeType.name())){
				return mimeType;
			}
		}
		return null;
	}
}
