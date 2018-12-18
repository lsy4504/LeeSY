<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">	
  <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var memberVO={};
	console.log(JSON.stringify(memberVO));
	memberVO.mem_id= "a001";
	console.log(JSON.stringify(memberVO));
	memberVO["mem_name"]="김은대";
	console.log(JSON.stringify(memberVO));
	for(var prop in memberVO){
	console.log(prop+" : "+ memberVO[prop]);
		
	}
	console.log("================");
	$.each(memberVO, function(prop,propValue) {
		console.log(prop+":"+propValue+","+memberVO[prop])
	})
</script>

</head>
<body>
<h4>EL 에서의 집합객체 사용</h4>
<pre>
	<% 
		String[] array= new String[]{"element1","element2","element3"};
		pageContext.setAttribute("array", array);
		List<String> list= new ArrayList<>();
		list.add(";ostVa;ie1");
		list.add(";ostVa;ie2");
		list.add(";ostVa;ie3");
		pageContext.setAttribute("list", list);
		Map<String,Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key-2", new Date());
		pageContext.setAttribute("map", map);
		MemberVO member= new MemberVO("a007","asd");
		pageContext.setAttribute("member", member);
		Set<String> set= new HashSet<>();
		set.add("setV1");
		set.add("setV2");
		set.add("setV1");
		pageContext.setAttribute("set", set);
	%>
	${array[2] },${list.get(1) },
	${list[1] },${list[5] }
	<!--el안에서는 메서드 호출 하지말자.. 에러 발생할수 있음  -->
	${map.get("key1") }
	${map.key1 }
	${map.get("key-2") }
	${map["key-2"] }
	${member.mem_id } ,${member.getMem_id() }, ${member["mem_id"] }
	${set }
</pre>
</body>
</html>