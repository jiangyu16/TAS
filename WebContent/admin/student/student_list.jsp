<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <%
 
 String ss= request.getParameter("classId")  ;

%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" > 
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
   	<link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="bootstrap/css/blog-home.css" rel="stylesheet">
    <script src="<%=basePath %>bootstrap/js/jquery-2.1.1.js"></script>
     <script src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
</head>
<!--CourseServlet?action=get_course--> 
<script type="text/javascript">
 
var ctrPage=1;//翻页按钮的页面
var ctrpagenum=5;//翻页按钮每页有几个按钮
var totalPagest;//总共有多少页
var pageSizet=9;//每页多少行
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
	get_students_fun(curPageT);
	return false;
}) ; 
 
function get_students_fun(curPage){
	
	 
	url="StudentServlet?action=get_students";
    $("#studentlist tr:not(:first)").remove();
    
    var classId=$("#classId").val();
 
    var args={"classId":classId,
			 "curPage":curPage,
			 'pageSize':pageSizet,
			 "time":new Date()
    };
	  
    $.getJSON(url,args,function(data){	
    	totalPages=data.totalPages;
		totalPagest=totalPages;
		dataList = data.list;
		$("#studentlisttbody tr").remove();
		for(var i=0;i<(data.list).length;i++){
			var studentId =  (data.list)[i].studentId;
			var stuName =  (data.list)[i].stuName;
			var tr="<tr><td>"+studentId+"</td>"
				+"<td>"+stuName+"</td>"
				+"<td> <button class='btn btn-default' onclick='reset_password(this.value)' value='"+studentId+"'>重置密码</button></td>"
				+"<td> <button class='btn btn-default' onclick='delete_student(this.value)' value='"+studentId+"'> 删除</button></td></tr>";
				$("#studentlisttbody").append(tr);
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
	alert(ss);
});

function delete_student(studentId){
	 
	var url="StudentServlet?action=del_student";
	var args={ "studentId":studentId,"time":new Date()};
	$.post(url,args,function(data){
		if(data==1)get_students_fun(ctrPage);
	});
	
}
function reset_password(studentId){
	
	var url="StudentServlet?action=reset_password";
	var args={ "studentId":studentId,"time":new Date()};
	$.post(url,args,function(data){
		//TODO
	});
	
}
/*
$(function(){
	$("#page li:not(:first)").remove();
	$("#page").append(" <li><a href='#'>5</a></li>");
	$("#page").append( "<li><a href='#'>next</a></li>");
});*/
</script>
<body>

	<div class="container">
		<form role="form" method="post" onsubmit="return false;">

			<div class="form-group row col-md-8">
				<label for="name">输入班级编号</label> 
				<input type="text" id="classId"/>
				<button onclick="get_students_fun(ctrPage)">查询</button>
			</div>
			<div class="form-group row col-md-1"></div>
			<div class="form-group row col-md-4"></div>
			<div class="form-group row col-md-1"></div>
			<div class="form-group row col-md-2">
				<div>
					<br>
				</div>
			</div>
		</form>
	</div>

	<div class="container  ">
		<div class="row col-md-10">
			<table class="table table-striped" id="studentlist">
				<thead>
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>操作</th>
						<th>删除</th>
					</tr>
				</thead>
				<colgroup>
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 40%">
					<col style="width: 20%">
				</colgroup>
				<tbody id="studentlisttbody">
				</tbody>
			</table>

		</div>
	</div>
	<div class="container">
		<ul class="pagination" id="page">
		</ul>
	</div>
	<%=ss %>
</body>
</html>