<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-2 col-md-2 column">
	<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
		<li>
			<a href="admin/manager_index.jsp" >
				<i class="glyphicon glyphicon-home"></i> 首页
			</a>
		</li>
		<li>
		     <c:choose>
		     <c:when test="${ param.paramName eq 'typeManage' or param.paramName eq 'newsManage'
		      or param.paramName eq 'noticeManage' or param.paramName eq 'commentManage'}">
		     
			<a href="#functionManage" id="collapseListGroupHeading1" class="nav-header collapsed" data-toggle="collapse">
				<i class="glyphicon glyphicon-cog"></i> 功能管理
				<span class="pull-right glyphicon glyphicon-chevron-up"></span>
			</a>
			<div id="functionManage" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
				<ul class="nav nav-pills nav-stacked secondmenu">
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'typeManage'}">
						      <a href="admin/typeAdmin?method=gotoTypeManage" style="color:#D58512"><i class="fa fa-user"></i>主题管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/typeAdmin?method=gotoTypeManage"><i class="fa fa-user"></i> 主题管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'newsManage'}">
						      <a href="admin/NewsServlet?method=gotoNewsManage" style="color:#D58512"><i class="fa fa-user"></i>新闻管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/NewsServlet?method=gotoNewsManage"><i class="fa fa-user"></i> 新闻管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'noticeManage'}">
						      <a href="admin/noticeAdmin?method=gotoNoticeManage" style="color:#D58512"><i class="fa fa-user"></i>公告管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/noticeAdmin?method=gotoNoticeManage"><i class="fa fa-user"></i> 公告管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						<c:choose>
							<c:when test="${ param.paramName eq 'commentManage'}">
								<a href="admin/CommentServlet?method=gotoCommentManage" style="color:#D58512"><i class="fa fa-user"></i> 评论管理</a>	
							</c:when>
							<c:otherwise>
								<a href="admin/CommentServlet?method=gotoCommentManage"><i class="fa fa-user"></i> 评论管理</a>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
			</div>
			 </c:when>
			 <c:otherwise>
			 <a href="#functionManage" id="collapseListGroupHeading1" class="nav-header collapsed" data-toggle="collapse">
				<i class="glyphicon glyphicon-cog"></i> 功能管理
				<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
			<div id="functionManage" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
				<ul class="nav nav-pills nav-stacked secondmenu">
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'typeManage'}">
						      <a href="admin/typeAdmin?method=gotoTypeManage" style="color:#D58512"><i class="fa fa-user"></i>主题管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/typeAdmin?method=gotoTypeManage"><i class="fa fa-user"></i> 主题管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						<a href="admin/NewsServlet?method=gotoNewsManage"><i class="fa fa-newspaper-o"></i> 新闻管理</a>
					</li>
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'noticeManage'}">
						      <a href="admin/noticeAdmin?method=gotoNoticeManage" style="color:#D58512"><i class="fa fa-user"></i>公告管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/noticeAdmin?method=gotoNoticeManage"><i class="fa fa-user"></i> 公告管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						<c:choose>
							<c:when test="${ param.paramName eq 'commentManage'}">
								<a href="admin/CommentServlet?method=gotoCommentManage" style="color:#D58512"><i class="fa fa-user"></i> 评论管理</a>	
							</c:when>
							<c:otherwise>
								<a href="admin/CommentServlet?method=gotoCommentManage"><i class="fa fa-user"></i> 评论管理</a>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
			</div>
			</c:otherwise>
			</c:choose>
		</li>
		<li>
		    <c:choose>
		    <c:when test="${param.paramName eq 'userManage' or param.paramName eq 'systemLog'}">
			<a href="#systemSetting" id="collapseListGroupHeading2" class="nav-header collapsed" data-toggle="collapse">
				<i class="glyphicon glyphicon-list"></i> 系统管理
				<span class="pull-right glyphicon glyphicon-chevron-up"></span>
			</a>
			<div id="systemSetting" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading2">
				<ul class="nav nav-pills nav-stacked secondmenu">
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'userManage'}">
						      <a href="admin/userAdmin?method=gotoUserManage" style="color:#D58512"><i class="fa fa-user"></i>用户管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/userAdmin?method=gotoUserManage"><i class="fa fa-user"></i> 用户管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						 <c:choose>
						   <c:when test="${param.paramName eq 'systemLog'}">
						      <a href="#"><i class="fa fa-edit" style="color:#D58512"></i> 系统日志</a>
						   </c:when>
						   <c:otherwise>
						     <a href="#"><i class="fa fa-edit"></i> 系统日志</a>
						     </c:otherwise>
						   </c:choose>
					</li>
				</ul>
			</div>
			</c:when>
			<c:otherwise>
			 <a href="#systemSetting" id="collapseListGroupHeading2" class="nav-header collapsed" data-toggle="collapse">
				<i class="glyphicon glyphicon-list"></i> 系统管理
				<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
			<div id="systemSetting" class="panel-collapse collapse " role="tabpanel" aria-labelledby="collapseListGroupHeading2">
				<ul class="nav nav-pills nav-stacked secondmenu">
					<li>
						<c:choose>
						   <c:when test="${param.paramName eq 'userManage'}">
						      <a href="admin/userAdmin?method=gotoUserManage" style="color:#D58512"><i class="fa fa-user"></i>用户管理</a>
						   </c:when>
						   <c:otherwise>
						     <a href="admin/userAdmin?method=gotoUserManage"><i class="fa fa-user"></i> 用户管理</a>
						     </c:otherwise>
						   </c:choose>
					</li>
					<li>
						 <c:choose>
						   <c:when test="${param.paramName eq 'systemLog'}">
						      <a href="#"><i class="fa fa-edit" style="color:#D58512"></i> 系统日志</a>
						   </c:when>
						   <c:otherwise>
						     <a href="#"><i class="fa fa-edit"></i> 系统日志</a>
						     </c:otherwise>
						   </c:choose>
					</li>
				</ul>
			</div>
     </c:otherwise>
       </c:choose>
       </li>
       </ul>
       </div>
