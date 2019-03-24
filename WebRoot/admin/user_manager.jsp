<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<%@include file="top.jsp" %>
		<div class="container-fluid">
			<div class="row clearfix">
				<!--左边栏 导航-->
				<jsp:include page="left.jsp">
					<jsp:param value="userManager" name="paraName"/>
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
							<li class="active">
								用户管理
							</li>
						</ul>
					</div>
					<!--这里添加内容-->
					<div class="panel panel-default" style="margin-top:5px;">
						<div class="panel-heading">
							<h3 class="panel-title">
								用户列表
							</h3>
						</div>
						<div class="panel-body">
							<div class="col-md-12 column">
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-2 column">
											<select class="form-control" id="selectOffer">
												<option></option>
												<option>按用户名查询</option>
												<option>按姓名查询</option>
											</select>
										</div>
										<div class="col-md-2 column">
											<input type="text" class="form-control" placeholder="输入查询条件" id="queryCondition">
										</div>
										<div class="col-md-2 column">
											<input type="submit" value="查询" class="btn btn-primary">
										</div>
										<div class="col-md-3 column">
										</div>
										<div class="col-md-3 column">
											<input type="button" value="新增人员" onclick="javascript:location.href='add_user.html'" 
												class="btn btn-default" style="border:1px solid #fff;color:#fff;color:#fff;background-color:#EC971F;">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" value="批量删除" class="btn btn-default" 
												style="border:1px solid #fff;color:#fff;color:#fff;background-color: #EC971F;"
												data-toggle="modal" data-target="#deletePerson">
										</div>
									</div>
								</form>
							</div>
							<br><br>
							<div class="panel-body" >
								<table class="table table-hover" style="margin-top:2px auto;">
									<thead>
										<tr style="background-color: #129E5A;color:#fff;">
											<th><input type="checkbox" id="inlineCheckboxAll" value="option1"></th>
											<th>编号</th>
											<th>用户名</th>
											<th>姓名</th>
											<th>性别</th>
											<th>联系方式</th>
											<th>E-Mail</th>
											<th>是否管理员</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
						
									<c:forEach var="user" items="${ requestScope.userList }" varStatus="vs">
										<tr>
											<td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
											<td scope="row">${vs.count }</td>
											<td>${ user.username }</td>
											<td>${ user.realname }</td>
											<td>${ user.gender }</td>
											<td>${ user.telephone }</td>
											<td>${ user.email }</td>
											<td><c:if test="${ user.status ne 0 }">
												   		是
												</c:if>
												<c:if test="${ user.status eq 0 }">
														否
												</c:if></td>
											<td>
												<a href="edit_user.html"><span class="fa fa-pencil-square"></span> 编辑</a> &nbsp;| &nbsp;
												<a href="javascript:void(0);" data-toggle="modal" data-target="#deleteUser"><span class="fa fa-trash"></span> 删除</a>
											</td>
										</tr>
									</c:forEach>
							
									</tbody>
								</table>
								<!--分页组件-->
								<div style="margin:0px;text-align:center;margin-top:0px;border-top: 1px solid #CCCCCC;">
									<br>
									<ul class="pagination" style="height:50px;">
										<li>
											<a href="#">«</a>
										</li>
										<li class="active">
											<a href="#">1</a>
										</li>
										<li class="disabled">
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li>
											<a href="#">5</a>
										</li>
										<li>
											<a href="#">»</a>
										</li>
										<li>
											总记录 1 总页数 1 当前第 1 页 转到
											<input type="text" style="width:30px;text-align: center;" class="input" value="1" /> 页
											<button type="button" class="btn btn-primary active">确定</button>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		<!-- 模态框   信息删除确认 -->
		<div class="modal fade" id="deleteUser">
			<div class="modal-dialog">
				<div class="modal-content message_align">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
						<h4 class="modal-title">提示</h4>
					</div>
					<div class="modal-body">
						<!-- 隐藏需要删除的id -->
						<input type="hidden" id="deletePersonId" />
						<p>您确认要删除吗？</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="deleteHaulBtn">确认删除</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
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