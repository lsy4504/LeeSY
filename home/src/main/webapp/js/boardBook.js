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
				html+="<tr>";
				html+="<td>	NO."+boardBook.bo_no+"</td>";
				html+="<td name='writer'>"+boardBook.bo_writer+"</td>                                                                      ";
				html+="<td>"+boardBook.bo_date+"</td>                                                                                    ";
				html+="<td><span id='updateBoard' bono='"+boardBook.bo_no+"' data-toggle='modal' class='boardUpdateBtn'>[수정]</span></td>  ";
				html+="<td><span id='deleteBoard'>[삭제]</span></td>	                                                                   ";
				html+="</tr>                                                                                                               ";
				html+="<tr id='"+boardBook.bo_no+"'>                                                                                        ";
				html+=" <td  class='.content'>"+boardBook.bo_content+"</td>                                                                ";
				html+=" </tr>	                                                                                                           ";
				html+="<tr>                                                     ";
				html+="<td >                           ";
				html+="	<input type='button' id='replyInset' value='답글 달기' core='"+boardBook.bo_no+"'> ";
				html+="</td>                                                    ";
				html+="</tr>	                                                ";
				})
				$("#boardBookTable").html(html);
				$("#page2").html(resp.pagingHTML);
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
		var content=boardBookTable.find('#'+bono).find('p').text();
		var writer=boardBookTable.find('#'+bono).find('[name=writer]').text();
		modalForm1.find("#bo_no").val(bono);
		modalForm1.find("#bo_content").text(content);
		modalForm1.find("#bo_writer").val(writer);
		updateModal.modal("show");
		
	});
	boardBookTable.delegate("#deleteBoard","click",function(){
		var bono=$(this).attr('bono');
		var content=boardBookTable.find('#'+bono).find('p').text();
		var writer=boardBookTable.find('#'+bono).find('[name=writer]').text();
		updaateForm.attr("action",$.getContextPath()+"/boardBook/boardBookDelete.do");
		modalForm1.find("#bo_no").val(bono);
		modalForm1.find("#bo_content").text(content);
		modalForm1.find("#bo_writer").val(writer);
		updateModal.modal("show");
		
	});
	$("#updateBtn").on("click",function(){
		var bo_no=modalForm1.find("#bo_no").val();
		var bo_pass=modalForm1.find("#bo_pass").val();
		alert(modalForm1.find("#bo_content").text());
		var bo_content=modalForm1.find("#bo_content").val();
		alert(bo_content)
		updaateForm.find("[name=bo_no]").val(bo_no);
		updaateForm.find("[name=bo_pass]").val(bo_pass);
		updaateForm.find("[name=bo_content]").text(bo_content);
		modalForm1[0].reset();
		updaateForm[0].reset();
		updateModal.modal("hide");
		updaateForm.submit();
		updaateForm.attr("action",$.getContextPath()+"/boardBook/boardBookUpdate.do");
	});
	updaateForm.ajaxForm({
		dataType:'JSON',
		success: function(resp){
			if(resp.flag){
				alert(" 성공");
			}else{
				alert(resp.content);
				
			}
			document.insertBoard.reset();
			paging(1);
		},
		error:function(resp){
			alert("dd")
		}
	});
	boardBookTable.delegate("#replyInset","click",function(){
		alert($(this).attr('core'));
		
	});
	var replyForm=$("#replyForm");
	boardBookTable.delegate("#replyList","click",function(){
		var bo_no=$(this).attr('bono');
		replyForm.find("[name=bo_no]").val(bo_no);
		replyForm.submit();
		
	})
	replyForm.ajaxForm({
		dataType:'json',
		success:function(resp){
			html="";
			if(resp.dataList){
			
			$.each(resp.dataList,function(idx,reply){
				html+="<ul id='rep_"+reply.rep_no+"'>";
				html+="<li>"+reply.rep_no+"</li>";
				html+="<li>"+reply.rep_writer+"</li>";
				html+="<li>"+reply.rep_date+"</li>";
				html+="<li>"+reply.rep_content+"</li>";
				html+="</ul>";
			});
			}
			html+="<ul><li><input type='button' name='rep_insert' value='등록'></li>";
			html+="</ul>";
			alert(resp.dataList);
			alert(resp.searchVO.bo_no);
			$('#re_'+resp.searchVO.bo_no).html(html);
			
			
		}
	});
	
	

})