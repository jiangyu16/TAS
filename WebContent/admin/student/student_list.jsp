<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <%
 
 String classId= request.getParameter("classId")  ;
 String className= request.getParameter("className")  ;

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
    <link href="bootstrap/bootstrap-fileinput-master/css/fileinput.min.css"
	media="all" rel="stylesheet" type="text/css" />
	
    <script src="<%=basePath %>bootstrap/js/jquery-2.1.1.js"></script>
     <script src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
     
     <!-- 文件上传插件需要导入的文件 -->
<script
	src="<%=basePath%>bootstrap/bootstrap-fileinput-master/js/fileinput.min.js"></script>
<script
	src="<%=basePath%>bootstrap/bootstrap-fileinput-master/js/fileinput_locale_zh.js"></script>
</head>
<!--CourseServlet?action=get_course--> 
<script type="text/javascript">
 
var ctrPage=1;//翻页按钮的页面
var ctrpagenum=5;//翻页按钮每页有几个按钮
var totalPagest;//总共有多少页
var pageSizet=30;//每页多少行
var curPageT=1;//当前第几页
var dataList;
var totalRows;//总共多少行
 
 
$(document).on("click","#page_studentlist li", function(){
		 
	var pagectro= $(this).text();
	//alert(pagectro); 
	if(pagectro=='previous'){
		ctrPage--;
		if(ctrPage==0){
			ctrPage=1;
		}
		curPageT=(ctrPage-1)*ctrpagenum+1;
	}else if(pagectro=='pnext'){
		
		ctrPage++;
		if(ctrPage>Math.ceil(totalPagest/ctrpagenum)){
			ctrPage--;
		}
		curPageT=(ctrPage-1)*ctrpagenum+1;
		
	}else{
		curPageT=pagectro;
		
	}
	get_students_fun(curPageT);	
	return false;
}) ; 
 
function get_students_fun(curPage){
	
	 
	url="StudentServlet?action=get_students";
    $("#studentlist tr:not(:first)").remove();
    
   // var classId=$("#classId").val();
 var classId=<%=classId%>;
    var args={"classId":classId,
			 "curPage":curPage,
			 'pageSize':pageSizet,
			 "time":new Date()
    };
	  
    $.getJSON(url,args,function(data){	
    	totalPages=data.totalPages;
		totalPagest=totalPages;
		dataList = data.list;
		totalRows = data.totalRows;
		 $("#totalRows").text("总人数: "+totalRows);
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
		$("#page_studentlist li").remove();
		$("#page_studentlist").append( "<li><a href='#'>previous</a></li>");
		for(var i=0; (i+1+(ctrPage-1)*ctrpagenum)<=totalPagest&&i<ctrpagenum;i++){
			if(curPageT==(i+1+(ctrPage-1)*ctrpagenum)){
				//$("#page").append(" <li><a href='avaScript:return false;' style='opacity: 0.3;background-color:#000000'>"+(i+1)+"</a></li>");
				$("#page_studentlist").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");	
			}else{
				$("#page_studentlist").append(" <li><a href='#'>"+(i+1+(ctrPage-1)*ctrpagenum)+"</a></li>");	
			}	 
		}		 
		//$("#page").append( "<li><a href='#'>next</a></li>");
		$("#page_studentlist").append( "<li><a href='#'>pnext</a></li>");
	});
   
   
}

jQuery(document).ready(function() {

	get_students_fun(1);
	 
});


function delete_student(studentId){
	alert("删除学生");
	 
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



//文件上传
$(function() {
		//0.初始化fileinput
		var oFileInput = new FileInput();
		oFileInput.Init("fileinput", "FileInputServlet?action=fileinput");
	});

	//初始化fileinput
	var FileInput = function() {
		var oFile = new Object();
		var a =<%=classId%>;
		//初始化fileinput控件（第一次初始化）
		oFile.Init = function(ctrlName, uploadUrl) {
			var control = $('#' + ctrlName);
		 
			//初始化上传控件的样式
			control.fileinput({
				language : 'zh', //设置语言
				uploadUrl : uploadUrl, //上传的地址
				//allowedFileExtensions : [ 'xls', 'xlsx' ],//接收的文件后缀
				showUpload : true, //是否显示上传按钮
				showCaption : false,//是否显示标题
				browseClass : "btn btn-primary", //按钮样式 
				enctype : 'multipart/form-data',
				validateInitialCount : true,
				previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
				uploadExtraData : {
					classId : a
				}
			});

			//导入文件上传完成之后的事件
			$("#fileinput").on("fileuploaded",
					function(event, data, previewId, index) {
			//	alert("fwefw");
			//	alert("fwefw");
						//$("#myModal").modal("hide");
						get_students_fun(curPageT);	
						var data = data.response.lstOrderImport;
						if (data == undefined) {
							toastr.error('文件格式类型不正确');
							return;
						}
						$("#myModal").modal("hide");
						alert("fwefw");
						//get_students_fun(curPageT);	
						//1.初始化表格
						//var oTable = new TableInit();
						//oTable.Init(data);
						//$("#div_startimport").show();
					});
		}
		return oFile;
	};

</script>
<body>



	<div class="container">
		<form role="form" method="post" onsubmit="return false;">
		<div  class=" row col-md-2">班级:<%=className %></div>
	 
		<div  class=" row col-md-2" id="totalRows"></div>
	

			<div class="form-group row col-md-6">
			 
		<button class="btn btn-primary " data-toggle="modal" data-target="#myModal">导入学生名单</button>		 
				
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
		<ul class="pagination" id="page_studentlist">
		</ul>
	</div>
 
 <form>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">请选择Excel文件</h4>
							</div>
							<div class="modal-body">
								<input type="file" name="fileinput" id="fileinput"
									class="file-loading" />
							</div>
						</div>
						</div>
						</div>
						
			</form>
			
			
</body>
</html>