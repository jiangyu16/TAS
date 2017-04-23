<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<base href="<%=basePath%>">
<head>

<!-- Bootstrap3.3.5 CSS -->



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
            <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style>
.panel-group {
	max-height: 770px;
	overflow: auto;
}

.leftMenu {
	margin: 10px;
	margin-top: 5px;
}

.leftMenu .panel-heading {
	font-size: 14px;
	padding-left: 20px;
	height: 36px;
	line-height: 36px;
	color: white;
	position: relative;
	cursor: pointer;
} /*转成手形图标*/
.leftMenu .panel-heading span {
	position: absolute;
	right: 10px;
	top: 12px;
}

.leftMenu .menu-item-left {
	padding: 2px;
	background: transparent;
	border: 1px solid transparent;
	border-radius: 6px;
}

.leftMenu .menu-item-left:hover {
	background: #C4E3F3;
	border: 1px solid #1E90FF;
}
</style>

 
     
</head>

<body>
	<div class="row">

		<div class="panel-group table-responsive" role="tablist">
			<div class="panel panel-primary leftMenu">
				<!-- 利用data-target指定要折叠的分组列表 -->
				<div class="panel-heading" id="collapseListGroupHeading1"
					data-toggle="collapse" data-target="#collapseListGroup1" role="tab">
					<h4 class="panel-title">
						题目管理 <span class="glyphicon glyphicon-chevron-up right"></span>
					</h4>
				</div>
				<!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
				<div id="collapseListGroup1" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="collapseListGroupHeading1">
					<ul class="list-group">
						<li class="list-group-item">
							<!-- 利用data-target指定URL -->
						<li class="list-group-item">
							<button class="menu-item-left" onclick="new_question_page()">
								<span class="glyphicon glyphicon-triangle-right"></span>新增题目
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left" onclick="get_question_page()">
								<span class="glyphicon glyphicon-triangle-right"></span>查询题目
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left" onclick="admin_course_page()">
								<span class="glyphicon glyphicon-triangle-right"></span>课程管理
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-4
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-5
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-6
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-7
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-8
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-9
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-10
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项1-11
							</button>
						</li>
					</ul>
				</div>
			</div>
			<!--panel end-->
			<div class="panel panel-primary leftMenu">
				<div class="panel-heading" id="collapseListGroupHeading2"
					data-toggle="collapse" data-target="#collapseListGroup2" role="tab">
					<h4 class="panel-title">
						学生管理 <span class="glyphicon glyphicon-chevron-down right"></span>
					</h4>
				</div>
				<div id="collapseListGroup2" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="collapseListGroupHeading2">
					<ul class="list-group">
						<li class="list-group-item">
							<button class="menu-item-left"  onclick="admin_class_page()">
								<span class="glyphicon glyphicon-triangle-right" ></span>班级管理
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项2-2
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项2-3
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项2-4
							</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="panel panel-primary leftMenu">
				<div class="panel-heading" id="collapseListGroupHeading2"
					data-toggle="collapse" data-target="#collapseListGroup3" role="tab">
					<h4 class="panel-title">
						fz<span class="glyphicon glyphicon-chevron-down right"></span>
					</h4>
				</div>
				<div id="collapseListGroup3" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="collapseListGroupHeading2">
					<ul class="list-group">
						<li class="list-group-item">
							<button class="menu-item-left" onclick="get_students_page()">
								<span class="glyphicon glyphicon-triangle-right"></span>查询学生
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项3-2
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项3-3
							</button>
						</li>
						<li class="list-group-item">
							<button class="menu-item-left">
								<span class="glyphicon glyphicon-triangle-right"></span>分组项3-4
							</button>
						</li>
					</ul>
				</div>
			</div>
		</div>


	</div>

	<script>
		$(function() {

			$(".panel-heading").click(function(e) {
				/*切换折叠指示图标*/
			 
				$(this).find("span").toggleClass("glyphicon-chevron-down");
				$(this).find("span").toggleClass("glyphicon-chevron-up");
			});
		});
		function get_question_page() {
			//alert("eeeee");
			$("#questionlist").load("./admin/question/question_list.jsp");
		}
		function new_question_page() {
			$("#questionlist").load("./admin/question/question_new.jsp");
		}
		function admin_course_page() {
			$("#questionlist").load("./admin/question/AdminCourse.jsp");
		}

		function get_students_page() {
			$("#questionlist").load("./admin/question/student_list_y.jsp");
		}
		function admin_class_page(){
			//alert("eeeee");
			$("#questionlist").load("./admin/student/AdminClass.jsp");
		}
	</script>
</body>

</html>