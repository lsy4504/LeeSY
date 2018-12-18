<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.Year"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%-- 	<link href="<%=request.getContextPath() %>/css/Calendar.css" rel="stylesheet" type="text/css"> --%>
<%-- 	<link href="<%=request.getContextPath() %>/css/select.css" rel="stylesheet" type="text/css"> --%>

<script type="text/javascript">
	function langChange() {
		document.formName.submit();
	}
	function sendIt() {
		document.formName2.submit();

	}
	function sendIt2() {
		document.formName2.submit();
	}
</script>
<%
	String selMonth = request.getParameter("month");
	String selYear = request.getParameter("year");
	Calendar calendar = Calendar.getInstance();//캘린더 객체생성
	Calendar startDay = Calendar.getInstance();//시작날짜를 위한 객체생성
	Calendar endDay = Calendar.getInstance();//마지막 날짜를 위한 객체 생성
	int year = calendar.get(Calendar.YEAR);//현재 년도를 받아옴
	int month = calendar.get(Calendar.MONTH);//현재 달
	int day = calendar.get(Calendar.DATE);//현대 날
	if (selMonth != null) {
		month = Integer.parseInt(selMonth);
	}
	if (selYear != null)
		year = Integer.parseInt(selYear);
	//검증부분
	startDay.set(year, month, 1);//시작날짜 세팅
	endDay.set(year, month, startDay.getActualMaximum(Calendar.DATE));//종료날짜 세팅
	int START_DAY_OF_WEEK = startDay.get(Calendar.DAY_OF_WEEK);//무슨 요일에서 시작하나 찾기위한 값
	startDay.add(Calendar.DATE, -startDay.get(Calendar.DAY_OF_WEEK) + 1);//저번달 날짜를 받아오기 위함
	endDay.add(Calendar.DATE, 7 - endDay.get(Calendar.DAY_OF_WEEK));//다음달 날짜를 받아오기 위함
	System.out.println(day);

	String lang = request.getParameter("lang");
	Locale locale = null;
	locale = request.getLocale();
	Locale currentLocale = request.getLocale();
	if (lang != null && lang.trim().length() != 0) {
		currentLocale = Locale.forLanguageTag(lang);
	}

	Map<String, String[]> langMap = new HashMap<String, String[]>();
	Locale locale3 = request.getLocale();

	if (lang != null) {

		System.out.print(lang);
	}
%>
	<%
		Locale[] locales = Locale.getAvailableLocales();
		Locale[] locales2 = calendar.getAvailableLocales();
	%>
	<form action="" name="formName">
	
		<select name="lang" onchange="langChange()">
			<option value="">언어선택</option>
			<%
				String pattern = "<option value=%s %s >%s</option>";
				for (Locale lo : locales) {
					String seloption = "";
					if (currentLocale.equals(lo)) {
						seloption = "selected";
					}
					if (lo.getDisplayLanguage() != null && lo.getDisplayLanguage().trim().length() != 0)
						out.println(String.format(pattern, lo.toLanguageTag(), seloption, lo.getDisplayLanguage()));
				}
			%>
		</select>

	</form>

	<table id="calendar">
		<caption>
				<form name="formName2">
					<select name="year" onchange="sendIt()">
						<%
							String option = "<option value=%s %s >%s년</option>";
							for (int i = year - 5; i <= year; i++) {
								String sel = "";
								if (i == year) {
									sel = "selected";
								}
								out.print(String.format(option, i, sel, i));

							}
							for (int i = year + 1; i < year + 5; i++) {
								String sel = "";
								out.print(String.format(option, i, sel, i));
							}
						%>

					</select> <select name="month" onchange="sendIt2()">
						<%
							option = "<option value=%s %s>%s월";
							for (int i = 0; i <= 11; i++) {
								String sel = "";
								if (i == month) {
									sel = "selected";
								}
								out.print(String.format(option, i, sel, i + 1));
							}
						%>
					</select>
				</form>
				<%
					if (month < 0) {
						month = 11;
						year -= 1;
					}
				%> <a
				href="<%=request.getContextPath()%>/03/CalendarTest.jsp?year=<%=year%>&month=<%=month - 1%>">◀</a>
				<a href="<%=request.getContextPath()%>/03/CalendarTest.jsp"><%=year%>년<%=month + 1%>월</a>

				<%
					if (month > 10) {
						month = -1;
						year += 1;
					}
				%> <a
				href="<%=request.getContextPath()%>/03/CalendarTest.jsp?year=<%=year%>&month=<%=month + 1%>">▶</a>
		
		</caption>
		<tr class="weekdays">
			<%
				for (int i = 1; i <= 7; i++) {
					calendar.set(Calendar.DAY_OF_WEEK, i);
					String dayOfWeekStr = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, currentLocale);
					out.print("<th scope='col'>" + dayOfWeekStr + "</th>");
				}
			%>
		</tr>
		<tr class='days'>
		<%
			String tag = "<td class='day' style='%s'><div class='date'>%d</div></td>";
			for (int i = 1; startDay.before(endDay) || startDay.equals(endDay); startDay.add(Calendar.DATE, 1)) {
				int printDay = startDay.get(Calendar.DATE);
				if (printDay == day) {
					out.print(String.format(tag, "border:3px solid blue;", printDay));

				} else {
					out.print(String.format(tag, "border: 0px;", printDay));
				}
				if (i++ % 7 == 0) {
					out.print("</tr>");
				}
			}
		%>
	</table>
