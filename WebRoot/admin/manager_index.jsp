<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
	<base href="<%=basePath%>" />
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
		<%@include file="/admin/top.jsp" %>
		<div class="container-fluid">
			<div class="row clearfix">
				<!--左边栏 导航-->
				<%@include file="/admin/left.jsp" %>
				<!--右边栏  主要显示区-->
				<div id="col2" class="col-sm-10 col-md-10 column" data-spy="scroll" data-target="#myNavBar" 
					data-offset="0" style="height:600px;overflow:auto;position:relative;">
					<div id="nav" style="border-bottom: 1px solid #EEEEEE;">
						<ul class="breadcrumb">
							<li>
								<span class="fa fa-tasks" style="vertical-align: middle;"></span>
								<a rel="nofollow" href="javascript:void(0);">新闻中国后台管理系统</a>
							</li>
							<li class="active">
								首页
							</li>
						</ul>
					</div>
					<!--这里添加内容-->
					<div class="panel panel-info" style="margin-top:5px;">
						<div class="panel-heading">
							<h4 class="panel-title" style="text-align: center;">
								【公告】
							</h4>
						</div>
						<div class="panel-body">
							<div class="col-md-12 column">
								<marquee direction="up" height="100px" scrolldelay ="350" onmouseover="this.stop()" onmouseout="this.start()">
								<ul class="newsList">
									<li>
										<a rel="nofollow" title="" href="HappyNew.jsp" >爱国NEWS祝您新年快乐！猪年大吉</a>
										<img src="img/new.gif">
										<span class="date" style="float: right;">[ 2019-01-28  ]</span>
									</li>
								    <li>
										<a rel="nofollow" title="" href="http://www.layoutit.cn">欢迎使用</a>
										<img src="img/new.gif">
										<span class="date" style="float: right;">[ 2019-01-28  ]</span>
									</li>
									<li>
										<a rel="nofollow" title="" href="notice_view.jsp">爱国NEWS系统上线   </a>
										<img src="img/new.gif">
										<span class="date" style="float: right;">[ 2019-01-28 ]</span>
									</li>
									
									<li>
										<a rel="nofollow" title="" href="http://www.layoutit.cn">请大家及时报告使用中出现的问题 </a>
										<img src="img/new.gif">
										<span class="date" style="float: right;">[ 2019-01-28 ]</span>
									</li>
									<li>
										<a rel="nofollow" title="" href="Holiday.jsp">放假通知</a>
										<img src="img/new.gif">
										<span class="date" style="float: right;">[ 2019-01-28 ]</span>
									</li>
									
									<li>
										<a rel="nofollow" title="" href="http://www.layoutit.cn">本系统为测试版本</a>
										<span class="date" style="float: right;">[ 2019-01-28 ]</span>
									</li>
									<li>
										<a rel="nofollow" title="" href="http://www.layoutit.cn">公测版本敬请期待</a>
										<span class="date" style="float: right;">[ 2019-01-28 ]</span>
									</li>
								</ul>
								</marquee>
								<br>
								<span style="float:right;"><a href="notice_manage.html"><img src="img/more.gif"></a></span>
							</div>
						</div>
					</div>
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