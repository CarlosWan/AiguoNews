<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<script type="text/javascript">
		$(document).ready(function(){
			//设置消息显示
			$(function(){
				if("${not empty requestScope.msg}"=="true"){
					$("#msg").show();	
					$("#msg").delay(3000).fadeOut(2000); //延时淡出 
				}else{
					$("#msg").hide();
				}
			});
			
			//JSON数组
			var objArr = [{value:"0",text:""},{value:"1",text:"评论IP"},{value:"2",text:"评论内容"}];
			$.each(objArr,function(index,content){
				if("${requestScope.conditionType}"==content.value){
					$("#selectConditionType").append("<option value='"+content.value+"' selected>"+content.text+"</option>");
				}else{
					$("#selectConditionType").append("<option value='"+content.value+"'>"+content.text+"</option>");
				}
			});
		
			//搜索类别下拉框发生改变时
			$("#selectConditionType").change(function(){
				if($(this).val()=="0"){
					$("#queryCondition").val("");  //清空条件表达式
				}
			});
			
			//查询按钮点击时
			$("#btnQuery").click(function(){
				if($("#selectConditionType").val()!="0" && $("#queryCondition").val()==""){
					alert("请输入查询的条件!");
				}
			});
			
			//点击"删除"超链接时
			$("[name='del_link']").click(function(){
				//将要删除的用户Id传入模态框
				$("#deleteCommentId").val($(this).attr("rel"));
				//显示模态框
				$("#deleteUser").modal(); 
			});
			
			//模态框中的"确认删除"点击时
			$("#deleteHaulBtn").click(function(){
				window.location.href="<%=basePath%>admin/CommentServlet?method=deleteComment&commentId="+$("#deleteCommentId").val();
			});
			
			//全选
			$("#chkAll").click(function(){
				if($("#chkAll").prop("checked")){
					$("[name='chk']").prop("checked",true);
				}else{
					$("[name='chk']").prop("checked",false);
				}
			});
			
			//单个复选框被点击时
			$("[name='chk']").click(function(){
				//如果没被选中
				if($(this).prop("checked")==false){
					$("#chkAll").prop("checked",false);
				}else{
					//如果被选中 
					var sumLength = $("[name='chk']").length;
					var selectedLength = $("[name='chk']:checked").length
					if(sumLength==selectedLength){
						$("#chkAll").prop("checked",true);
					}
				}
			});
			
			//批量删除 按钮点击时
			$("#btnDelBatch").click(function(){
				//获取当前页面选中的checkbox 个数
				var selectedCount = $("[name='chk']:checked").length;
				if(selectedCount==0){
					alert("请先勾选要删除评论对应的复选框!");
					return;
				}
				
				if(!confirm("确定要删除这些评论吗?")){
					return;
				}
				
				//获取选中的复选框绑定的多个userId值
				var arr = $("[name='chk']:checked");
				var ids=""; 
				for(var i=0;i<arr.length;i++){
					ids += arr[i].value +",";
				}
				//去除尾部的逗号
				ids = ids.substring(0,ids.length-1);
				//执行删除操作
				window.location.href="<%=basePath%>admin/CommentServlet?method=deleteCommentBatch&commentIds="+ids;
			});
		});
		
		//翻页和跳页功能
		function gotoPage(currPage){
			window.location.href=
				"<%=basePath%>admin/CommentServlet?method=gotoCommentManage&currPage="+currPage
						+"&conditionType="+$("#selectConditionType").val()
						+"&queryCondition="+$("#queryCondition").val();
		}
		</script>
	</head>

	<body onload="disptime()">
		<!--顶部导航栏-->
		<%@include file="top.jsp" %>
		<div class="container-fluid">
		<div class="row clearfix">
		<!--左边栏 导航-->
		 <jsp:include page="left.jsp" >
		 <jsp:param value="commentManage" name="paramName"/>
		 </jsp:include>
				<!--右边栏  主要显示区-->
				<div id="col2" class="col-sm-10 col-md-10 column" data-spy="scroll" data-target="#myNavBar" 
					data-offset="0" style="height:600px;overflow:auto;position:relative;">
					<div id="nav" style="border-bottom: 1px solid #EEEEEE;">
						<ul class="breadcrumb">
							<li>
								<span class="fa fa-tasks" style="vertical-align: middle;"></span>
								<a rel="nofollow" href="javascript:void(0);">功能管理</a>
							</li>
							<li class="active">
								评论管理
							</li>
						</ul>
					</div>
					<!--这里添加内容-->
					<div class="panel panel-default" style="margin-top:5px;">
						<div class="panel-heading">
							<h3 class="panel-title">
								评论列表
							</h3>
						</div>
						<div class="panel-body">
							<div class="col-md-12 column">
								<form class="form-horizontal" action="admin/CommentServlet?method=gotoCommentManage" method="post">
									<div class="row clearfix">
										<div class="col-md-2 column">
											<select class="form-control" id="selectConditionType" name="conditionType">
											</select>
										</div>
										<div class="col-md-2 column">
											<input type="text" class="form-control" placeholder="输入查询条件" 
												id="queryCondition" name="queryCondition" value="${ requestScope.queryCondition }">
										</div>
										<div class="col-md-2 column">
											<input type="submit" id="btnQuery" value="查询" class="btn btn-primary">
										</div>
										<div class="col-md-3 column">
										</div>
										<div class="col-md-3 column">
											<input type="button" value="批量删除" class="btn btn-default"  id="btnDelBatch"
												style="border:1px solid #fff;color:#fff;color:#fff;background-color: #EC971F;">
											&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</form>
							</div>
							<br><br>
							<div class="panel-body" >
								<table class="table table-hover" style="margin-top:2px auto;">
									<thead>
										<tr style="background-color: #129E5A;color:#fff;">
											<th><input type="checkbox" id="chkAll"  value="option1"></th>
											<th>编号</th>
											<th>评论ID</th>
											<th>用户ID</th>
											<th>新闻ID</th>
											<th>评论IP</th>
											<th>评论內容</th>
											<th>评论时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="Comment" items="${ requestScope.commentList }" varStatus="vs">
											<tr>
												<td>
													<input type="checkbox" id="inlineCheckbox${ Comment.commentId }" 
													name="chk"
													value="${ Comment.commentId }" style="display:${ display };">
												</td>
												<td scope="row">${ vs.count + pageData.pageSize*(pageData.currPage-1) }</td>
												<td>${ Comment.commentId }</td>
												<td>${ Comment.userId }</td>
												<td>${ Comment.newsId }</td>
												<td>${ Comment.ipaddr }</td>
												<td>${ Comment.content }</td>
												<td>${ Comment.date }</td>
												
												<td>
													
													<a href="javascript:void(0);" name="del_link" style="display:${ display }"
														rel="${ Comment.commentId }">
														<span class="fa fa-trash"></span> 删除  </a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!--分页组件-->
								<div style="margin:0px;text-align:center;margin-top:0px;border-top: 1px solid #CCCCCC;">
									<br>
									<ul class="pagination" style="height:50px;">
									<c:choose>
									   <c:when test="${ pageData.currPage eq 1 }">
										   	<li class="disabled">
												<a href="javascript:void(0)">«</a>
											</li>
									   </c:when>
									   <c:otherwise>
											<li>
												<a href="javascript:gotoPage(${ pageData.currPage-1 })">«</a>
											</li>
										</c:otherwise>
									</c:choose>
									<c:forEach var="p" begin="1" end="${ pageData.sumPages }">
										<c:choose>
											<c:when test="${ pageData.currPage eq p }">
												<li class="active">
													<a href="javascript:void(0)">${ p }</a>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="javascript:gotoPage(${ p })">${ p }</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>	
									 <c:choose>
									   <c:when test="${ pageData.currPage eq pageData.sumPages }">
										   	<li class="disabled">
												<a href="javascript:void(0)">»</a>
											</li>
									   </c:when>
									   <c:otherwise>
											<li>
												<a href="javascript:gotoPage(${ pageData.currPage+1 })">»</a>
											</li>
										</c:otherwise>
									 </c:choose>
										<li>
											总记录 ${ pageData.maxCount } 总页数 ${ pageData.sumPages } 当前第 ${ pageData.currPage } 页 转到
											<input type="text" style="width:30px;text-align: center;" class="input" value="1"
											onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
												id="skipPage" /> 页
											<button type="button" class="btn btn-primary active" id="btnSkipPage"
												onclick="gotoPage(skipPage.value)">确定</button>
										</li>
									</ul>
								</div>
							</div>
						</div>
					   <div class="panel-footer" id="msg"  
					   	style="background-color:#47BB67;display:none;color:#fff;text-align:center;">${ msg }</div>
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
						<input type="hidden" id="deleteCommentId" />
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