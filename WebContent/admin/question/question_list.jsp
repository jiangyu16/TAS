<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" > 
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<!--CourseServlet?action=get_course--> 
<script type="text/javascript">
 
var ctrPage=1;//翻页按钮的页面
var ctrpagenum=5;//翻页按钮每页有几个按钮
var totalPagest;//总共有多少页
var pageSizet=9;//每页多少行
 var curPageT=1;
 var dataList;
 
 
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
			  get_programs_fun( pagectro);}
		  return false;
	}) ; 
 
function get_programs_fun(  curPage){
	
	 
	url="QuestionServlet?action=get_programs";
    $("#questionlist tr:not(:first)").remove();
	var chapterId = $("#chapter").val();
	 var courseId = $("#course").val();
	 var programType = $("#programType").val();
 
	 var args={"courseId": courseId,
			 "chapterId":chapterId,
			 "curPage":curPage,
			 'pageSize':pageSizet,
			 'programType':programType,
			 "time":new Date()
	          };
	  
	 $.getJSON(url,args,function(data){				 
		 totalPages=data.totalPages;
		 totalPagest=totalPages;
		 dataList = data.list;
		// alert("eeeweee");
		// alert(totalPages);
		//+"<td> <button class='btn btn-default'  href='page.jsp' onclick='operation_program(this.value)' value='"+programProblemId+"'> 查看</button></td>" 
		$("#questlisttbody tr").remove();
		  for(var i=0;i<(data.list).length;i++){
			  var chapterName = (data.list)[i].chapterName;
				 var title =  (data.list)[i].title;
				 var programProblemId =  (data.list)[i].programProblemId;
				 var tr="<tr><td>"+programProblemId+"</td>"
				 +"<td>"+title+"</td>"
				 +"<td>"+chapterName+"</td>"
				 +"<td> <button class='btn btn-default' onclick='operation_program(this.value)' value='"+programProblemId+"'> 查看</button></td>"
				 +"<td> <button class='btn btn-default' onclick='edit_testdata(this.value)' value='"+programProblemId+"'> 测试数据</button></td>"
				 // +"<td><a href='./admin/question/AdminCourse.jsp' data-toggle='modal' data-target='#openEditProgramDialog'>打开对话框</a> </td>"
				+"<td> <button class='btn btn-default' onclick='delete_program(this.value)' value='"+programProblemId+"'> 删除</button></td><tr>"
				
				$("#questlisttbody").append(tr);
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
				$("#courseId").append("<option value='"+courseId+"'>"+courseName+"</option>");
			}
			
		}
	});
	
	$("#course").change(function(){
		 
		$("#chapter option:not(:first)").remove();
		 $("#questionlist tr:not(:first)").remove();
		var course=$(this).val();
		if(course!=0){
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
						$("#chapterId").append("<option value='"+chapterId+"'>"+chapterName+"</option>");
					}
					ctrPage=1; curPageT=1;
					get_programs_fun(curPageT);
				}
			});
		}else{
			ctrPage=1; curPageT=1;
		}
	});
	
$("#chapter").change( function(){
		
		ctrPage=1; get_programs_fun(curPageT);});
$("#programType").change( function(){
	
	ctrPage=1; get_programs_fun(curPageT);});

	
});




function delete_program(programProblemId  ){
	 
	var url="QuestionServlet?action=del_program";
	var args={ "programProblemId":programProblemId,"time":new Date()};
	$.post(url,args,function(data){
	 
		if(data==1)get_programs_fun(curPageT);
	});
}
function operation_program(programProblemId){
	var program;
	for(var i=0;i<dataList.length;i++){
		if(dataList[i].programProblemId==programProblemId)program=dataList[i];
	}
	/*$("#courseId option").remove();
	$("#courseId").append("<option value='"+program.courseId+"'>"+program.courseName+"</option>");
	$("#chapterId option").remove();
	$("#chapterId").append("<option value='"+program.chapterId+"'>"+program.chapterName+"</option>");
	*/ 
	$("#courseId").val(program.courseId);
	$("#chapterId").val(program.chapterId);
	$("#programsNameText").val(program.title);
	//$("#programsNameText").val("");
	$("#languageId").val(program.languageId);
	$("#programsContextText").val(program.text);
	$("#programsSourceText").val(program.scource);
	$("#spendTime_text").val(program.spendTime); 
	$("#programsAnswerText").val(program.answer);
	$("#saveProgramBtn").val(program.programProblemId);	//	$("#courseId").val(program.courseId);
	//editModalProgramType
	$("#editModalProgramType").val(program.programType);	
	//alert(program);
	//$("modal-body").append("hhhhh");
 //	$("modal-body").load("./admin/question/question_new.jsp");
	 $("#openEditProgramDialog").modal();
	
	 // $("#openEditProgramDialog").modal({
	 //     remote: "./admin/question/question_new.jsp?programProblemId="+program.programProblemId
	//  });
	// $("#update_chapterModal").modal();
	//window.open("./admin/question/question_new.jsp","","dialogWidth=1000px;dialogHeight=400px;status=yes;help=no;scrollbars=yes");
}
function save_program(programProblemId){
	 var url ="QuestionServlet?action=save_program";
	// alert(programProblemId);
	 var args={
			 "programProblemId":programProblemId,
			 "courseId":$("#courseId").val() ,
			 "chapterId": $("#chapterId").val(),
			 "programsTitle":$("#programsNameText").val() ,
		 "programsSource":$("#programsSourceText").val() ,
		 "spendTime":$("#spendTime_text").val() ,
		 "programsContext":$("#programsContextText").val() ,
		 "languageId":$("#languageId").val(),
		 "answer":$("#programsAnswerText").val(),
		 "programType":$("#editModalProgramType").val(), 
		 "time":new Date()}
	 
	 $.post(url,args,function(data){
	//	 alert(data);	
		 if(data!=1)return false;
			
			else  {
				$("#openEditProgramDialog").modal('hide');;
				get_programs_fun(curPageT);
			}
		 
		});
	$("#testdatabtn").removeAttr("disabled");
	return false;
}
function edit_testdata(programProblemId){
	get_testData(programProblemId);
	
	 $("#openTestDataDialog").modal();
	 $("#programProblemIdBtn").val(programProblemId)
}
function get_testData(programProblemId){
	//$("#number")
	   $("#number label:not(:first)").remove();
	   $("#inputcol label:not(:first)").remove();
	   $("#outputcol label:not(:first)").remove();
	   $("#detailcol button:not(:first)").remove();
	   $("#delcol button:not(:first)").remove();
	var url="TestDataServlet?action=get_testDataList";
	var args={"programProblemId":programProblemId,
	"time":new Date()
	}
	$.getJSON(url,args, function(data){
		if(data.length==0){
			 
		}else{
			for(var i=0;i<data.length;i++){
				var courseId=data[i].testdataId;
				var courseName=data[i].programId;
				var input=data[i].input;
				var output=data[i].output;
				var reason = data[i].reason;
				var percentage = data[i].percentage;
				$("#number").append("<label class='list-group-item'> "+(i+1)+"</label>");
				 $("#inputcol").append("<label class='list-group-item'>"+input+"</label>");
				 $("#outputcol").append("<label class='list-group-item'>"+output+"</label>");
				 $("#detailcol").append("<button  class='btn btn-default list-group-item onclick='''>查看</button>");;
				 $("#delcol").append("<button  class='btn btn-default list-group-item onclick='''>删除</button>");;

				//$("#course").append("<option value='"+courseId+"'>"+courseName+"</option>");
				//$("#courseId").append("<option value='"+courseId+"'>"+courseName+"</option>");
			}
			
		}
	});
	
//	$("#testDataList li").remove();
//	$("#testDataList").append("<li class='list-group-item'>免费域名注册</li>");
}

//以下两个方法想关闭模式对话框清楚数据。不行。
$("#openEditProgramDialog").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});
$("#openEditProgramDialog").on("hide.bs.modal", function() {
    $(this).removeData("bs.modal");
});
/*
$(function(){
	$("#page li:not(:first)").remove();
	$("#page").append(" <li><a href='#'>5</a></li>");
	$("#page").append( "<li><a href='#'>next</a></li>");
});*/
 
function clickTestDataIdBtn(){//保存测试数据
	// var url ="QuestionServlet?action=save_program";
	var url ="TestDataServlet?action=save_testData";
	//url ="QuestionServlet?action=save_program";
	// alert(programProblemId);programProblemId
	//alert($("#testDataIdBtn").val());
	var programProblemId=$("#programProblemIdBtn").val();
	var testDataInput=$("#testDataInputText").val();
	var testDataOutput= $("#testDataOutputText").val();
	var testDataId=$("#testDataIdBtn").val();
	var percent=$("#percentText").val();
	var reason=$("#testDataReasonText").val()
	 var args={
			 "programProblemId":$("#programProblemIdBtn").val(),
			 "testDataInput":$("#testDataInputText").val() ,
			 "testDataOutput": $("#testDataOutputText").val(),
			 "testDataId":$("#testDataIdBtn").val() ,
		 "percent":$("#percentText").val() ,
		 "reason":$("#testDataReasonText").val() ,
		  
		 "time":new Date()}
	 
	 $.post(url,args,function(data){
		// alert(data);	
		 if(data!=1)return false;
			
			else  {
				 $("#number").append("<label class='list-group-item'> "+data+"</label>");
				 $("#inputcol").append("<label class='list-group-item'>"+testDataInput+"</label>");
				 $("#outputcol").append("<label class='list-group-item'>"+testDataOutput+"</label>");
				 $("#detailcol").append("<button  class='btn btn-default list-group-item onclick='''>查看</button>");;
				 $("#delcol").append("<button  class='btn btn-default list-group-item onclick='''>删除</button>");;
			}
		 
		});
}
</script>
<body>

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
				<label for="name">选择章节名称</label> <select class="form-control"
					id="chapter">
					<option value="0">所有</option>
				</select>
			</div>
			<div class="form-group row col-md-1"></div>
	<div class="form-group row col-md-2">
				<label for="name">类型</label> <select class="form-control"
					id="programType">
					<option value="0">所有</option>
					<option value="1">考试</option>
					<option value="2">练习</option>
				</select>
			</div>
			
		
		</form>
	</div>

	<div class="container  ">
		<div class="row col-md-10">
			<table class="table table-striped" id="questionlist">
				<thead>
					<tr>
						<th>题目ID</th>
						<th>题目名称</th>
						<th>章节</th>
						<th>查看</th>
						<th>测试数据</th>
						<th>删除</th>
					</tr>
				</thead>
				<colgroup>
					<col style="width: 10%">
					<col style="width: 50%">
					<col style="width: 10%">
					<col style="width: 10%">
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

	<!-- 模态框（Modal） -->
	<div class="modal" id="openEditProgramDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog"
			style="width: 1000px; overflow: scroll; height: 650px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改题目</h4>
				</div>
				<div class="modal-body">

					<div class="container">
						<form name="formlwn" id="programForm" role="form"
							style="width: 80%" action="QuestionServlet?action=save_program"
							method="post" onsubmit="return false;">

							<div class="input-group ">
								<span class="input-group-addon">选择课程</span> <select
									class="form-control" id="courseId" name="courseId">
									 <option value="0">未选择</option>
								</select> <span class="input-group-addon">选择章节</span> <select
									class="form-control" id="chapterId" name="chapterId">
									<option value="0">未选择</option>
								</select> <span class="input-group-addon">时间</span> <input type="text"
									class="form-control" name="spendTime" id="spendTime_text"
									value="3000">
							</div>
							<div class="input-group  ">
								<span class="input-group-addon">题目名称</span> <input type="text"
									class="form-control" id="programsNameText" name="programsTitle">
								<span class="input-group-addon">语言</span> <select
									class="form-control" id="languageId" name="language">
									<option value="1">C++</option>
									<option value="2">JAVA</option>
								</select>
								<span class="input-group-addon">题目类型</span> <select
									class="form-control" id="editModalProgramType" name="editModalProgramType">
									<option value="0">考试</option>
									<option value="1">练习</option>
								</select>
							</div>
							<br>
							<div class=" form-group">
								<textarea class="form-control"
									style="margin: 0px auto; width: 90%" rows="13"
									placeholder="题目描述" id="programsContextText"
									name="programsContext"></textarea>
							</div>
							<div class=" form-group">

								<textarea class="form-control"
									style="margin: 0px auto; width: 90%" rows="7"
									placeholder="填空源代码" id="programsSourceText"
									name="programsSource"></textarea>
							</div>
							<div class=" form-group">

								<textarea class="form-control"
									style="margin: 0px auto; width: 90%" rows="7"
									placeholder="参考答案" id="programsAnswerText"
									name="programsAnswer"></textarea>
							</div>


						</form>

					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="save_program(this.value)" id="saveProgramBtn">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


	<!-- 测试数据模态框（Modal） -->
	<div class="modal" id="openTestDataDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false"  >
		<div class="modal-dialog" style="overflow: scroll; width: 1000px" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="programProblemIdBtn"  
						aria-hidden="true">&times;</button>
					<h3 class="modal-title" id="testDataModelTitle">测试数据</h3>
				</div>
				<div class="modal-body"  >
					<div class="container row" >
                        <div class="row col-md-1" id="number">
							<label class="list-group-item"> 编号</label>
							
						</div>
						<div class="row col-md-4" id="inputcol">
							<label class="list-group-item"> 输入数据</label>
							
						</div>
						<div class="row col-md-4" id= "outputcol">
							<label class="list-group-item"> 输出数据</label>
						
						</div>

						<div class="row col-md-1" id ="detailcol">
						<label class="list-group-item"> 查看</label>
						
						</div>
						<div class="row col-md-1" id ="delcol">
						<label class="list-group-item"> 删除</label>
						
						</div>

					 
					</div>
					<br>
					<div id="op_status" >新增测试数据</div>
					
					<div class="container row"  >
						<div class="container row" >
							<div class="row col-md-5">
								<textarea class="form-control" rows="3" placeholder="输入数据"
									id="testDataInputText"></textarea>
							</div>
							<div class="row col-md-5">
								<textarea class="form-control" rows="3" placeholder="输出数据"
									id="testDataOutputText"></textarea>
							</div>
						</div>
						<div class="input-group" style="width: 120%; text-align: center">
							<span class="input-group-addon">错误原因</span> 
							<input type="text"class="form-control"    placeholder="请输入该测试数据未能通过的原因" id="testDataReasonText"> 
							
							<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;百分比</span> 
							<input type="text"class="form-control" value="100" style="width: 20%;" id="percentText"> 
							

						</div>
					 
							
							 
					</div>
					
					<br>
				<div class="container"  style ="text-align:center"></div>
					 
				</div>

				<div class="modal-footer">
						<button class="btn btn-default" type="button" onclick="clickTestDataIdBtn()" id="testDataIdBtn" value="-1">插入</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>