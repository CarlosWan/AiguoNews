<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.lang.Math" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<title>爱国News - Registration</title>
 <base href="<%=basePath%>">
<meta name="keywords" content="" />
<meta name="description" content="" />

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />

<!--[if lt IE 9]>
<script type="text/javascript" src="layout/plugins/html5.js"></script>
<![endif]-->

<link rel="stylesheet" href="layout/style.css" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=PT+Sans:400,700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="layout/js/jquery.js"></script>

<!-- PrettyPhoto start -->
<link rel="stylesheet" href="layout/plugins/prettyphoto/css/prettyPhoto.css" type="text/css" />
<script type="text/javascript" src="layout/plugins/prettyphoto/jquery.prettyPhoto.js"></script>
<!-- PrettyPhoto end -->

<!-- jQuery tools start -->
<script type="text/javascript" src="layout/plugins/tools/jquery.tools.min.js"></script>
<!-- jQuery tools end -->

<!-- Calendar start -->
<link rel="stylesheet" href="layout/plugins/calendar/calendar.css" type="text/css" />
<script type="text/javascript" src="layout/plugins/calendar/calendar.js"></script>
<!-- Calendar end -->

<!-- ScrollTo start -->
<script type="text/javascript" src="layout/plugins/scrollto/jquery.scroll.to.min.js"></script>
<!-- ScrollTo end -->

<!-- MediaElements start -->
<link rel="stylesheet" href="layout/plugins/video-audio/mediaelementplayer.css" />
<script src="layout/plugins/video-audio/mediaelement-and-player.js"></script>
<!-- MediaElements end -->

<!-- FlexSlider start -->
<link rel="stylesheet" href="layout/plugins/flexslider/flexslider.css" type="text/css" />
<script type="text/javascript" src="layout/plugins/flexslider/jquery.flexslider-min.js"></script>
<!-- FlexSlider end -->

<!-- iButtons start -->
<link rel="stylesheet" href="layout/plugins/ibuttons/css/jquery.ibutton.css" type="text/css" />
<script type="text/javascript" src="layout/plugins/ibuttons/lib/jquery.ibutton.min.js"></script>
<!-- iButtons end -->

<!-- jQuery Form Plugin start -->
<script type="text/javascript" src="layout/plugins/ajaxform/jquery.form.js"></script>
<!-- jQuery Form Plugin end -->

<script type="text/javascript" src="layout/js/main.js"></script>
	
 <script type="text/javascript"> 
            var flag = {
                "email":false,
                "username":false,
                "telephone":false,
                "password":false,
            };

            $(function(){
                // email验证  
                $("#txtEmail").blur(function(){
                    var email = $(this).val();

                    var pattern=/\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/; 
                    if(!pattern.test(email)){
                        $("#email\\.info").html("Email格式不正确");
                        return;
                    }else{
                        $("#email\\.info").html("");
                        flag.email = true;
                    }

                    // 邮箱重复校验
                });

                $("#txtUserName").blur(function(){
                    // 用户名校验
                    var username = $(this).val();

                    // 校验规则，可调整
                    var pattern = /\b(^['A-Za-z0-9]{4,20}$)\b/;
                    if(!pattern.test(username)){
                        $("#username\\.info").html("用户名不合法");
                        return;
                    }else{
                        $("#username\\.info").html("");
                        flag.username = true;
                    }
                });

                $("#txtTelephone").blur(function(){
                    // 电话号码校验
                    var nickname = $(this).val();

                    // 校验规则，可调整
                    var pattern = /\b(^1\d{10}$)\b/;
                    if(!pattern.test(nickname)){
                        $("#telephone\\.info").html("电话号码不合法");
                        return;
                    }else{
                        $("#telephone\\.info").html("");
                        flag.telephone = true;
                        return;
                    }
                });

                // 密码校验
                $("#txtPassword").blur(function(){
                    var password=$(this).val(); 

                    var pattern = /\b(^['A-Za-z0-9]{4,20}$)\b/; 
                    if (!pattern.test(password)) { 
                        $("#password\\.info").html("密码格式不正确"); 
                        return;    
                    }else{ 
                        $("#password\\.info").html(""); 
                        //flag.password=true; 
                        return; 
                    } 
                });


                // 密码重复校验
                $("#txtRepeatPass").blur(function(){
                    var repeatPass = $(this).val();

                    var pattern = /\b(^['A-Za-z0-9]{4,20}$)\b/; 
                    if (repeatPass!=$("#txtPassword").val()) { 
                        $("#repeatPass\\.info").html("两次密码输入不一致"); 
                        return;
                    }else{
                        $("#repeatPass\\.info").html(""); 
                        flag.password = true;
                        return;
                    }
                });

                
	//qq邮箱验证码
                $(function(){
			     	$("#btn").click(function(){
			     		if($(".email").val()){
			     					$.ajax({
			     						type:"POST",
			     						url :"SendEmailServlet?random"+Math.random(),
			     						data:{
			     							email:$(".email").val(),
			     						},
			     						success:function(data){
			     							alert("success");
			     						},
			     					})
			     		}else{
			     			alert("fail");
			     			$("#notice").html("请填写邮箱");
			     			setTimeout(function(){
			     				$("#notice").hide();
			     			},2000);
			     		}
			     	});
     	//  判断用户是否可以注册
		     	$("#submit").click(function(){
		     		if($(".email").val()){
		     			$("#Form").submit();
		     		}else{   //  如果没有值
		     			$("#notice").html("请完整信息");
		     			setTimeout(function(){
		     				$("#notice").hide();
		     			},2000);
		     		}
		     	});
		     });

                $("#form").submit(function(){
                    var ok = flag.email&&flag.password&&flag.nickname;
                    if(ok==false){
                        alert("表单项正在检测或存在错误");
                        history.back();
                        return false;
                    }
                    return true;
                });

            });
            
        </script> 



</html>


		    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <header>
          <%@ include file="user/usertop.jsp" %>
        </header>
    	<!-- HEADER END -->
        
        <!-- CONTENT BEGIN -->
        <div id="content" class="">
        	<div class="inner">
            	<div class="general_content">
                	<div class="main_content">
                    	<div class="block_breadcrumbs">
                        	<div class="text"><p>You are here:</p></div>
                            
                            <ul>
                            	<li><a href="index.html">Home</a></li>
                                <li>Registration</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:28px;"></div>
                        
                        <p class="general_title"><span>Join The NEWS</span></p>
                        <div class="separator" style="height:39px;"></div>
                        
                        <div class="block_registration">
                        	 <form class="form-horizontal" action="${pageContext.request.contextPath}/RegistServlet" method="post" id="form" >
							
						      <div class="label" style="text-align: left;"><p>Username:<span style="color:#ff0000;">*</span></p></div>
						      <div class="col-sm-6"><input type="text" class="form-control" id="txtUserName" name="username"
						            placeholder="请输入用户名" required>
						      <span id="username.info" style="color:red"></span>
						      </div>
						       <div class="separator" style="height:12px;"></div>
						      
						      <div class="label" style="text-align: left;"><P>Realname:<span style="color:#ff0000;">*</span></P></div>
						      <div class="col-sm-6">
						         <input type="text" class="form-control" id="txtRealName" name="realname"
						            placeholder="请输入昵称" required>
						          <span id="realname.info" style="color:red"></span>
						      </div>
						   	   <div class="separator" style="height:12px;"></div>
						      
						       <div class="label" style="text-align: left;"><P>Password:<span style="color:#ff0000;">*</span></P></div>
						       <div class="col-sm-6">
						          <input type="password" class="form-control" name="password" id="txtPassword"
						            placeholder="请输入密码" required>
						           <span id="password.info" style="color:red"></span> 
						       </div>
							   <div class="separator" style="height:12px;"></div>
							  
						        <div class="label" style="text-align: left;"><P>Repeat Password:<span style="color:#ff0000;">*</span></P></div> 
						        <div class="col-sm-6">
						          <input type="password" class="form-control" name="repeatPass" id="txtRepeatPass"
						            placeholder="请再次输入密码" required>
						           <span id="repeatPass.info" style="color:red"></span> 
						       
							 	 <div class="separator" style="height:12px;"></div>

							      <div class="label" style="text-align: left;"><P>Telephone:</P></div>
							      <div class="col-sm-6">
							         <input name="telephone" type="text" class="form-control" id="txtTelephone" 
							            placeholder="请输入电话号码" required>
							          <span id="telephone.info" style="color:red"></span> 
							      </div>
								  <div class="separator" style="height:12px;"></div>

								   
							     <div class="label" style="text-align: left;"><p>Gender:<span style="color:#ff0000;">*</span></p></div>
						         <div class="col-sm-6" >
										<input type="radio" class="from-control" id="rdoGender" name="gender" value="男" checked >
										<img src="img/m.gif">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" class="from-control" id="rdoGender" name="gender" value="女">
										<img src="img/f.gif">
									</div>
							       
								<div class="separator" style="height:12px;"></div>

							      <div class="label" style="text-align: left;"><P>E-Mail:<span style="color:#ff0000;">*</span></P></div>
							         <div class="col-sm-6" >
							         <input name="email" type="text" class="email" id="txtEmail" 
							            placeholder="请输入注册邮箱" required>
							          <span id="email.info" style="color:red"></span> 
							      </div>
							      
								 <div class="separator" style="height:12px;"></div>

								   <td>验证码:</td>
									<td><input type="text" name="code" class="code"></td>
									</tr>
									<span id="notice" class="hide">请先完成邮箱验证</span>
									<td>
										<input type="button" class="btn" id="btn" value="发送邮箱验证码"/><br/>
									</td>
									

									<div class="clearboth"></div>
									<div class="separator" style="height:32px;"></div>
	                                <p class="info_text">By clicking on the button "Register" you agree to be the terms of service (<a href="registration.jsp">User Agreement</a>)</p>
	                                <p class="info_text"><input type="submit" class="general_button" value="Register On Site" /></p>
									
                           
							</form>
							   
                        
                        <div class="line_3" style="margin:42px 0px 0px;"></div>
                        
                    </div>
                    
                	<div class="clearboth"></div>
                </div>
            </div>
        </div>
    	<!-- CONTENT END -->
        
        <!-- FOOTER BEGIN -->
        <footer>
           <%@ include file="user/userfooter.jsp" %>
        </footer>
        <!-- FOOTER END -->
    </div>
    
    <!-- POPUP BEGIN -->
    <div id="overlay"></div>
    <div id="login" class="block_popup">
    	<div class="popup">
        	<a href="#" class="close">Close</a>
            
            <div class="content">
            	<div class="title"><p>Enter the site</p></div>
                
                <div class="form">
                	<form action="#" />
                    	<div class="column">
                        	<p class="label">Login</p>
                            <div class="field"><input type="text" /></div>
                        </div>
                        
                        <div class="column">
                        	<p class="label">Password</p>
                            <div class="field"><input type="password" /></div>
                        </div>
						
						<div class="column_2">
                            <div class="remember">
                            	<div class="checkbox"><input type="checkbox" /></div>
                                <div class="remember_label"><p>Remember me</p></div>
                            </div>
                        </div>
                        
                        <div class="column_2">
                            <p class="forgot_pass"><a href="#">Forgot password?</a></p>
                        </div>
                        
                        <div class="column button">
                            <a href="#" class="enter"><span>Login</span></a>
                        </div>
                        
                        <div class="clearboth"></div>
                    </form>
                </div>
                
                <div class="subtitle"><p>SIGN IN AS A USER</p></div>
                
                <div class="fb_button"><a href="#"><img src="images/button_fb_login.png" alt="" /></a></div>
                <div class="text"><p>Use your account on the social network Facebook, to create a profile on BusinessPress</p></div>
            </div>
        </div>
    </div>
    <!-- POPUP END -->
</body>

</html>