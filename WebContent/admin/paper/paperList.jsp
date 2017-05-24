<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" > 
<meta charset="UTF-8">
<title>试卷列表</title>
</head>
<body>
	<div class="container">
		<form role="form">

			<div class="form-group row col-md-2">
				<label for="name">选择课程</label> 
				<select class="form-control"
					id="course">
					<option value="0">未选择</option>
				</select>
			</div>

			<div class="form-group row col-md-1"></div>
			<div class="form-group row col-md-2">
				<label for="name">类型</label> <select class="form-control"
					id="paperType">
					<option value="0">所有</option>
					<option value="1">试卷</option>
					<option value="2">实验</option>
					<option value="2">练习</option>
				</select>
			</div>
			
			<div class="form-group row col-md-3"></div>	
			<div class="form-group row col-md-5">
				<label for="name">用户ID</label>
				<div class="form-inline">  
					<input type="text" class="form-control" id="teacherId" placeholder="请输入用户Id">
					<button class="btn btn-primary" id="queryPaper">查询</button>
				</div>
			</div>	
		
		</form>
	</div>
	
	<!-- 列表主体 -->
	<div class="container">
		<div class="row col-md-10">
			<table class="table table-striped" id="questionlist">
				<thead>
					<tr>
						<th>paper名称</th>
						<th>paper类型</th>
						<th>用户ID</th>
						<th>选择题分数</th>
						<th>编程题分数</th>
						<th>课程ID</th>
						<th>查看编程题</th>
					</tr>
				</thead>
				<colgroup>
					<col style="width: 14%">
					<col style="width: 14%">
					<col style="width: 14%">
					<col style="width: 14%">
					<col style="width: 14%">
					<col style="width: 18%">
				</colgroup>
				<tbody id="paperListTbody">
					<!-- 测试样式使用的数据 -->
					<tr>
						<td>paper1</td>
						<td>试卷</td>
						<td>123</td>
						<td>23</td>
						<td>40</td>
						<td>12</td>
						<td><button class="btn btn-primary">编程题</button></td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>

</body>

<script type="text/javascript">
//分页所需数据
var ctrPage=1;//翻页按钮的页面
var ctrpagenum=5;//翻页按钮每页有几个按钮
var totalPagest;//总共有多少页
var pageSizet=30;//每页多少行
var curPageT=1;//当前第几页
var dataList;
var totalRows;//总共多少行

//分页所需js

//页面初始化时下拉框加载课程
jQuery(document).ready(function() {
	//课程下拉框添加选项
	var url="CourseServlet?action=get_course";
	var args={"time":new Date()}; 
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
});

//获取paperList
var getPaperList = function(){
	var courseId = $("#course").val();//课程
	var teacherId = $("#teacherId").val()//用户ID
	var paperType =  $("#paperType").val()//类型
	
	var url = "PaperServlet?action=get_paperList";
	//ajax参数
	var args={"courseId":courseId,"teacherId":teacherId,"paperType":paperType
				,"curPage":curPageT,"pageSize":pageSizet}; 
	//后端返回一个PageControl对象
	$.getJSON(url,args,function(data){
		if(data.paperList.length==0){
			alert("当前系统里没有试卷");
		}else{
			//获取后台传递的PageControl对象,赋给dataList
			dataList=data.list;
			for(var i=0;i<dataList.length;i++){
				//对返回的int类型进行处理
				var type;
				if(dataList[i].paperType==1){
					type="试卷";	
				}else if(paperList[i].paperType==2){
					type="实验";	
				}else if(dataList[i].paperType==3){
					type="练习";	
				}
				var html ="<tr><td>"+dataList[i].paperName+"</td><td>"+type+"</td>";
				html+="<td>"+dataList[i].teacherId+"</td><td>"+dataList[i].choiceScore+"</td><td>"+dataList[i].programScore+"</td><td>"+dataList[i].courseId+"</td>";
				//在编程题按钮中添加了value值为paperId
				html+="<td><button class='btn btn-primary' value='"+dataList[i].paperId+"'>编程题</button></td></tr>";
				$("#course").html(html);
			}
			
		}
	});
};

//类型下拉框改变事件
$("#course").change(function(){
	//调用getPaperList方法
	getPaperList();
});
//课程下拉框改变事件
$("#paperType").change(function(){
	//调用getPaperList方法
	getPaperList();
});
//查询按钮点击事件
$("#queryPaper").click(function(){
	//调用getPaperList方法
	getPaperList();
});
</script>
</html>