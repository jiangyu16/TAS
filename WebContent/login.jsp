<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
String errorMsg = (String)request.getSession().getAttribute("msg");
 
%>
<jsp:include page="frame/Header.jsp"></jsp:include>

<body>

	<div class="container">
		<div class="row col-md-6">
			<form name="login_form" role="form" action="UserServlet?action=login"
				method="POST" onsubmit="return isValidate(login_form)">
				<fieldset>
					<div id="legend">
						<legend class="caption">登录</legend>
					</div>
					<div class="form-group">
						<label for="username">用户名</label> <input type="text"
							class="form-control " name="username" id="username">
					</div>
					<div class="form-group">
						<label for="password">密码</label> <input type="password"
							class="form-control" name="password" id="password"
							 >
					</div>
					<div class ="form-group">
					<label for="" class="radio-inline"   >
					  <input type="radio" value = "student" name="userType"/> 学生
					</label>
					<label for="" class="radio-inline"   >
					  <input type="radio" value = "teacher" name="userType"/> 教师
					</label>
					<label for="" class="radio-inline"  >
					  <input type="radio" value = "admin" name="userType"/> 管理员
					</label>
					</div>
					 

					<div class="form-group">
						<button type="submit" class="btn btn-success">登录</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
    <% if (null != errorMsg) { %>		<%-- 登录验证失败提示信息 --%>
    <div class="container">
    <div class="alert alert-error">
    <%=errorMsg %>
    </div>
    </div>
    <%    request.getSession().removeAttribute("msg");
    } %>
</body>
<script type="text/javascript">
function isValidate(login_form) {
	var username = login_form.username.value;
	var password = login_form.password.value;
	var usertype=login_form.userType.value;
	
	if (username == "" || password == "") {
		alert("请填写用户名和密码！");	
		
		return false;
	}
	 
	if(usertype==""){
		
		alert("请选择用户类型!");
		return false;
		}
	return true;
}

</script>
</html>