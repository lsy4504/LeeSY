function paging(page) {
		alert("여기는옵니까??")
		$.ajax({
			url : $.getContextPath()+"/boardBook/boardBookList.do",
			data :{
				page:page
			},
			dataType : "json",
			success :function(resp){
				var html="";
				$.each(resp.dataList,function(idx,boardBook){
				html+="<hr>";
				html+="NO."+boardBook.bo_no;
				html+="&nbsp;&nbsp;"+boardBook.bo_writer+"&nbsp;&nbsp;";
				html+=boardBook.bo_date+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				html+="<span>수정</span><span>삭제</span>";	
				html+="<hr>";
				html+="${boardBook.bo_content }";
				})
				$("#boardBookList").html(html);
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}

$(function(){
	
	$("#insertBtn").on("click",function(){
		alert("여기까지만");
		$("#insertBoard").submit();
	})
	$("#insertBoard").ajaxForm({
		dataType:'JSON',
		success: function(resp){
			if(resp.flag){
				alert("등록 성공");
				paging(1);
				document.insertBoard.reset();
			}else{
				alert("서버오류로 인한 실패")
				
			}
		},
		error:function(resp){
			alert("dd")
		}
		
	});
	

})