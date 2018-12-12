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
				html+="<hr id='"+boardBook.bo_no+"'>";
				html+="NO."+boardBook.bo_no;
				html+="&nbsp;&nbsp;"+boardBook.bo_writer+"&nbsp;&nbsp;";
				html+=boardBook.bo_date+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				html+="<span id='updateBoard' bono='"+boardBook.bo_no+"'>[수정]</span><span id='deleteBoard'>[삭제]</span>";	
				html+="<hr>";
				html+=boardBook.bo_content;
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
	var updateModal=$("#updateBoardModal");
	var updaateForm=$("#updateBoardForm");
	var boardBookTable=$("#boardBookTable");
	var modalForm1=$("#modalForm1");
	boardBookTable.delegate("#updateBoard","click",function(){
		var bono=$(this).attr('bono');
		var content=boardBookTable.find('#'+bono).text();
		var writer=boardBookTable.find('#'+bono).parent().find(writer).text();
		modalForm1.find("#bo_no").val(bono);
		modalForm1.find("#bo_content").text(content);
		updateModal.modal("show");
		
	});
	$("#updateBtn").on("click",function(){
		var bo_no=modalForm1.find("#bo_no").val();
		var bo_pass=modalForm1.find("#bo_pass").val();
		var bo_content=modalForm1.find("#bo_content").text();
		
		updaateForm.find("[name=bo_no]").val(bo_no);
		updaateForm.find("[name=bo_pass]").val(bo_pass);
		updaateForm.find("[name=bo_content]").text(bo_content);
		modalForm1[0].reset();
		updaateForm[0].reset();
		updateModal.modal("hide");
		updaateForm.submit();
	});
	updaateForm.ajaxForm({
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