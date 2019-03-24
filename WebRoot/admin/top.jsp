<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div id="myNavBar" class="navbar nav-default" style="background-color:#000;padding-right:30px;margin-bottom: 5px;" role="navigation">
			<div class="navbar-header" style="line-height: 100%;">
				<span class="h3" style="color:#fff;"><img src="img/earth.png" style="width:48px;height:48px;padding-top:2px;"> 新闻中国后台管理系统</span>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="userindex.jsp" style="color:#fff">返回新闻首页</a>
				</li>
				<li>
					<a href="#" class="text" style="color:#fff">
						<span class="fa fa-user-circle"></span>&nbsp;
						<span style="color:#fff;font-weight: bold;">${sessionScope.LoginUser.username } </span> &nbsp;欢迎登录
					</a>
				</li>
				<li>
					<a href="#" id="myclock" class="text" style="color:#fff"></a>
				</li>

				<li>
					<a href="admin/index.jsp" style="color:#fff">
						<span class="glyphicon glyphicon-log-out"></span> 退出
					</a>
				</li>
			</ul>
		</div>