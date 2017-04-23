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
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
   	<link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="bootstrap/css/blog-home.css" rel="stylesheet">
    <script src="<%=basePath %>bootstrap/js/jquery-2.1.1.js"></script>
    <script src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
</head>
 
<script type="text/javascript">
jQuery(document).ready(function() {
	
	var url="CourseServlet?action=get_course";
	 var args={"time":new Date()}; 
	$.getJSON(url, function(data){
		if(data.length==0){
			 
		}else{
			for(var i=0;i<data.length;i++){
				var courseId=data[i].courseId;
				var courseName=data[i].courseName;
				$("#course").append("<option value='"+courseId+"'>"+courseName+"</option>");
			}
			
		}
	});});
  
	$("#course").change(show_chapter);
	var chapterArray;
	var chapterNameTextReadOnly=false;
	var update_chapterId;
	var update_chapterName;
	function show_chapter(){
		 
		$("#chapter option:not(:first)").remove();
		 
		var course=$("#course" ).val();
		if(course==0)return;
		if(course!=""){
			var url="CourseServlet?action=get_chapter";
			var args={"courseId":course,"time":new Date()};
			$.getJSON(url,args,function(data){
				//alert("hahahah");
				 $("#chapter tr").remove();
				if(data.length==0){
					alert("当前课程没有章节");
					
				}else{
					chapterArray=data;
					for(var i=0;i<data.length;i++){
						var chapter=data[i];
						var chapterId=data[i].chapterId;
						var chapterName=data[i].chapterName;
						 var tr="<tr><td>"+chapterName+"</td>"
						 +"<td> <button class='btn btn-default'  onclick='show_update_chapter_modal(this.value)' value='"+chapterId+"'> 修改</button></td>" 
						+"<td> <button class='btn btn-default' onclick='delete_chapter(this.value)' value='"+chapterId+"'> 删除</button></td><tr>"
						
						$("#chapter tbody").append(tr);
						
						 
					}
					 
				}
			});
		}
	} 
function show_update_chapter_modal(chapterId){
	update_chapterId=chapterId;
	 for(var i=0; i<chapterArray.length;i++){
		if(chapterArray[i].chapterId==chapterId) $("#update_chapterModal").modal();
	 }
}
function  update_chapter( ){
	update_chapterName = $("#updatechapterName").val();
	 if(update_chapterName=="") $("#updatechapterName").focus();
	 else {
		 var url="CourseServlet?action=update_chapter";
			var args={ "chapeterId":update_chapterId,"chapterName":update_chapterName,"time":new Date()};
			$.post(url,args,function(data){
				if(data==1)show_chapter();
			});
		 $("#updatechapterName").val(""); 
	     $("#update_chapterModal").modal('hide');
	 }
	 
}
function delete_chapter(chapetrId  ){
 
	var url="CourseServlet?action=delete_chapter";
	var args={ "chapterId":chapetrId,"time":new Date()};
	$.post(url,args,function(data){
		if(data==1)show_chapter();
	});
}
 
function new_chapter(){
	 
	var courseId=$("#course").val();
	if(courseId==0)alert("请选择课程名称");
	else {
		var chapterName=$("#chapterName_text").val();
		if(chapterName==""){
			$("#chapterName_text").focus();
			return;}
		var url="CourseServlet?action=new_chapter";
		var args={ "courseId":courseId,"chapterName":chapterName,"time":new Date()};
		$.post(url,args,function(data){
			if(data==1)show_chapter();
		});
	}
}
</script>
<body>
<br>
 <div class="container">
 
   
	<div class="form-group row col-md-4">
		<select  class="form-control" id="course">
		<option value=0>请选择课程</option>
		</select>
		
	</div>
	 <div class="form-group row col-md-1">
		 
	</div>
	<div class="form-group row col-md-6">
	 
	  <div class="input-group">
             
            <input type="text" class="form-control" placeholder="请输入章节名称" id="chapterName_text">
             <span class="input-group-btn">
              <button class="btn btn-default" type="button" onclick="new_chapter ( )" >新增</button>
              </span>
        </div>
        
	</div>
	
 
</div>
<div class ="container">
<div class="form-group  row col-md-10"  style=" overflow:scroll;   height:500px;">
		<label for="name">章节列表</label>
		 <table class ="table" id="chapter" >
		  <colgroup>
         	<col style="width:80%">
         	<col style="width:10%">
         	<col style="width:10%">
         	</colgroup>
		 <tbody></tbody>
		 </table>
	</div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="update_chapterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改章节名称</h4>
            </div>
             <div class="modal-body">
       
           
            <form class="form-horizontal"  id="editForm"   method="post">
                    <div class="form-group">
                         
                        <div  >
                            <input type="text" placeholder="请输入章节名称" class="form-control input-sm" name="username" id="updatechapterName">
                        </div>
                         
                    </div>
                     
                </form>
                 </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="update_chapter()">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>   
</body>
</html>