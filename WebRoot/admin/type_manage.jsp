<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		
		//查询按钮点击时
			$("#btnQuery").click(function(){
				if($("#selectConditionType").val()!="0" && $("#queryCondition").val()==""){
					alert("请输入查询的条件!");
				}
			});
		
		  //点击“删除”超链接时
		  $( "[name='del_link']" ).click(function(){
		     
		     $("#deleteTypeId").val($(this).attr("rel"));
		     $("#deleteType").modal();
		  });
		  
		 //模态框中的“确认删除点击时”
		   $("#deleteHaulBtn").click(function(){
		   window.location.href="<%=basePath%>admin/typeAdmin?method=deleteType&typeId="+$("#deleteTypeId").val();
		   });
		 });
		 
      //翻页和跳页功能
		function gotoPage(currPage){
			window.location.href=
				"<%=basePath%>admin/typeAdmin?method=gotoTypeManage&currPage="+currPage
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
		 <jsp:param value="typeManage" name="paramName"/>
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
						主题管理
					</li>
				</ul>
			</div>
			<!--这里添加内容-->
			<div class="panel panel-default" style="margin-top:5px;">
				<div class="panel-heading">
					<h3 class="panel-title">
						查询条件
					</h3>
				</div>
				<form class="form-horizontal" action="admin/typeAdmin?method=gotoTypeManage" method="post">
					<div class="panel-body">
						<div class="col-md-8 column">
							<div class="row clearfix">
								<div class="form-group">
									<label for="inputTypeName" class="col-sm-2 control-label">主题名称</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="queryCondition" name="queryCondition" value="${ requestScope.queryCondition }"/>
									</div>
									<div class="col-sm-4">
										<button type="submit" class="btn btn-primary" onclick="" id="btnQuery">查   询</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="reset" class="btn btn-default" onclick="">重   置</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>	
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						查询列表
					</h3>
				</div>
				<div class="col-md-12 column" style="line-height: 40px;">
					<div class="row clearfix">
						<div class="col-md-9 column"></div>
						<div class="col-md-3 column" style="text-align: right;">
							<input type="button" value="添加" class="btn btn-default" 
								onclick="javascript:location.href='<%=basePath %>admin/typeAdmin?method=gotoAddType'"
								style="border:1px solid #fff;color:#fff;background-color:#EC971F;">
						</div>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-hover" style="margin-top:2px auto;">
						<thead>
							<tr style="background-color: #129E5A;color:#fff;">
								<th><input type="checkbox" id="inlineCheckboxAll" value="option1"></th>
								<th>序号</th>
								<th>主题名称</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						 <c:forEach var="type" items="${requestScope.typeList }" varStatus="vs">
						 
							<tr>
								<td><input type="checkbox" id="inlineCheckbox${ type.typeId }" 
								value="option1"></td>
								<td scope="row">${vs.count + pageData.pageSize*(pageData.currPage-1) }</td>
								<td>${ type.typename }</td>
								<td>${ type.typedescription }</td>
								<td>
									<a href="edit_type.jsp"><span class="fa fa-pencil-square"></span> 编辑</a> &nbsp;| &nbsp;
									<a href="javascript:void(0);" name="del_link" style="display:${ display }"
									  rel="${ type.typeId }">
									<span class="fa fa-trash"></span> 删除</a>
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
			        <div class="panel-footer" id="msg"  
						style="background-color:#47BB67;display:none;color:#fff;text-align:center;">${ msg }
			  </div>
			
		</div>
	</div>
 </div>
</div>

<!-- 模态框   信息删除确认 -->
<div class="modal fade" id="deleteType">
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
				<input type="hidden" id="deleteTypeId" />
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