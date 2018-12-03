<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>


<script type="text/javascript">
	$(function() {
		var imgArea = $("#imgArea");
		var pattern = '<img src="img?kk=%v"/>';
		$("[name='kk']").on("change", function() {
			var filename = $(this).val();
			imgArea.append(pattern.replace("%v", filename));
			//최범균 메모 
		});

	});
</script>

<style type="text/css">
table {
	border: 1px solid black;
	background-color: yellow;
}
</style>
<form action='img' method='get' name="imgForm">
	<select name='kk'> 
		
		<%=request.getAttribute("optionsAttr") %>
	</select>
	<!--<input type='submit' value='전송'>-->
</form>
<div id="imgArea">
<%= request.getAttribute("imgTags") %>
</div>
<script type="text/javascript">
	// 		function changeHandler(event) {
	// 			var imgArea=document.getElementById("imgArea");
	// // 			document.imgForm.submit();
	// // 			var imgArea=$.("imgArea").innerHtml;
	// 			alert(event.target.value);
	// 			var pattern='<img src="img?kk="%v"/>';
	// 			var name=pattern.replace("%v",event.target.value);
	// 			imgArea.innerHTML=pattern.replace("%v",name);
	// // 			document.getElementById("imgArea").innerHtml()=

	// 		}
</script>

