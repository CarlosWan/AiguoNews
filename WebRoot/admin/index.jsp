<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>" />
	<title>新闻中国</title>
	<meta charset="utf-8" />
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<!--让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<!--引入Bootstrap-->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
	<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
	<!--[if lt IE 9]>
     <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
     <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  	<![endif]-->
	<!--jQuery (Bootstrap的Javascript插件需要引入 jQuery)-->
	<script src="js/jquery2.0.0.min.js"></script>
	<!--包含已编译的插件-->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function checkForm(){
	
	}
	
	</script>
	<style type="text/css">
		.bg {
		  margin:0;
 	      background:url(img/bg.jpg) no-repeat center;
    	  background-size:100% 100%;
    	}	
		
		.css_shadow{
			border:#909090 1px solid;
			background:#fff;
			color:#333;
			/*以下为重点*/
			-ms-filter:progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#000000')";  /* For IE 8 */
			filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#000000');  /* For IE 5.5 - 7 */
			-moz-box-shadow: 2px 2px 10px #909090;/* for firefox */
			-webkit-box-shadow: 2px 2px 10px #909090;/* for safari or chrome */
			box-shadow:2px 2px 10px #909090;/* for opera or ie9 */
		}
	</style>
</head>

<body class="bg">
<div class="container">
	<br><br><br><br><br><br><br><br><br>
	<div class="row clearfix">
		<div class="col-md-4 column"></div>
		<div class="col-md-4 column css_shadow">
			<h2 class="text-center text-primary">
				新闻发布系统后台管理
			</h2>
			<br>
			<form action="user?method=login" class="form-horizontal" role="form" method="post"
			     onSubmit="return checkForm()">
				<div class="form-group">
					<label for="inputUsername" class="col-xs-2 control-label">
						<span class="glyphicon glyphicon-user" style="font-size:18px;"></span>
					</label>
					<div class="col-xs-10">
						<input type="text" class="form-control" id="inputUsername" 
						    name="username" value="${cookie.username.value }"
							placeholder="请输入用户名"/>
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword" class="col-md-2 control-label">
					 	<span class="glyphicon glyphicon-lock" style="font-size:18px;"></span>
					 </label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="inputPassword" 
						name="password" value="${cookie.password.value }"
							placeholder="请输入密码"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-md-10">
						<div class="checkbox">
							 <label><input type="checkbox" name="isSave"/> 记住我</label>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-md-10">
						 <button type="submit" class="btn btn-primary btn-block">登录</button>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-md-10">
							 <font color="red">${requestScope.error }</font>
						</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-md-10">
						<a href="userindex.jsp"><button type="button" class="btn btn-primary btn-block">返回新闻首页</button></a>
					</div>
				</div>
					
			</form>
			<br>
			<br>
			<div class="text-center text-info">
				<span class="glyphicon glyphicon-bell">本系统为内部人员使用，未经授权谢绝访问！</span>
			</div>
			<br>
		</div>
		<div class="col-md-4 column"></div>
	</div>
	<br><br><br><br>
	<div class="row clearfix text-center" id="footer">
		<div class="copyRight" style="color:#fff;">
			Copyright ©2018  Company Name.版权所有
		</div>
	</div>
	<br><br><br>
</div>
</body>
</html>
