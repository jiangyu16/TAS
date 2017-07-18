<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String examInfoId= request.getParameter("examInfoId")  ;
String examName= request.getParameter("examName")  ;
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
<div class="row"><br> </div>
<div class="row"> 
	<div class=" col-md-1"></div>
	<div class="  col-md-2">
		<div></div>
		<jsp:include page="../frame/AdminLeft.jsp"></jsp:include>

	</div>

	<div class=" col-md-9">
		<div class=row><h2 class="text-center"> 考试编号:<%=examInfoId %>  &nbsp; 考试名称:<%=examName %> </h2></div>
		<div ng-app="examStudentApp" ng-controller="examStudentCtrl">
        <div class="row">
				<div class="container">
					<table class="table">
						<caption>考生列表</caption>
						<thead>
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>班级</th>
								<th><input type="checkbox" ng-model="selectAll" ng-change="changeAll()" /> 
								选择
								</th>

							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="x in examStudentList">
								<td>{{x.studentId}}</td>
								<td>{{x.studentName}}</td>
								<td>{{x.className}}</td>
								<td><input type="checkbox"  ng-model="x.checked" ng-click="changeCurrent(x, $event)" /></td>
							</tr>
						</tbody>


					</table>
					<div class="text-center">已选数量：{{count}} <a href='javascript:;' class="btn btn-default" ng-click="deleteExamStudent()">删除考生</a></div>
					<nav>
						<ul class="pagination">
							<li><a ng-click="examStudentListPrevious()"
								class="btn btn-default  "> <span>上一页</span>
							</a></li>
							<li ng-repeat="page in examStudentPageList"
								ng-class="{active: isActivePage(page)}"><a
								ng-click="selectExamStudentPage(page)" class="btn btn-default  ">{{
									page }}</a></li>
							<li><a ng-click="examStudentListNext()"
								class="btn btn-default  "> <span>下一页</span>
							</a></li>
							<li><span>总共 {{estotalPages}} 页</span></li>
							
						</ul>
					</nav>

				</div>
			</div>


			<div class="clearfix visible-xs"></div>


<div class="row">


	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseOne">
					点击选择班级添加考生
				</a>
			</h4>
		</div>
   <div id="collapseOne" class="panel-collapse collapse ">
			<div class="panel-body">
			
			
			
				<div class="container">
					<table class="table">
						
						<thead>
							<tr>
								<th>班级编号</th>
								<th>班级名称</th>
								<th>挑选学生</th>
								<th>加入所有学生</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="x in classList">
								<td><a href='javascript:;'
									ng-click="updateExamInfo('./admin/exam/examInfoNew.jsp',x.classId )">{{x.classId
										}}</a></td>
								<td><a href='javascript:;'
									ng-click="updateExamInfo('./admin/exam/examInfoNew.jsp',x.classId )">{{
										x.className }}</a></td>


								<td><a href='javascript:;' ng-click="">添加考生</a></td>
								<td><a href='javascript:;'
									ng-click="addAllStudentsToExam(x.classId)">添加所有考生</a></td>
							</tr>
						</tbody>
					</table>
					<nav>
						<ul class="pagination">
							<li><a ng-click="classListPrevious()"
								class="btn btn-default  "> <span>上一页</span>
							</a></li>
							<li ng-repeat="page in classPageList"
								ng-class="{active: isActivePage(page)}"><a
								ng-click="selectClassPage(page)" class="btn btn-default  ">{{
									page }}</a></li>
							<li><a ng-click="classListNext()" class="btn btn-default  ">
									<span>下一页</span>
							</a></li>
						</ul>
					</nav>

				</div>
				</div></div>
			
			</div>
			
			
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
 
var classcurPage=0;
var classpageSize=2;
var examStudentcurPage=0;
var examStudentpageSize=12;

	$("#collapseListGroupHeading4").click();

	var examStudentApp = angular.module("examStudentApp", []);

	examStudentApp.controller("examStudentCtrl", function($scope, $http,$location) {
		//alert("ewwe");
		$scope.examInfoId=<%=examInfoId%>;
		//alert($scope.examInfoId);
		$scope.selectClassPage = function(cp){
			//if(curPage==cp)return;//如果没有更改页面，不跳转
		//	else curPage=cp;
			classcurPage=cp;
			$http({//先获取examInfo数据刷在界面上
				method: 'get', 
				url: 'ClassServlet?action=get_classes',
				params :{curPage:classcurPage,pageSize:classpageSize }
				  

				}).then(function(response){
					//alert("ee");
					$scope.classList = response.data.list;
					$scope.classPageList = response.data.pageList;
					$scope.totalPages=response.data.totalPages;
				},function(error){
					//alert("ee");
				});
		};
		
		$scope.selectExamStudentPage = function(cp){
			//if(curPage==cp)return;//如果没有更改页面，不跳转
		//	else curPage=cp;
			examStudentcurPage=cp;
			$http({//先获取examInfo数据刷在界面上
				method: 'get', 
				url: 'ExamStudentServlet?action=getAllExamStudents',
				params :{curPage:examStudentcurPage,pageSize:examStudentpageSize ,examInfoId:$scope.examInfoId}
				  

				}).then(function(response){
					//alert("ee");
					$scope.examStudentList = response.data.list;
					$scope.examStudentPageList = response.data.pageList;
					$scope.estotalPages=response.data.totalPages;
				},function(error){
					//alert("ee");
				});
		};
		
		$scope.examStudentListPrevious= function(){
			
			examStudentcurPage=examStudentcurPage-1;
			if(examStudentcurPage==0){examStudentcurPage=1;}
			else{
				 
				$scope.selectExamStudentPage(examStudentcurPage);
				
			}
				
			};
			$scope.examStudentListNext= function(){
				
				examStudentcurPage=examStudentcurPage+1;
				if(examStudentcurPage==$scope.estotalPages+1){examStudentcurPage--;}
				else{
					 
					$scope.selectExamStudentPage(examStudentcurPage);//调用下面的selectPage函数
					
				}
					
				};
		
		$scope.selectClassPage(1);
		$scope.selectExamStudentPage(1);
		$scope.addAllStudentsToExam=function(classId){
			 
			$http({//先获取examInfo数据刷在界面上
				method: 'get', 
				url: 'ExamStudentServlet?action=addAllStudentsToExam',
				params :{classId:classId,examInfoId:$scope.examInfoId }
				  

				}).then(function(response){
					//alert("ee");
					$scope.selectExamStudentPage(1);
				},function(error){
					//alert("ee");
				});
		};
		//复选框操作代码-------------------------------------------------------------------------
		//function(scope) {
		  $scope.count = 0;//已选择数量       
		  $scope.selectData = [];//已选对象      //选择单个（取消选择单个      
		  $scope.changeCurrent = function(current, $event) {       //计算已选数量 true加， false减       
			  $scope.count += current.checked ? 1 : -1;        //判断是否全选，选数量等于数据长度为true    
			  $scope.selectAll = $scope.count === $scope.examStudentList.length;        //统计已选对象        
			  $scope.selectData = [];        
			  angular.forEach($scope.examStudentList, function(item) {          
				  if(item.checked){           
					  $scope.selectData[$scope.selectData.length] = item;          
					  }        
				  });      
			//  console.log($scope.selectData);
			  $event.stopPropagation();//阻止冒泡      
			  };      
			  
				  
				  //全选（取消全选    
				  $scope.changeAll = function() {       
					  //console.log($scope.selectAll);   
					  angular.forEach($scope.examStudentList, function(item) {  
						  
						  item.checked = $scope.selectAll;  
						 //console.log(item);   
						  });        
					  $scope.count = $scope.selectAll ? $scope.examStudentList.length : 0;       
					  if ($scope.selectAll) {  
						  $scope.selectData = $scope.examStudentList;   
						  } else {   
							  $scope.selectData = []; 
							  } 
					//  console.log($scope.selectData);
					  };   
			$scope.deleteExamStudent=function(){
			//	console.log($scope.selectData);
				$http({//先获取examInfo数据刷在界面上
					  
					method: 'get', 
					url: 'ExamStudentServlet?action=deleteExamStudent',
							//将数组写成json
					params :{examStudents: angular.toJson($scope.selectData) ,examInfoId:$scope.examInfoId }
					  

					}).then(function(response){
						console.log(response);
						alert("成功删除"+response.data+"个学生");
						//alert("ee");
						$scope.selectExamStudentPage(1);
						 $scope.count=0;
					},function(error){
						//alert("ee");
					});
			};
					
		 // }
		//};
		
	});

	 

	  
</script>


</html>