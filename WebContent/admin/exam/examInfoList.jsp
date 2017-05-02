<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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

<script
	src="<%=basePath %>bootstrap/bootstrap-datetimepicker/js/moment.js"></script>
<script src="angular/angular.min.js"></script>
</head>
<body>


	<div class="row col-md-1"></div>
	<div class="row col-xs-2  col-md-1">
		<div></div>
		<jsp:include page="../frame/AdminLeft.jsp"></jsp:include>

	</div>
	<div class="row col-xs-4 col-md-4">
		<div class="container">
			<br> <br>
			<div ng-app="myApp" ng-controller="myCtrl">
				<div class="container">
				<table class="table table-striped" id="questionlist">
				<thead>
					<tr>
						<th>考试编号</th>
						<th>考试名称</th>
						<th>班级</th>
						
					</tr>
				</thead>
				<colgroup>
					<col style="width: 10%">
					<col style="width: 20%">
					<col style="width: 70%">
					
				</colgroup>
				<tbody >
				<tr ng-repeat="x in examInfoList">
    				<td>{{ x.examInfoId }}</td>
    				<td>{{ x.examName }}</td>
    				<td>添加班级</td>
  				</tr>
				</tbody>
			</table>
				</div>
			</div>
		</div>

		<br>

	</div>



</body>
<script type="text/javascript">
	$("#collapseListGroupHeading4").click();
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope, $http) {
		$http({//先获取examInfo数据刷在界面上
			method: 'get', 
			url: 'ExamInfoServlet?action=getExamAllInfos',
			params :{ }
			  

			}).then(function(response){
				//alert("ee");
				 
			},function(error){
				//alert("ee");
			});
	});
	
</script>


</html>