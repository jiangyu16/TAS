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
<script>
var ctrPage=1;//翻页按钮的页面
var ctrpagenum=5;//翻页按钮每页有几个按钮
var totalPagest;//总共有多少页
var pageSizet=2;//每页多少行
 var curPageT=1;
 var dataList;
 var update_chapterId;
 var update_chapterName;
 var chapterArray;
 
 
 get_classes_fun(1);

 $(document).on("click","#page li", function(){
	 
	 var pagectro= $(this).text();
	 
	 
	//if( $(this).text()=='next')alert( $(this).text());
	  if(pagectro=='previous'){
		  
		  ctrPage--;
		  if(ctrPage==0)ctrPage=1;
		  else {
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
		 var className=$("#searchName_text").val();
		 //alert(className);
		 if(className == "") {
			 get_classes_fun( pagectro);
			
		 } else {
			 search_class(pagectro);}
	  return false;
}
 }) ; 

function get_classes_fun(curPage){
	 
	url="ClassServlet?action=get_classes";
    $("#questionlist tr:not(:first)").remove();
	 var args={
			 "curPage":curPage,
			 "pageSize":pageSizet,
			 //"time":new Date()
	          };
	  
	 $.getJSON(url,args,function(data){
		 totalPages=data.totalPages;
		 totalPagest=totalPages;
		 dataList = data.list;
		 
		// alert(totalPages);
		//+"<td> <button class='btn btn-default'  href='page.jsp' onclick='operation_program(this.value)' value='"+programProblemId+"'> 查看</button></td>" 
		$("#questlisttbody tr").remove();
		chapterArray=data.list;
		  for(var i=0;i<(data.list).length;i++){
			  var classId = (data.list)[i].classId;
				 var className1 =  (data.list)[i].className;
				 var tr="<tr><td>"+classId+"</td>"
				 +"<td>"+className1+"</td>"
				 +"<td> <button class='btn btn-default' onclick='redirect_class_student("+classId+",this.value)' value='"+className1+"'> 查看学生</button></td>"
				// +"<td> <button class='btn btn-default' onclick='redirect_class_student(this.value)' value='"+classId+"'> 查看学生</button></td>"
				 +"<td> <button class='btn btn-default' onclick='show_update_chapter_modal(this.value)' value='"+classId+"'> 修改数据</button></td>"
				 +"<td> <button class='btn btn-default' onclick='delete_chapter(this.value)' value='"+classId+"'> 删除数据</button></td></tr>";
				 // +"<td><a href='./admin/question/AdminCourse.jsp' data-toggle='modal' data-target='#openEditProgramDialog'>打开对话框</a> </td>"
		
				
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
function redirect_class_student(classId,className){
	
	// alert(className);
	$("#questionlist").empty();
	$("#questionlist").load("./admin/student/student_list.jsp?classId="+classId+"&className="+className);
}
function show_update_chapter_modal(chapterId){
	update_chapterId=chapterId;
	 for(var i=0; i<chapterArray.length;i++){
		if(chapterArray[i].classId==chapterId) $("#update_chapterModal").modal();
	 }
}

function  update_chapter(){
	
	update_chapterName = $("#updatechapterName").val();
	console.log(update_chapterId);
	 if(update_chapterName=="") $("#updatechapterName").focus();
	 else {
		 var url="ClassServlet?action=update_class";
			var args={ "classId":update_chapterId,"className":update_chapterName};
			$.post(url,args,function(data){
				if(data==1)get_classes_fun(1);
			});
		 $("#updatechapterName").val(""); 
	     $("#update_chapterModal").modal('hide');
	 }
	 
}

function delete_chapter(classId){
	 
	var url="ClassServlet?action=delete_class";
	var args={ "classId":classId,"time":new Date()};
	$.post(url,args,function(data){
		if(data==1)get_classes_fun(1);
	});
}

function new_class(){
	 
	var className=$("#className_text").val();
	if(className==""){
		$("#className_text").focus();
		return;}
	var url="ClassServlet?action=new_class";
	var args={"className":className};
	$.post(url,args,function(data){
		if(data==1)get_classes_fun(1);
	});
}

function search_class (curPage){
	//alert("bbb");
	var className=$("#searchName_text").val();
	$("#questionlist tr:not(:first)").remove();
	if(className==""){
		$("#searchName_text").focus();
		return;}
	var url="ClassServlet?action=search_class";
	var args={ "className":className,
				"curPage":curPage,
			 'pageSize':pageSizet};
	
	 $.getJSON(url,args,function(data){
		 totalPages=data.totalPages;
		 totalPagest=totalPages;
		 dataList = data.list;
		 
		// alert(totalPages);
		//+"<td> <button class='btn btn-default'  href='page.jsp' onclick='operation_program(this.value)' value='"+programProblemId+"'> 查看</button></td>" 
		$("#questlisttbody tr").remove();
		chapterArray=data.list;
		  for(var i=0;i<(data.list).length;i++){
			  var classId = (data.list)[i].classId;
				 var className =  (data.list)[i].className;
				 var tr="<tr><td>"+classId+"</td>"
				 +"<td>"+className+"</td>"
				 +"<td> <button class='btn btn-default' onclick='redirect_class_student("+classId+",this.value)' value='"+className+"'> 查看学生</button></td>"
				// +"<td> <button class='btn btn-default' onclick='redirect_class_student(this.value)' value='"+classId+"'> 查看学生</button></td>"
				 +"<td> <button class='btn btn-default' onclick='show_update_chapter_modal(this.value)' value='"+classId+"'> 修改名称</button></td>"
				 +"<td> <button class='btn btn-default' onclick='delete_chapter(this.value)' value='"+classId+"'> 删除班级</button></td></tr>";
				 // +"<td><a href='./admin/question/AdminCourse.jsp' data-toggle='modal' data-target='#openEditProgramDialog'>打开对话框</a> </td>"
		
				
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


</script>
<body>
<br>
 <div class="container">
 
   
	<!-- <div class="form-group row col-md-4">
		<select  class="form-control" id="course">
		<option value=0>请选择</option>
		</select>
		 
	</div> -->
	
	<div class="form-group row col-md-5">
	 
	  <div class="input-group">
             
            <input type="text" class="form-control" placeholder="请输入班级名称" id="searchName_text">
             <span class="input-group-btn">
              <button class="btn btn-default" type="button" onclick="search_class(1)" >查找</button>
              </span>
        </div>
        
	</div>
	
	 <div class="form-group row col-md-1">
		 
	</div>
	<div class="form-group row col-md-5">
	 
	  <div class="input-group">
             
            <input type="text" class="form-control" placeholder="请输入班级名称" id="className_text">
             <span class="input-group-btn">
              <button class="btn btn-default" type="button" onclick="new_class ( )" >新增</button>
              </span>
        </div>
        
	</div>
	
 
</div>

	<div class="container  ">
		<div class="row col-md-10">
			<table class="table table-striped" id="questionlist">
				<thead>
					<tr>
						<th>班级ID</th>
						<th>班级名称</th>
						<th>查看学生</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<colgroup>
					<col style="width: 20%">
					<col style="width: 50%">
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
<div class="modal fade" id="update_chapterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改班级名称</h4>
            </div>
             <div class="modal-body">
       
           
            <form class="form-horizontal"  id="editForm"   method="post">
                    <div class="form-group">
                         
                        <div  >
                            <input type="text" placeholder="请输入班级名称" class="form-control input-sm" name="username" id="updatechapterName">
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