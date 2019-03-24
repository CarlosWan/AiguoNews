<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
        	    var text1 = document.myform.newusername.value;
				var text2 = document.myform.newpassword.value;
				
				if(text1==""){
					alert("Please Enter Username!");
				
					return false;				
				}
				if(text2==""){
					alert("Please Enter Password!");
					
					return false;
				}
				
					alert("Welcome To 爱国News");
				return true;
			 function reload(){
					document.getElementById("image").src="<%=request.getContextPath() %>/imageServlet?date="+new Date().getTime();
					$("#checkcode").val("");   // 将验证码清空
			  } 
	            function refreshImage(obj){
	            obj.src = "image?"+Math.random();
		      }
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
       		<div class="sidebar">
                    	<div class="block_subscribes_sidebar" align="center">
                    	<a href="#login" class="open_popup">
                    	<button style="height: 50px;width:160px;background:red";>
                        	 <li class="current"><font size="4" color="white">Welcome</font></li>
                        </button>
                        </a>
                        </div>
                        
                      <div class="separator" style="height:31px;"></div>
                        
                        <div class="block_popular_posts">
                        	<h4>Popular Posts</h4>
                            
                        	<div class="article">
								<div class="pic">
									<a href="#" class="w_hover">
										<img src="images/pic_popular_post_1.jpg" alt="" />
										<span></span>
									</a>
								</div>
                                        
								<div class="text">
									<p class="title"><a href="#">Packages and web page editors their default text.</a></p>
									<div class="date"><p>11 July, 2012</p></div>
                                    <div class="icons">
                                    	<ul>
                                        	<li><a href="#" class="views">41</a></li>
                                            <li><a href="#" class="comments">22</a></li>
                                        </ul>
                                    </div>
								</div>
							</div>
							<div class="line_3"></div>
                            
                            <div class="article">
								<div class="pic">
									<a href="#" class="w_hover">
										<img src="images/pic_popular_post_2.jpg" alt="" />
										<span></span>
									</a>
								</div>
                                        
								<div class="text">
									<p class="title"><a href="#">Web page editors their default model text, and a search for.</a></p>
									<div class="date"><p>07 July, 2012</p></div>
                                    <div class="icons">
                                    	<ul>
                                        	<li><a href="#" class="views">24</a></li>
                                            <li><a href="#" class="comments">16</a></li>
                                        </ul>
                                    </div>
								</div>
							</div>
							<div class="line_3"></div>
                            
                            <div class="article">
								<div class="pic">
									<a href="#" class="w_hover">
										<img src="images/pic_popular_post_3.jpg" alt="" />
										<span></span>
									</a>
								</div>
                                        
								<div class="text">
									<p class="title"><a href="#">Editors their default model text, and a search uncover.</a></p>
									<div class="date"><p>05 July, 2012</p></div>
                                    <div class="icons">
                                    	<ul>
                                        	<li><a href="#" class="views">33</a></li>
                                            <li><a href="#" class="comments">25</a></li>
                                        </ul>
                                    </div>
								</div>
							</div>
							<div class="line_2"></div>
                        </div>
                        
                        <div class="separator" style="height:31px;"></div>
                        
                        <div class="block_popular_stuff">
                        	<h4>Popular Video</h4>
                            
                            <div class="content">
                            	<a href="#" class="view_all">Show all video</a>
                            	<div class="media"><a href="http://www.youtube.com/watch?v=ySIvism2af8" class="general_pic_hover play no_fx" data-rel="prettyPhoto" title="Popular Video"><img src="images/pic_pop_video.jpg" alt="" /></a></div>
                                <p><a href="blog_post_w_video.html">Publishing packages and web page editors their default model.</a> <img src="images/icon_video.gif" alt="" /></p>
                                <p class="date">11 July, 2012</p>
                            </div>
                            
                            <div class="info">
                            	<ul>
                                	<li class="comments"><a href="#">115</a></li>
                                    <li class="views"><a href="#">220</a></li>
                                </ul>
                            </div>
                            
                            <div class="clearboth"></div>
                            
                            <div class="line_2"></div>
                        </div>
                        
                        <div class="separator" style="height:31px;"></div>
                        
                        <div class="block_calendar">
                        	<h4>Calendar</h4>
                            
                            <div class="calendar" id="calendar_sidebar">
                            </div>
                            
                            <script type="text/javascript">
								var today = new Date();
								var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
								$('#calendar_sidebar').DatePicker({
									flat : true,
									date : date,
									calendars : 1,
									starts : 1,
									locale : {
										days : ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
										daysShort : ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
										daysMin : ['S', 'M', 'T', 'W', 'T', 'F', 'S', 'S'],
										months : ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
										monthsShort : ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
										weekMin : 'wk'
									}
								});
							</script>
                            
                            <div class="line_2"></div>
                        </div>
                        
                        <div class="separator" style="height:31px;"></div>
                        
                        <div class="block_twitter_widget">
                        	<h4>Twitter Widget</h4>
                            <div class="lnk_follow"><a href="#" target="_blank">Follow on Twitter</a></div>
                            
                            <div class="tweet">
                            	<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
								<script type="text/javascript" src="layout/plugins/tweet/tweet.widget.js"></script>
                                <script type="text/javascript">
                                    // ('YOUR USERNAME','NUMBER OF POSTS');
                                    AddTweet('Web_Visions',1);
                                </script>
                            </div>
                            
                            <div class="line_2"></div>
                        </div>
                        
                        <div class="separator" style="height:31px;"></div>
                        
                        <div class="block_popular_stuff">
                        	<h4>Popular Photo</h4>
                            
                            <div class="content">
                            	<a href="#" class="view_all">Show all photos</a>
                            	<div class="media"><a href="images/pic_pop_photo_big.jpg" class="general_pic_hover zoom no_fx" data-rel="prettyPhoto" title="Popular Photo"><img src="images/pic_pop_photo.jpg" alt="" /></a></div>
                                <p><a href="blog_post_w_slider.html">Editors their default model text, and a search for will uncover many.</a> <img src="images/icon_photo.gif" alt="" /></p>
                                <p class="date">11 July, 2012</p>
                            </div>
                            
                            <div class="info">
                            	<ul>
                                	<li class="comments"><a href="#">100</a></li>
                                    <li class="views"><a href="#">134</a></li>
                                </ul>
                            </div>
                            
                            <div class="clearboth"></div>
                            
                            <div class="line_2"></div>
                        </div>
                        
                      	<div class="separator" style="height:31px;"></div>
                        
                        <div class="block_newsletter">
                        	<h4>Newsletter</h4>
                            
                            <form action="#" />
                            	<div class="field"><input type="text" class="w_def_text" title="Enter Your Email Addres" /></div>
                                <input type="submit" class="button" value="Subscribe" />
                                
                                <div class="clearboth"></div>
                            </form>
                        </div>
                        
                    </div>
                    
                	<div class="clearboth"></div>
                </div>
            </div>
        </div>

    	<!-- CONTENT END -->
        
        <!-- FOOTER BEGIN -->
        <footer>
            <%@include file="user/userfooter.jsp" %>
        </footer>
        <!-- FOOTER END -->
    </div>
    
    <!-- POPUP BEGIN -->
    <form name="myform" method="post" onSubmit="return checkForm()">
    <div id="overlay"></div>
    <div id="login" class="block_popup">
    	<div class="popup">
        	<a href="#" class="close">Close</a>
            
            <div class="content">
            	<div class="title"><p>Enter the site</p></div>
                
                <div class="form">
                	<form action="#" />
                    	<div class="column">
                        	<p class="label">Username</p>
                            <div class="field"><input type="text" name="newusername"/></div>
                        </div>
                        
                        <div class="column">
                        	<p class="label">Password</p>
                            <div class="field"><input type="password" name="newpassword"/></div>
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
                        
                        <div class="clearboth"></div>
                        
                    </form>
                </div>
                 <div>
                	<label>Checkcode:</label>
				    <input type="text" name="uCode" value="" class="login_input" />
				    <img src="image" alt="unclear,click to change" onclick="refreshImage(this)" style="cursor:hand;" width="80" height="20" />
                </div>
                
                <div class="subtitle"><p>SIGN IN AS A USER</p></div>
                 
            </div>    
         </div>
    </div>
    </form>
    <!-- POPUP END -->
</body>

</html>