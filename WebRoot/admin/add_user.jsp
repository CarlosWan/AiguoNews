<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
	    <base href="<%=basePath%>">
		<title>新闻中国后台管理</title>
		<meta charset="utf-8">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<!--让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放-->
		<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet">
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
		<script src="js/myscript.js"></script>
		<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
		<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">
		<link href="css/myindex.css" rel="stylesheet">
		<style type="text/css">

		</style>
	</head>

	<body onload="disptime()">
		<!--顶部导航栏-->
		<%@ include file="top.jsp" %>
		<div class="container-fluid">
			<div class="row clearfix">
				<!--左边栏 导航-->
				<jsp:include page="left.jsp">
				    <jsp:param value="userManage" name="paraName"/>
				</jsp:include>
				<!--右边栏  主要显示区-->
				<div id="col2" class="col-sm-10 col-md-10 column" data-spy="scroll" data-target="#myNavBar" 
					data-offset="0" style="height:600px;overflow:auto;position:relative;">
					<div id="nav" style="border-bottom: 1px solid #EEEEEE;">
						<ul class="breadcrumb">
							<li>
								<span class="fa fa-tasks" style="vertical-align: middle;"></span>
								<a rel="nofollow" href="javascript:void(0);">系统管理</a>
							</li>
							<li>
								<a rel="nofollow" href="javascript:void(0)">用户管理</a>
							</li>
							<li class="active">
								新增用户
							</li>
						</ul>
					</div>
					<!--这里添加内容-->
					<form class="form-horizontal" action="admin/userAdmin?method=addUser" method="post">
						<div class="panel panel-default" style="margin-top:5px;width:1100px;">
							<div class="panel-body">
								<div class="col-md-12 column">
									<div class="row clearfix">
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="inputUsername" class="col-sm-2 control-label" style="text-align: left;">用户名
												<span style="color:#ff0000;">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="inputUsername" name="username"
												value="${requestScope.username }">
											</div>
											
											<div class="col-sm-2" style="color: #ff0000;">
											    ${ error }
											</div>
		
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="inputPassword" class="col-sm-2 control-label" style="text-align: left;">密码
											<span style="color:#ff0000;">*</span></label>
											<div class="col-sm-6">
												<input type="password" class="form-control" id="inputPassword" name="password">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="inputRePassword" class="col-sm-2 control-label" style="text-align: left;">确认密码
											<span style="color:#ff0000;">*</span></label>
											<div class="col-sm-6">
												<input type="password" class="form-control" id="inputRePassword" name="repassword">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="inputRealname" class="col-sm-2 control-label" style="text-align: left;">姓名
											<span style="color:#ff0000;">*</span></label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="inputRealname" name="realname">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="rdoGender" class="col-sm-2 control-label" style="text-align: left;">性别
											<span style="color:#ff0000;">*</span></label>
											<div class="col-sm-6" >
												<input type="radio" class="from-control" id="rdoGender" name="gender" value="男" checked >
												<img src="img/m.gif">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" class="from-control" id="rdoGender" name="gender" value="女">
												<img src="img/f.gif">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="inputTelephone" class="col-sm-2 control-label" style="text-align: left;">手机号码</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="inputTelephone" name="telephone">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="inputEmail" class="col-sm-2 control-label" style="text-align: left;">E-Mail</label>
											<div class="col-sm-6">
												<input type="email" class="form-control" id="inputEmail" name="email">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-1"></div>
											<label for="rdoIsAdmin" class="col-sm-2 control-label" style="text-align: left;">是否管理员</label>
											<div class="col-sm-6" >
												<input type="radio" class="from-control" id="rdoIsAdmin" name="status" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" class="from-control" id="rdoIsAdmin" name="status" value="0" checked>否
											</div>
										</div>
									</div>
									<br>
									<div style="text-align:right;padding-right:262px;">
										<button type="submit" class="btn btn-primary" onclick="">保    存</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">取    消</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--设置菜单折叠更改图标脚本 -->
		<script>
			$(function() {
				$(".nav-header").click(function(e) {
					/*切换折叠指示图标*/
					$(this).find("span").toggleClass("glyphicon-chevron-down");
					$(this).find("span").toggleClass("glyphicon-chevron-up");
				});
			});
		</script>
		<script>
			h = document.body.clientHeight;
			if(document.body.clientHeight < document.documentElement.clientHeight) {
				h = document.documentElement.clientHeight
			}
			colHeight = h - document.getElementById("col1").offsetTop;
			document.getElementById("col1").style.height = colHeight + "px";
			document.getElementById("col2").style.height = colHeight + "px";
		</script>
	</body>

</html>