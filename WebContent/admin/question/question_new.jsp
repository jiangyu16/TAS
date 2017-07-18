<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
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
<title>新增题目</title>
	
</head>
 
<script type="text/javascript">


var courselist;
 
jQuery(document).ready(function() {
	
	var url="CourseServlet?action=get_course";
	 var args={"time":new Date()}; 
	$.getJSON(url, function(data){
		if(data.length==0){
			alert("当前系统里没有课程");
		}else{
			courselist=data;
			for(var i=0;i<data.length;i++){
				var courseId=data[i].courseId;
				var courseName=data[i].courseName;
				$("#course").append("<option value='"+courseId+"'>"+courseName+"</option>");
			}
			
		}
	});
	
	$("#course").change(function(){
		 
		$("#chapter option:not(:first)").remove();
		 
		var course=$(this).val();
		if(course!=""){
			var url="CourseServlet?action=get_chapter";
			var args={"courseId":course,"time":new Date()};
			$.getJSON(url,args,function(data){
				if(data.length==0){
					alert("当前课程没有章节");
					 $("#questionlist tr:not(:first)").remove();
				}else{
					for(var i=0;i<data.length;i++){
						var chapterId=data[i].chapterId;
						var chapterName=data[i].chapterName;
						$("#chapter").append("<option value='"+chapterId+"'>"+chapterName+"</option>");
					}
					 
				}
			});
		}
	});
	 
	
});

function save_program(){
	 var url ="QuestionServlet?action=save_program";
	 //alert($("#course").val());
	 var args={"courseId":$("#course").val() ,
			 "chapterId": $("#chapter").val(),
			 "programsTitle":$("#programsNameText").val() ,
		 "programsSource":$("#programsSourceText").val() ,
		 "spendTime":$("#spendTime_text").val() ,
		 "programsContext":$("#programsContextText").val() ,
		 "languageId":$("#languageId").val(),
		 "answer":$("#programsAnswerText").val(),
		 "time":new Date()}
	 
	 $.post(url,args,function(data){
		//alert(data);	
		 if(data==-1)return false;
			
			else $("#testdatabtn").attr("value",data);
		 
		});
	$("#testdatabtn").removeAttr("disabled");
	return false;
}
</script>
<body>
<br> <br>
	<div class="row col-md-1"></div>
	<div class="row col-xs-2  col-md-1">
		<div></div>
		<jsp:include page="../frame/AdminLeft.jsp"></jsp:include>

	</div>
	<div class="row col-md-1"></div>
	<div class="row col-xs-4 col-md-4">
 
<div class ="container">
<form name="formlwn" id="programForm" role="form"  style="width:80%" action="QuestionServlet?action=save_program" method="post" onsubmit="return false;">
    <div class="input-group " >
            <span class="input-group-addon"  >选择课程</span>
            <select  class="form-control" id="course" name="courseId" >
		<option value="0">未选择</option>
		</select>
		<span class="input-group-addon">选择章节</span>
           <select  class="form-control" id="chapter" name= "chapterId">
			<option value="0">未选择</option>
		</select>
     </div>
		<div class="input-group  " >
            <span class="input-group-addon">题目名称</span>           
           <input type="text" class="form-control" id="programsNameText" name ="programsTitle">
            <span class="input-group-addon">语言</span>           
            <select  class="form-control" id="languageId" name= "language">
			<option value="1">C++</option>
			<option value="2">JAVA</option>
		   </select>
     </div>
     <br>
			<div class=" form-group">
				<textarea class="form-control" style="margin: 0px auto; width: 90%"
					rows="13" placeholder="题目描述" id="programsContextText"
					name="programsContext"></textarea>
			</div>
			<div class=" form-group">

				<textarea class="form-control" style="margin: 0px auto; width: 90%"
					rows="7" placeholder="填空源代码" id="programsSourceText"
					name="programsSource"></textarea>
			</div>
			<div class=" form-group">

				<textarea class="form-control" style="margin: 0px auto; width: 90%"
					rows="7" placeholder="参考答案" id="programsAnswerText"
					name="programsAnswer"></textarea>
			</div>
			<div  style=" text-align:center" >        
           <button type="button" id="testdatabtn" class="btn btn-default" style="text-align:right;" disabled value="-1">添加或查看测试数据</button>
             <label for="name"  >&nbsp;时间限制&nbsp;</label>
			<input type="text" name ="spendTime" id="spendTime_text" value="3000"   >&nbsp;毫秒
			   <button type="button" class="btn btn-default" onclick="save_program()">保存</button>
         </div>              
 </form>
</div>
 <div class="container">
 
 
	
	 
   
</div> </div> 
</body>
<script type="text/javascript">
$("#collapseListGroupHeading1").click();
</script>
</html>


