<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/calendar.jsp</title>
<style type="text/css">
	.sunday{
		background-color: red;
	}
	.saturday{
		background-color: blue;
	}
	table{
		width: 100%;
		height: 500px;
		border-collapse: collapse;
		
	}
	td,th{
		border: 1px solid gray;
	}
</style>
<script type="text/javascript">
	function eventHandler(year,month) {
		var form=document.calForm;
		if((year && month)||month==0){//스크립트에서 널이나 공백일경우 false 값이 있으면 true
		form.year.value=year;
		form.month.value=month;
		}
			
		form.submit();
		return false;
	}
</script>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String yearStr=request.getParameter("year");
	String monthStr=request.getParameter("month");
	String lang= request.getParameter("lang");
	
	
	Locale clientLocale= request.getLocale();
	if(lang!=null && lang.trim().length()>0){
		clientLocale=Locale.forLanguageTag(lang);
		
	}
	DateFormatSymbols symbols= new DateFormatSymbols(clientLocale);
	Calendar cal = getInstance();//인스턴스 생성
	if(yearStr!=null && yearStr.matches("\\d{4}")&& monthStr!=null && monthStr.matches("1[0-1]|\\d")){
		cal.set(YEAR, Integer.parseInt(yearStr));		
		cal.set(MONTH, Integer.parseInt(monthStr));		
		
	}
	int currentYear=cal.get(YEAR);
	int currentMonth=cal.get(MONTH);
	int currentDay=cal.get(DATE);
	cal.set(DAY_OF_MONTH,1);
	int firstDayOfWeek=cal.get(DAY_OF_WEEK);// 달의 시작 위치 
	int offset=firstDayOfWeek-1;//달의 시작위치에서 -1만큼하면 그만큼 공백
	int lastDate=cal.getActualMaximum(DAY_OF_MONTH);//달의 마지막 위치
	cal.add(MONTH,-1);//현재 월에서 -1 해주므로 전달
	int beforYear=cal.get(YEAR);// 전년도
	int beforMonth=cal.get(MONTH);//전달
	cal.add(MONTH,2);
	int nextYear=cal.get(YEAR);// 다음년도
	int nextMonth=cal.get(MONTH);//다음달
	Locale[] locales= Locale.getAvailableLocales();
	
%>
<form action="" name="calForm" method="">
<input type="hidden" name="commend" value="calender"> 
<h4>
<a href="javascript:eventHandler(<%=beforYear%>,<%=beforMonth%>);">이전달</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="number" name="year" value="<%=currentYear%>"
	onblur="eventHandler();"
/>년
<select name="month" onchange="eventHandler();">
	<%
		String[] monthStrs= symbols.getMonths();
		for(int i=0; i<monthStrs.length-1; i++){
			out.print(String.format("<option value='%d' %s>%s</option>", i,i==currentMonth?"selected":"" ,monthStrs[i]));
		}
	%>
</select>
<select name="lang" onchange="eventHandler();">
	<%
		for(Locale lo: locales){
			out.println(String.format("<option value=%s %s>%s</option>", lo.toLanguageTag(), 
					lo==clientLocale?"selected":"",lo.getDisplayLanguage(clientLocale)));	
		}
	%>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a onclick="eventHandler(<%=nextYear%>,<%=nextMonth%>);">다음달</a>
</h4>
</form>
<table>
<thead>
	<tr>
		<%
		
			String[] weeks= symbols.getShortWeekdays();
			for(int i=Calendar.SUNDAY; i<=Calendar.SATURDAY;i++){
				out.print(String.format("<th>%s</th>", weeks[i]));
			}
		%>
	</tr>
</thead>
<tbody>
<%
	int dayCount=1;
	for(int row=1; row<=6; row++){
		out.print("<tr>");
		for(int col=1; col<=7; col++){
			int dateChar=  dayCount++-offset;
			if(dateChar<1||dateChar>lastDate){
			out.print("<td>&nbsp;</td>");
				
			}else{
				String clzValue="nomal";
				if(col==1){
						clzValue="sunday"	;				
				}else if(col==7){
						clzValue="saturday"		;			
					
					
				}
				out.print(String.format("<td class='%s'>%d</td>",clzValue,dateChar));
			}
		}
		out.print("</tr>");
	}

%>
</tbody>
</table>

</body>
</html>