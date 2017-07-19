<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  
  
<!DOCTYPE html>
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
<title>试卷管理</title>
</head>

<script type="text/javascript">

	var ctrPage=1;//翻页按钮的页面
	var ctrpagenum=5;//翻页按钮每页有几个按钮
	var totalPagest;//总共有多少页
	var pageSizet=1;//每页多少行
 	var curPageT=1;
 	var dataList;
 	
	function get_papers_fun(curPage){
		url="PaperServlet?action=get_papers";//
		$("#paperlist tr:not(:first)").remove();
		var courseId = $("#course").val();
		var teacherId = $("#teacher").val();
		var papertype = $("#papertype").val();
		var args = {
				"paperType":papertype,
				"teacherId":teacherId,
				"courseId":courseId,
	            "curPage":curPage,
	            'pageSize':pageSizet,
	            "time":new Date()
		};
		$.getJSON(url,args,function(data){ 
			//$("#paperlist tr").remove();
			totalPages=data.totalPages;
			totalPagest=totalPages;
			dataList = data.list;
			for(var i=0;i<(data.list).length;i++){
				var paperId = (data.list)[i].paperId;
				var paperName = (data.list)[i].paperName;
				var paperType = (data.list)[i].paperType;
				var teacherId = (data.list)[i].teacherId;
				var choiceScore = (data.list)[i].choiceScore;
				var programScore = (data.list)[i].programScore;
				var courseId = (data.list)[i].courseId;
				var tr="<tr><td>"+paperName+"</td>"+"<td>"+paperType+"</td><td>"+teacherId+"</td><td>"
						+choiceScore+"</td><td>"+programScore+"</td><td>"+courseId+"</td>"
					 +"<td> <button class='btn btn-default' onclick='operation_program(this.value)' value='"+paperId+"'> 编程题</button></td>";
					
					$("#paperlist").append(tr);
			  }
			  $("#page li").remove();
				 $("#page").append( "<li><a href='#'>previous</a></li>");
				 for(var i=0;(i+1+(ctrPage-1)*ctrpagenum)<=totalPagest&&i<ctrpagenum;i++){
						$("#page").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");
						 
				  }		 
				 $("#page").append( "<li><a href='#'>next</a></li>");
			
		});
	}

    jQuery(document).ready(function() {
    	var url="PaperServlet?action=get_papertype";
    	var args={"time":new Date()}; 
    	$.getJSON(url, function(data){
    		if(data.length==0){
    			alert("当前系统里没有试卷");
    			}else{
    				
   			for(var i=0;i<data.length;i++){
   				var paperTypeId = data[i].paperTypeId;
   				var paperTypeName = data[i].paperTypeName;
   				$("#papertype").append("<option value='"+paperTypeId+"'>"+paperTypeName+"</option>");	
   				}	
   		}
   		});
    	url="CourseServlet?action=get_course";
   	 	args={"time":new Date()}; 
    	$.getJSON(url, function(data){
    		if(data.length==0){
    			alert("当前系统里没有课程");
    		}else{
    			for(var i=0;i<data.length;i++){
    				var courseId=data[i].courseId;
    				var courseName=data[i].courseName;
    				$("#course").append("<option value='"+courseId+"'>"+courseName+"</option>");
    			}	
    		}
   		});
    	url="TeacherServlet?action=get_teacher";
   	 	args={"time":new Date()}; 
    	$.getJSON(url, function(data){
    		if(data.length==0){
    			alert("当前系统里没有拥有者");
    		}else{
    			for(var i=0;i<data.length;i++){
    				var teacherId=data[i].teacherId;
    				$("#teacher").append("<option value='"+teacherId+"'>"+teacherId+"</option>");
    			}	
    		}
   		});
    	ctrPage=1; curPageT=1;
		get_papers_fun(curPageT);
    });
    
	$("#course").change(function(){
		$("#paperlist tr:not(:first)").remove();
		var course=$(this).val();
		var teacher = $("#teacher").val();
		var papertype = $("#papertype").val();
		ctrPage=1; curPageT=1;
		get_papers_fun(curPageT);
		/* if(course!=0){
			ctrPage=1; curPageT=1;
			get_papers_fun(curPageT);
		}else{
			ctrPage=1; curPageT=1;
		} */
	});
	$("#teacher").change(function(){
		$("#paperlist tr:not(:first)").remove();
		var course=$("#course").val();
		var teacher = $(this).val();
		var papertype = $("#papertype").val();
		ctrPage=1; curPageT=1;
		get_papers_fun(curPageT);
		/* if(teacher!=0){
			ctrPage=1; curPageT=1;
			get_papers_fun(curPageT);
		}else{
			ctrPage=1; curPageT=1;
		} */
	});
	$("#papertype").change(function(){
		$("#paperlist tr:not(:first)").remove();
		var course=$("#course").val();
		var teacher = $("#teacher").val();
		var papertype = $(this).val();
		console.log(papertype);
		ctrPage=1; curPageT=1;
		get_papers_fun(curPageT);
		/* if(papertype != 0){
			ctrPage=1; curPageT=1;
			get_papers_fun(curPageT);
		}else{
			ctrPage=1; curPageT=1;
		} */
	});
	
	
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
		<form role="form">
			<div class="form-group row col-md-4">
				<label for="name">选择类型</label> 
				<select class="form-control"
					id="papertype">
					<option value="0">未选择</option>
				</select>
			</div>
			<div class="form-group row col-md-1"></div>
			<div class="form-group row col-md-4">
				<label for="name">选择拥有者</label> <select class="form-control"
					id="teacher">
					<option value="0">所有</option>
				</select>
			</div>
			<div class="form-group row col-md-1"></div>
			<div class="form-group row col-md-2">
				<div>
					<br>
				</div>
			</div>
		</form>
	</div>
	
	<div class="container">
		<form role="form">
			<div class="form-group row col-md-4">
				<label for="name">选择课程</label> 
				<select class="form-control"
					id="course">
					<option value="0">未选择</option>
				</select>
			</div>
			<div class="form-group row col-md-1"></div>
			<div class="form-group row col-md-4">
				
			</div>
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
			<table class="table table-striped" id="paperlist">
				<thead>
					<tr>
						<th>试卷名称</th>
						<th>试卷类型</th>
						<th>用户id</th>
						<th>选择题分数（百分比）</th>
						<th>编程题分数（百分比）</th>
						<th>课程id</th>
						<th>操作</th>
					</tr>
				</thead>
				<colgroup>
					<col style="width: 10%">
					<col style="width: 10%">
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 20%">
					<col style="width: 10%">
					<col style="width: 10%">
				</colgroup>
				<tbody id="questlisttbody">
				</tbody>
			</table>

		</div>
	</div>
	<div class="container">
		<ul class="pagination" id="page">
		
		</ul>
	</div>

	</div>
	
</body>
<script type="text/javascript">
//类型下拉框改变事件
$("#course").change(function(){
	
	//调用getPaperList方法
	
 ctrPage=1;//翻页按钮的页面
		 curPageT=1;
	get_papers_fun(curPageT);
});
//papertype
$("#papertype").change(function(){
	
	//调用getPaperList方法
	
     ctrPage=1;//翻页按钮的页面
		 curPageT=1;
	get_papers_fun(curPageT);
});
//teacher
$("#teacher").change(function(){
	
	//调用getPaperList方法
	
     ctrPage=1;//翻页按钮的页面
		 curPageT=1;
	get_papers_fun(curPageT);
});
$("#collapseListGroupHeading2").click();

$(document).on("click","#page li", function(){
	 
	 var pagectro= $(this).text();
	  
	 
	//if( $(this).text()=='next')alert( $(this).text());
	  if(pagectro=='previous'){
		  
		  ctrPage--;
		  if(ctrPage==0)ctrPage=1;
		  else{
			  $("#page li").remove();
				 $("#page").append( "<li><a href='#'>previous</a></li>");
				 for(var i=0;(i+1+(ctrPage-1)*ctrpagenum)<=totalPagest&&i<ctrpagenum;i++){
						$("#page").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");
						 
				  }		 
				 $("#page").append( "<li><a href='#'>next</a></li>");
				 
		  }
	  }
	  else if(pagectro=='next'){
		  ctrPage++;
		  
		  if(ctrPage>Math.ceil(totalPagest/ctrpagenum)){ctrPage--;
		  
		  }
		  else{
		  $("#page li").remove();
		 $("#page").append( "<li><a href='#'>previous</a></li>");
		 for(var i=0; (i+1+(ctrPage-1)*ctrpagenum)<=totalPagest&&i<ctrpagenum;i++){
				$("#page").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");	 
		  }		 
		 $("#page").append( "<li><a href='#'>next</a></li>");
		  }
		 }
	  else  {
		 // ctrPage
		  curPageT=pagectro;
		  get_papers_fun( pagectro);}
	  return false;
}) ; 



</script>
</html>