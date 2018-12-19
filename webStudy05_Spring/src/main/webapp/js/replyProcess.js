/**
 * 게시글에서 상세조회의 댓글 처리 
 */

//wait for the DOM to be loaded 
	 function showResponse(resp, statusText, xhr, $form)  {
			pagingArea=$("#pagingArea");
			listBody=$("#listBody");
        	if(resp.errors){
        		alert(resp.errors.message);
        		return;
        	}
        	
        	var html=""
				if(resp.dataList){
					$.each(resp.dataList,function(idx,reply){
						html+="<tr id='TR_"+reply.rep_no+"'>";
						html+="<td>"+reply.rep_no+"</td>";
						html+="<td>"+reply.rep_writer+"</td>";
						html+="<td>"+reply.rep_date+"</td>";
						html+="<td>"+reply.rep_content+"<span data-toggle='modal' class='replyDelBtn'>[삭제]</span ></td>";
						html+="<td><input type='button' value='수정' data-toggle='modal' class='replyUpBtn'></td>";
						html+="</tr>";
					});
				}else{
					html+="<tr><td colspan='4'> 데이터없즘 .</td></tr>";
				}
        	$("#listBody").html(html);
				pagingArea.html(resp.pagingHTML);
         }
	function pagingReply(page,bo_no) {
		alert("여기는옵니까??")
		$.ajax({
			url : $.getContextPath()+"/reply/replyList.do",
			data :{
				bo_no:bo_no,
				page:page
			},
			dataType : "json",
			success : showResponse,
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}
	$(function(){
		pagingArea=$("#pagingArea");
		listBody=$("#listBody")
		
	})
	$(function() {
		replyForm=$("#insertReply")
		var delModal=$("#dela");
		listBody.on("click",".replyDelBtn",function(){
			var trId=$(this).closest("tr").prop("id");
			var rep_no=trId.substring(trId.indexOf("_")+1);
			delModal.find("#rep_no").val(rep_no);
			alert(delModal.find("#rep_no").val());
			delModal.modal("show");
			
		});
		$("#modalBtn").on("click",function(){
			replyForm.attr("action", $.getContextPath()+"/reply/replyDelete.do");
			var rep_no=delModal.find("#rep_no").val();
			var rep_pass=delModal.find("#rep_pass").val();
			
			replyForm.find("[name=rep_no]").val(rep_no);
			replyForm.find("[name=rep_pass]").val(rep_pass);
			replyForm.submit();
			replyForm.attr("action",$.getContextPath()+"/reply/replyInsert.do");
			replyForm[0].reset();
			$("#modalForm")[0].reset();
			
			delModal.modal("hide");
		});
		
        $(document).ready(function() {  
        	var options={
        			dataType:	'JSON',
        	        success:       showResponse  // post-submit callback 
        	};
        	 $('#insertReply').ajaxForm(options); 
        });
        
         
        // post-submit callback 
       
        });
	$(function() {
		var delModal=$("#update");
		listBody.on("click",".replyUpBtn",function(){
			var trId=$(this).closest("tr").prop("id");
			var rep_no=trId.substring(trId.indexOf("_")+1);
			delModal.find("#rep_no1").val(rep_no);
			alert(delModal.find("#rep_no1").val());
			delModal.modal("show");
			
		});
		$("#updateBtn").on("click",function(){
			replyForm.attr("action", $.getContextPath()+"/reply/replyUpdate.do");
			var rep_no=delModal.find("#rep_no1").val();
			var rep_pass=delModal.find("#rep_pass1").val();
			var rep_content=delModal.find("#rep_content").val();
			
			replyForm.find("[name=rep_no]").val(rep_no);
			replyForm.find("[name=rep_pass]").val(rep_pass);
			replyForm.find("[name=rep_content]").val(rep_content);
			replyForm.submit();
			replyForm.attr("action",$.getContextPath()+"/reply/replyInsert.do");
			replyForm[0].reset();
			$("#modalForm1")[0].reset();
			
			delModal.modal("hide");
		});
		
		$(document).ready(function() {  
			var options={
					dataType:	'JSON',
					success:       showResponse  // post-submit callback 
			};
			$('#insertReply').ajaxForm(options); 
		});
		
		
		// post-submit callback 
		
	});
	