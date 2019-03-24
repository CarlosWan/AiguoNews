<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">
<title>BusinessNews - Business News - News Name</title>

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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <header>
           <%@include file="user/usertop.jsp" %>
        </header>
    	<!-- HEADER END -->
        
        <!-- CONTENT BEGIN -->
        <div id="content" class="right_sidebar">
        	<div class="inner">
            	<div class="general_content">
                	<div class="main_content">
                    	<div class="block_breadcrumbs">
                        	<div class="text"><p>You are here:</p></div>
                            
                            <ul>
                            	<li><a href="index.html">Home</a></li>
                                <li><a href="business.html">Business News</a></li>
                                <li>News Name</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:30px;"></div>
                        
                        <article class="block_single_news">
                        	<div class="f_pic"><a href="#"><img src="images/pic_news_post_1.jpg" alt="" /></a></div>
                          <p class="title"><a href="#">Words which don't look even slightly.</a></p>
                            <p class="subtitle">Many variations of passages of available, but the majority have suffered alteration in some form. Humour, or randomised words which don't look even slightly believable.</p>
                            
                            <div class="info">
                                <div class="date"><p>15 July, 2012</p></div>
                                <div class="author"><p>By: <a href="#">John Doe</a></p></div>
                                    
                            	<div class="r_part">
                                	<div class="category"><p><a href="#">Life</a></p></div>
                                    <a href="#" class="views">220</a>
                                    <a href="#" class="comments">25</a>
                                </div>
                            </div>
                            
                            <div class="content">
                            	<p>There are many variations of passages of available, but the majority have suffered alteration in some form, by injected humour, or <b>randomised words</b> which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't <a href="#" class="lnk_blue"><b>anything embarrassing hidden</b></a> in the middle of text. All the generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over words, combined with a handful of model sentence structures, to generate which looks reasonable. Theherefore <b>always free from repetition</b>, injected humour words etc.</p>
                                <blockquote class="full">Going to use a passage you need to be sure there isn't anything embarrassing hidden in the middle of text. established fact that a reader will be distracted by the readable content.</blockquote>
                                <p>Available, but the majority have suffered alteration.By injected humour, or randomised words which don't look even slightly believable. If you are <a href="#"><b>going to use a passage</b></a> of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. Established fact that a reader will be distracted by the readable content of a page when <b>looking at its layout</b>. The point of using Lorem Ipsum is that it has a more-or-less.</p>
                            </div>
                        </article>
                        
                        <div class="separator" style="height:4px;"></div>
                        
                        <div class="block_post_tags">
                        	<p>Tags: <a href="#">business,</a><a href="#">stock market</a></p>
                        </div>
                        
                        <div class="block_post_social">
                        	<h4><span>B</span></h4>
                            
                            <section class="rating">
                            	<p class="title"><span>Rating</span></p>
                                
                                <ul>
                                	<li><span>1024</span>views</li>
                                    <li><span>4</span>comments</li>
                                </ul>
                            </section>
                            
                            <section class="subscribe">
                            	<p class="title"><span>Subscribe</span></p>
                                <a href="#">Subscribe to comments</a>
                            </section>
                            
                            <section class="recommend">
                            	<p class="title"><span>recommend to friends</span></p>
                                
                                <ul>
                                	<li><a href="http://www.facebook.com/share.php?u=http://google.com" target="_blank"><img src="images/button_social_1.png" alt="" /></a></li>
                                    <li><a href="https://twitter.com/share?text=I like BusinessNews Template and You?" target="_blank"><img src="images/button_social_2.png" alt="" /></a></li>
                                    <li><a href="https://plusone.google.com/_/+1/confirm?url=http://google.com" target="_blank"><img src="images/button_social_3.png" alt="" /></a></li>
                                    <li><a href="http://pinterest.com/pin/create/button/?url=http://google.com" target="_blank"><img src="images/button_social_4.png" alt="" /></a></li>
                                </ul>
                            </section>
                            
                            <div class="clearboth"></div>
                        </div>
                        
                        <div class="line_2" style="margin:22px 0px 29px;"></div>
                        
                        <div class="block_related_posts">
                        	<h3>Related Posts</h3>
                            
                            <div class="block_main_news">
                            	<article class="block_news_post">
                                    <div class="f_pic"><a href="#" class="general_pic_hover scale_small"><img src="images/pic_main_news_9.jpg" alt="" /></a></div>
                                  <p class="category"><a href="#">photography</a></p>
                                    <p class="title"><a href="#">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                                <article class="block_news_post">
                                    <div class="f_pic"><a href="#" class="general_pic_hover scale_small"><img src="images/pic_main_news_4.jpg" alt="" /></a></div>
                                  <p class="category"><a href="#">sport</a></p>
                                    <p class="title"><a href="#">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                                <article class="block_news_post">
                                    <div class="f_pic"><a href="#" class="general_pic_hover scale_small"><img src="images/pic_main_news_6.jpg" alt="" /></a></div>
                                  <p class="category"><a href="#">business</a></p>
                                    <p class="title"><a href="#">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                                <article class="block_news_post">
                                    <div class="f_pic"><a href="#" class="general_pic_hover scale_small"><img src="images/pic_main_news_13.jpg" alt="" /></a></div>
                                  <p class="category"><a href="#">video</a></p>
                                    <p class="title"><a href="#">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                            	<div class="clearboth"></div>
                            </div>
                        </div>
                        
                        <div class="line_2" style="margin:5px 0px 30px;"></div>
                        
                        <div class="block_comments_type_2">
                        
                        	<h3>Comments</h3>
                        	<div class="comment">
                            	<div class="userpic"><a href="#"><img src="images/ava_default_1.jpg" alt="" /></a></div>
                                
                                <div class="comment_wrap">
                                    <div class="name"><p><a href="#">John Doe</a></p></div>
                                    <div class="date"><p>Febr 16, 2012 at 4:43 pm</p></div>
                                    <div class="content">
                                        <p>Established fact that a reader will be distracted by the readable content of a page.</p>
                                    </div>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="#"><img src="images/ava_default_1.jpg" alt="" /></a></div>
                                
                                <div class="comment_wrap">
                                    <div class="name"><p><a href="#">Sara Jonson</a></p></div>
                                    <div class="date"><p>Febr 16, 2012 at 4:43 pm</p></div>
                                    <div class="content">
                                        <p>Established fact that a reader will be distracted by the readable content of a page. When looking at its layout. The point of using is that it has a more-or-less normal distribution of letters.</p>
                                    </div>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="#"><img src="images/ava_default_1.jpg" alt="" /></a></div>
                                
                                <div class="comment_wrap">
                                    <div class="name"><p><a href="#">Mark Defo</a></p></div>
                                    <div class="date"><p>Febr 16, 2012 at 4:43 pm</p></div>
                                    <div class="content">
                                        <p>Page when looking at its layout. The point of usinghas a more-or-less normal distribution.</p>
                                    </div>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
										<c:forEach var="Comment" items="${ requestScope.commentList }" varStatus="vs">
										
										<div class="comment">
		                            	<div class="userpic"><a href="#"><img src="images/ava_default_1.jpg" alt="" /></a></div>
		                                 <div class="comment_wrap">
		                                    <div class="name"><p><a href="#">${ Comment.userId }</a></p></div>
		                                    <div class="date"><p>${ Comment.date }</p></div>
		                                    <div class="content">
		                                        <p>${ Comment.content }</p>
		                                    </div>
		                                </div>
		                                <div class="clearboth"></div>
		                                <div class="line_3"></div>
		                            	</div>
										</c:forEach>
									
                        
                        <div class="separator" style="height:30px;"></div>
                        
                        <!-- 评论框 -->
                        	<form class="w_validation" action="admin/CommentServlet?method=addComment" method="post" onSubmit="return checkForm()"/>
                          		<p>UserName<span>*</span></p>
                            	<div class="field"><input type="text" class="req" name="userId" /></div>
                                
                          		<p>Comment</p>
                                <div class="textarea"  ><textarea rows="6" cols="85" name="content" id="comment"></textarea></div>
                                
                                 <div class="separator" style="height:18px;"></div>
                                <input type="submit" class="general_button" value="Post comment" />
                            </form>
                        </div>
                        
                    </div>
                    
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