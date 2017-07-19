<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
 
 String ss= request.getParameter("paperId")  ;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" > 
 
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">

<script src="<%=basePath %>bootstrap/js/jquery-2.1.1.js"></script>
<script src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
<script
	src="<%=basePath %>bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="<%=basePath %>bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script
	src="<%=basePath %>bootstrap/bootstrap-datetimepicker/js/moment.js"></script>
<script src="angular/angular.min.js"></script>



<meta charset="UTF-8">
</head>
<script type="text/javascript">
 
var ctrPage=1;//翻页按钮的页面
var ctrpagenum=5;//翻页按钮每页有几个按钮
var totalPagest;//总共有多少页
var pageSizet=4;//每页多少行
var curPageT=1;
var dataList;
 
 
$(document).on("click","#page li", function(){
		 
	var pagectro= $(this).text();
		  
	if(pagectro=='previous'){
		ctrPage--;
		if(ctrPage==0){
			ctrPage=1;
		}
	}else if(pagectro=='next'){
		ctrPage++;
		if(ctrPage>Math.ceil(totalPagest/ctrpagenum)){
			ctrPage--;
		}
	}else{
		curPageT=pagectro;
	}	
	edit_paper(curPageT);
	return false;
}); 
 
function edit_paper(curPage){
	
	url="PaperServlet?action=get_problems";
    $("#paperlist tr:not(:first)").remove();
    
    var paperId=$("#paperId").val();
 
    var args={"paperId":paperId,
			 "curPage":curPage,
			 'pageSize':pageSizet,
			 "time":new Date()
    };
	  
    $.getJSON(url,args,function(data){	
    	totalPages=data.totalPages;
		totalPagest=totalPages;
		dataList = data.list;
		$("#problemlisttbody tr").remove();
		for(var i=0;i<(data.list).length;i++){
			var programProblemId =  (data.list)[i].programProblemId;
			var title = (data.list)[i].title;
			var chapterName = (data.list)[i].chapterName;
			var tr="<tr><td>"+programProblemId+"</td>"
				+"<td>"+title+"</td>"
				+"<td>"+chapterName+"</td>"
				+"<td> <button class='btn btn-default' onclick='detail_problem(this.value)' value='"+programProblemId+"'>详情</button></td>"
				+"<td> <button class='btn btn-default' onclick='delete_problem(this.value)' value='"+programProblemId+"'>删除</button></td></tr>";
				$("#problemlisttbody").append(tr);
		}
		$("#page li").remove();
		$("#page").append( "<li><a href='#'>previous</a></li>");
		for(var i=0; (i+1+(ctrPage-1)*ctrpagenum)<=totalPagest&&i<ctrpagenum;i++){
			if(curPageT==(i+1+(ctrPage-1)*ctrpagenum)){
				//$("#page").append(" <li><a href='avaScript:return false;' style='opacity: 0.3;background-color:#000000'>"+(i+1)+"</a></li>");
				$("#page").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");	
			}else{
				$("#page").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");	
			}	 
		}		 
		$("#page").append( "<li><a href='#'>next</a></li>");
	});
}

jQuery(document).ready(function() {
var ss=<%=ss %>;
});

function delete_problem(problemId) {
	var paperId = $("#paperId").val();
	url="PaperServlet?action=delete_problem";
	var args = {
		"problemId" : problemId,
		"time" : new Date(),
		"paperId" : paperId
	};
	$.post(url, args, function(data) {
		edit_paper(curPageT);
	});
}

function detail_problem(studentId) {

	var url = "StudentServlet?action=reset_password";
	var args = {
		"studentId" : studentId,
		"time" : new Date()
	};
	$.post(url, args, function(data) {
		
	});
}
</script>
<body>
<br> <br>
	<div class="row col-md-1"></div>
	<div class="row col-xs-2  col-md-1">
		<div></div>
		<jsp:include page="../frame/AdminLeft.jsp"></jsp:include>

	</div>
	<div class="row col-xs-1 col-md-1"></div>
	<div class="row col-xs-4 col-md-4">
	<div class="container">
		<form role="form" method="post" onsubmit="return false;">

			<div class="form-group row col-md-8">
				<label for="name">输入试卷编号</label> <input type="text" id="paperId" />
				<button onclick="edit_paper(ctrPage)">编辑</button>
			</div>
			<div class="form-group row col-md-8">
			<!-- 按钮触发模态框 -->
				<button class="btn btn-primary btn-lg" style="margin-left: 50px" data-toggle="modal" data-target="#addProblemModal">添加</button>
			</div>
		</form>
	</div>
	
	<!-- 选择参与人员 -->
	<div class="modal fade" id="addProblemModal" tabindex="-1" role="dialog" aria-labelledby="problemModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="problemModalLabel">选择题目</h4>
				</div>
				<div class="modal-body">
					<div class="container" id="problemChooseDiv"></div>			
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div>

	<div class="container  ">
		<div class="row col-md-10">
			<table class="table table-striped" id="paperlist">
				<thead>
					<tr>
						<th>id</th>
						<th>标题</th>
						<th>章节</th>
						<th>详情</th>
						<th>删除</th>
					</tr>
				</thead>
				<colgroup>
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 20%">
				</colgroup>
				<tbody id="problemlisttbody">
				</tbody>
			</table>
		</div>
	</div>
	<div class="container">
		<ul class="pagination" id="page">
		</ul>
	</div></div>
</body>
<script type="text/javascript">

/* 模态框显示以后执行的回调 */
$('#addProblemModal').on('shown.bs.modal', function () {
	$("#problemChooseDiv").load("./admin/question/question_list.jsp");
})

</script>
</html>