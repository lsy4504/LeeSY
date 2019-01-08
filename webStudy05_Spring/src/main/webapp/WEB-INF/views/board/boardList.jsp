<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<link href="<%=request.getContextPath() %>/css/board.css" rel="stylesheet" type="text/css">
 
 
    <script type="text/javascript"> 
   
		
 	function ${pagingVO.funcName }(page){
  		$("[name='searchForm']").find("[name='page']").val(page);
// 		document.searchForm.page.value=page;
		$("[name='searchForm']").submit();
  	}
 	$(function() {
 		$.ajax({
			url : "",
			method : "",
			data : "",
			dataType : "",
			success : function(resp) {

			},
			error : function(resp) {

			}
		});
 		var searchForm=$('#searchForm');
        // wait for the DOM to be loaded
        $(window).on("popstate",function(event){
        	console.log(event);
        	if(event.originalEvent.state){
        		var pagingVO=event.originalEvent.state;
        		 var tag="<tr>";
      			var pattern="<td>%V</td>"
      			$.each(pagingVO.dataList,function(idx,p){
      				tag+=pattern.replace("%V",p.rowNo);
      				tag+=pattern.replace("%V",p.bo_no);
      				tag+=pattern.replace("%V",p.bo_title);
      				tag+=pattern.replace("%V",p.board_writer);
      				tag+=pattern.replace("%V",p.bo_date);
      				tag+=pattern.replace("%V",p.bo_hit);
      				tag+=pattern.replace("%V",p.bo_rcmd);
      			tag+="</tr>";
      			});
      			$("#output1").html(tag );
      			$("#page2").html(pagingVO.pagingHTML);
        	}else{
        		location.reload();
        	}
        });
        
        $(document).ready(function() { 
            $('#searchForm').ajaxForm(function() { 
            	var options={
            			dataType:	'JSON',
            			target:        '#output1',   // target element(s) to be updated with server response 
            	        beforeSubmit:  showRequest,  // pre-submit callback 
            	        success:       showResponse  // post-submit callback 
            	};
            	  $('#searchForm').ajaxForm(options); 
            }); 
        });
        function showRequest(formData, jqForm, options) { 
            // formData is an array; here we use $.param to convert it to a string to display it 
            // but the form plugin does this for you automatically when it submits the data 
            var queryString = $.param(formData); 
         
            // jqForm is a jQuery object encapsulating the form element.  To access the 
            // DOM element for the form do this: 
            // var formElement = jqForm[0]; 
         
            alert('About to submit: \n\n' + queryString); 
         
            // here we could return false to prevent the form from being submitted; 
            // returning anything other than false will allow the form submit to continue 
            return true; 
        } 
         
        // post-submit callback 
        function showResponse(resp, statusText, xhr, $form)  { 
            // for normal html responses, the first argument to the success callback 
            // is the XMLHttpRequest object's responseText property 
         
            // if the ajaxForm method was passed an Options Object with the dataType 
            // property set to 'xml' then the first argument to the success callback 
            // is the XMLHttpRequest object's responseXML property 
         
            // if the ajaxForm method was passed an Options Object with the dataType 
            // property set to 'json' then the first argument to the success callback 
            // is the json data object returned by the server 
         
          var tag="<tr>";
			var pattern="<td>%V</td>"
			$.each(resp.dataList,function(idx,p){
				tag+=pattern.replace("%V",p.rowNo);
				tag+=pattern.replace("%V",p.bo_no);
				tag+=pattern.replace("%V",p.bo_title);
				tag+=pattern.replace("%V",p.board_writer);
				tag+=pattern.replace("%V",p.bo_date);
				tag+=pattern.replace("%V",p.bo_hit);
				tag+=pattern.replace("%V",p.bo_rcmd);
			tag+="</tr>";
			});
			$("#output1").html(tag );
			$("#page2").html(resp.pagingHTML);
			var pageNum=$("[name='page']").val(); 
			var queryString=searchForm.serialize();
			
			history.pushState(resp, pageNum+" 페이지","?"+queryString );
			$("[name='page']").val(" "); 
        } 
        $("#output1").on("click","tr",function(){
        	var what=$(this).find("td:nth-child(2)").text();
        	location.href='<c:url value="/board/boardView.do?what=" ></c:url>'+what
        	alert(what)
        })
  });
    </script>
  	
  

  
  
<input type="image" src="<c:url value='/images/korea.png'/>"
			onclick="location.href='?locale=ko'" name="click" value="ko">
		<input type="image" src="<c:url value='/images/america.png'/>"
			onclick="location.href='?locale=en'" name="click" value="en">
	<table>
		<thead>
		<tr>
			<th><spring:message code="board.bo_row" /></th>
			<th><spring:message code="board.bo_no" /></th>
			<th><spring:message code="board.board_writer" /></th>
			<th><spring:message code="board.bo_title" /></th>
			<th><spring:message code="board.bo_date" /></th>
			<th><spring:message code="board.bo_hit" /></th>
			<th><spring:message code="board.bo_rcmd" /></th>
		</tr>	
		</thead>
		<tbody id="output1">
		
		<c:forEach items="${pagingVO.dataList }" var="board">
			<tr>
				<td>${board.rowNo}</td>	
				<td>${board.bo_no}</td>	
				<td>
				<c:forEach begin="2" end="${board.bo_level }">
					&nbsp;
				</c:forEach>
				${board.bo_title}</td>	
				<td>${board.board_writer}</td>	
				<td>${board.bo_date}</td>	
				<td>${board.bo_hit}</td>	
				<td>${board.bo_rcmd}</td>	
			</tr>
		
		</c:forEach>
		
		</tbody>
		<tfoot>
		<tr>
		<td colspan="7">
		<nav aria-label="Page navigation example" id='page2'>
			${pagingVO.pagingHTML }
			</nav>
			</td>
			</tr>
		</tfoot>
	</table>
	<form id="searchForm" action="<c:url value='/board/boardList.do' />" method="post" name="searchForm"> 
		<input type="hidden" name="page">
		<select name="searchType">
			<option value="all">전체</option>
			<option value="writer">작성자</option>
			<option value="title">제목</option>
		</select>
		<script type="text/javascript">
						document.searchForm.searchType.value = "${empty pagingVO.searchType? 'all':pagingVO.searchType}";
					</script>
		<input type="text" name="searchWord" value="${pagingVO.searchWord }"/>
		<input type="submit" value="검색">
		
		<input type="button" value="글 작성" onclick="location.href='<c:url value='/board/boardInsert.do'/>'" >
	</form>

