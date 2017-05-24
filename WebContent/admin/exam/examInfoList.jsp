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
						<th>开始时间</th>
						<th>结束时间</th>
						<th>考生</th>
						<th>删除</th>
						
					</tr>
				</thead>
				<colgroup>
					<col style="width: 10%">
					<col style="width: 20%">
					<col style="width: 25%">
					<col style="width: 25%">
					<col style="width: 10%">
					<col style="width: 10%">
				</colgroup>
				<tbody >
				<tr ng-repeat="x in examInfoList">
				<td ><a  href='javascript:;'   ng-click="updateExamInfo('./admin/exam/examInfoNew.jsp',x.examInfoId)" >{{x.examInfoId }}</a></td>
    			<td ><a  href='javascript:;'   ng-click="updateExamInfo('./admin/exam/examInfoNew.jsp',x.examInfoId)" >{{ x.examName }}</a></td>	
    				 
    				<td>{{ x.startTime }}</td>
    				<td>{{ x.endTime }}</td>
    				<td ><a  href='javascript:;'   ng-click="adminStudentForExamInfo('./admin/exam/examStudent.jsp',x.examInfoId,x.examName)" >考生</a></td>
    				<td ><a  href='javascript:;'   ng-click="deleteExamInfo(x.examInfoId)" >删除</a></td>
  				</tr>
				</tbody>
			</table>
					<nav>
						<ul class="pagination">
							<li><a ng-click="Previous()" class="btn btn-default  "> <span>上一页</span>
							</a></li>
							<li ng-repeat="page in pageList"
								ng-class="{active: isActivePage(page)}"><a
								ng-click="selectPage(page)"  class="btn btn-default  ">{{ page }}</a></li>
							<li><a ng-click="Next()"  class="btn btn-default  "> <span>下一页</span>
							</a></li>
						</ul>
					</nav>

				</div>
			</div>
		</div>

		<br>

	</div>



</body>
<script type="text/javascript">
 
var curPage=0;
var pageSize=12;

	$("#collapseListGroupHeading4").click();
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope, $http,$location) {
		
		
		$scope.updateExamInfo= function(path,value){
		//	alert(value);
			// alert( $($event.target).text());
			//$($event.target).text()获得id值
			path=path+"?examInfoId="+ value;
			 window.location=path;
			
		};
		$scope.Previous= function(){
			
			curPage=curPage-1;
			if(curPage==0){curPage=1;}
			else{
				 
				$scope.selectPage(curPage);
				
			}
				
			};
			$scope.Next= function(){
				
				curPage=curPage+1;
				if(curPage==$scope.totalPages+1){curPage--;}
				else{
					 
					$scope.selectPage(curPage);//调用下面的selectPage函数
					
				}
					
				};
				
			$scope.selectPage = function(cp){
				//if(curPage==cp)return;//如果没有更改页面，不跳转
			//	else curPage=cp;
				curPage=cp;
				$http({//先获取examInfo数据刷在界面上
					method: 'get', 
					url: 'ExamInfoServlet?action=getExamAllInfos',
					params :{curPage:curPage,pageSize:pageSize }
					  

					}).then(function(response){
						//alert("ee");
						$scope.examInfoList = response.data.list;
						$scope.pageList = response.data.pageList;
						$scope.totalPages=response.data.totalPages;
					},function(error){
						//alert("ee");
					});
			};
			
			$scope.deleteExamInfo=function(examInfoId){///删除
				$http({//先获取examInfo数据刷在界面上
					method: 'get', 
					url: 'ExamInfoServlet?action=deleteExamInfoById',
					params :{examInfoId:examInfoId}
					  

					}).then(function(response){
						$scope.selectPage(curPage);
					 
					},function(error){
						//alert("ee");
					});
			};
			$scope.selectPage(1);//页面加载直接调用，显示第一页
			$scope.adminStudentForExamInfo=function(path,examInfoId,examName ){
				//alert(examName);
				path=path+"?examInfoId="+ examInfoId+"&examName="+examName;
				 window.location=path;
			};
	});
	
	 
	  
</script>


</html>