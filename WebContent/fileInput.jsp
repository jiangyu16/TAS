<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New Document</title>
<meta name="Generator" content="EditPlus">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
<link href="bootstrap/css/blog-home.css" rel="stylesheet">
<link href="bootstrap/bootstrap-fileinput-master/css/fileinput.min.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>bootstrap/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<!-- 文件上传插件需要导入的文件 -->
<script
	src="<%=basePath%>bootstrap/bootstrap-fileinput-master/js/fileinput.min.js"></script>
<script
	src="<%=basePath%>bootstrap/bootstrap-fileinput-master/js/fileinput_locale_zh.js"></script>
</head>

<body>
	<!-- 按钮触发模态框 -->
	<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Excel导入</button>
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
			</form>
</body>
</html>

<script>
	$(function() {
		//0.初始化fileinput
		var oFileInput = new FileInput();
		oFileInput.Init("fileinput", "FileInputServlet?action=fileinput");
	});

	//初始化fileinput
	var FileInput = function() {
		var oFile = new Object();
		var a = "3";
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
						$("#myModal").modal("hide");
						var data = data.response.lstOrderImport;
						if (data == undefined) {
							toastr.error('文件格式类型不正确');
							return;
						}
						//1.初始化表格
						var oTable = new TableInit();
						oTable.Init(data);
						$("#div_startimport").show();
					});
		}
		return oFile;
	};
</script>
