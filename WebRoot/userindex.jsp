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
<title>爱国News</title>

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
	jQuery(function () {
	});
</script>

<script type="text/javascript">  
          	 function checkForm(){
        	    var text1 = document.myform.username.value;
				var text2 = document.myform.password.value;
				var text3 = document.myform.uCode.value;
				if(text1==""){
					alert("Please Enter Username!");
					return false;				
				}
				if(text2==""){
					alert("Please Enter Password!");					
					return false;
				}			
				
	          	if(text3==""){
					alert("Please Enter CheckCode!");					
					return false;
				}			
	        	return true;		
	         }	
          	 
          	 function reload(){
     			document.getElementById("image").src="<%=request.getContextPath() %>/imageServlet?date="+new Date().getTime();
     			$("#checkcode").val("");   // 将验证码清空
     		} 
                function refreshImage(obj){
                     obj.src = "image?"+Math.random();
            }
      </script>  



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <header>
         	<%@include file="user/usertop.jsp" %>
        </header>
    	<!-- HEADER END -->
        <div class="inner_copyright">Collect from <a href="http://www.cssmoban.com/" target="_blank" title="网站模板">网站模板</a></div>
        <!-- CONTENT BEGIN -->
       		<%@include file="user/usercontent.jsp" %>
       		<%@include file="user/userright.jsp" %>
    	<!-- CONTENT END -->
        
        <!-- FOOTER BEGIN -->
        <footer>
            <%@include file="user/userfooter.jsp" %>
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
                 <form name="myform" action="UserLoginServlet?method=login" method="post" onSubmit="return checkForm()">
                
                    	<div class="column">
                        	<p class="label">Username</p>
                            <div class="field"><input type="text" name="username"/></div>
                        </div>
                        
                        <div class="column">
                        	<p class="label">Password</p>
                            <div class="field"><input type="password" name="password"/></div>
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
                        
                        <div class="column button" >
                            <button type="submit" >Login</button>
                        </div>
                    	<div class="separator" style="height:12px;"></div>
		                <label><b>Checkcode:</b></label>
					    <input type="text" name="uCode" value="" class="login_input" />
					    <img src="image" alt="unclear,click to change" onclick="refreshImage(this)" style="cursor:hand;" width="90" height="30" />
            	    <div class="clearboth"></div>
            
                <div class="separator" style="height:20px;"></div> 
             	<tr>
	               	<td><p>SIGN IN AS A USER</p></td>  
	               	<td><font color="red">${requestScope.error }</font></td> 
	               </tr>
               		
              
			</form>	   
			</div>
        </div>
    </div>
    </form>
    <!-- POPUP END -->
</body>

</html>