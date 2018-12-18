package kr.or.ddit.tags;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MakeTimeZoneSelectTag extends SimpleTagSupport {
	private String name;
	private String eventHandler;
	public void setName(String name) {
		this.name = name;
	}
	public void setEventHandler(String eventHandler) {
		this.eventHandler = eventHandler;
	}
	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer selTag= new StringBuffer();
		String pattern= "<option value='%s'>%s</option>\n";
		selTag.append("<select name='"+name+"' onchange='"+eventHandler+"'>\n");
		selTag.append("<option value=''>타임존 선택</option>");
		String[] timeZones= TimeZone.getAvailableIDs();
		for (String timeZone : timeZones) {
			selTag.append(String.format(pattern, timeZone, TimeZone.getTimeZone(timeZone).getDisplayName()));
		}
		selTag.append("</select>\n");
		getJspContext().getOut().print(selTag);
	
	}

}
