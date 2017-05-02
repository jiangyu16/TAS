<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String examInfoId= request.getParameter("examInfoId")  ;
%>

<!DOCTYPE HTML>
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

<script	src="<%=basePath %>bootstrap/bootstrap-datetimepicker/js/moment.js"></script>
<script src="angular/angular.min.js"></script>
</head>
<body>

<%=basePath%>
 <div class="row col-md-1" ></div>
<div class="row col-xs-2  col-md-1">
<div></div>
 <jsp:include page="../frame/AdminLeft.jsp"></jsp:include>
            
 </div>
 <div class="row col-xs-4 col-md-4">
 <div class="container" >
 <br> <br>
 <div ng-app="myApp" ng-controller="siteCtrl"> 
 <div class="container">
		<form class="bs-example bs-example-form" role="form" action="ExamInfoServlet?action=saveExamInfo" method="post">
			<div class="input-group">
				<span class="input-group-addon">paper编号</span>
				 <input type="text"
					class="form-control" ng-model="examInfoId" name="examInfoId">
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">考试名称</span>
				 <input type="text"
					class="form-control" ng-model="examName" name="examName">
			</div>
				<!--指定 date标记-->
				<div class='input-group date' id='datetimepicker1'>
				<span class="input-group-addon">开始时间</span>
					<input type='text' class="form-control" ng-model="startTime" name="startTime"/> <span
						class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			 
				<!--指定 date标记-->
				<div class='input-group date' id='datetimepicker2'>
				<span class="input-group-addon">结束时间</span>
					<input type='text' class="form-control" ng-model="endTime" name="endTime"/> <span
						class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			 <div class="input-group">
				<span class="input-group-addon" >编程题总分</span>
				 <input type="text"
					class="form-control"  ng-model="programScore" name ="programScore">
			</div>
			<!-- 
			 <div class="input-group">
				<span class="input-group-addon">选择题总分</span>
				 <input type="text"
					class="form-control"   ng-model="choiceScore">
			</div>
			
			 -->
			 <button type="submit" class="btn btn-default" >保存</button>

		</form>
	 
 </div>
 </div>
 </div>
 
<br>
 
 </div>


	
</body>
<script type="text/javascript">
var examInfoId=<%=examInfoId%>;
examInfoId=8;
var app = angular.module('myApp', []);
app.controller('siteCtrl', function($scope, $http) {
	if(examInfoId!=null){	//修改考试	 ,先获取数据
		//alert(examInfoId);
		$http({//先获取examInfo数据刷在界面上
			method: 'get', 
			url: 'ExamInfoServlet?action=getExamInfoById',
			params :{ examInfoId:examInfoId}
			  

			}).then(function(response){
				//alert("ee");
			
				$scope.examInfoId = response.data.examInfoId;
				$scope.examName=response.data.examName;
				$scope.paperId =response.data.paperId;
				$scope.startTime =response.data.startTime;
				$scope.endTime =response.data.endTime;
				$scope.programScore =response.data.programScore;
				$scope.choiceScore =response.data.choiceScore;
			},function(error){
				//alert("ee");
			});
		// alert("ee");
 
		
	}
	
	
  
});


$("#collapseListGroupHeading4").click();
	$(function() {
		$('#datetimepicker1').datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd hh:ii',
			setDate : new Date(),
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep : 5,
			forceParse : 0
		//  locale: moment.locale('zh-cn')  
		});
		$('#datetimepicker2').datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd hh:ii',
			setDate : new Date(),
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep : 5,
			forceParse : 0
		//  locale: moment.locale('zh-cn')  
		});
	});
	
</script>


</html>