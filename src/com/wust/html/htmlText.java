package com.wust.html;

public class htmlText {
	//  返回页面Html携带的6位随机码
	public static String html(String code) {
		
		String html = "Email地址验证:<br/>"+ 
		"亲爱的用户，您好，这封邮件是由 爱国News 发送的。<br/>"+
		"您收到的这封邮件是爱国News进行新用户注册使用这个地址。<br/>"+
		"《账号激活声明》<br/>"+
		"你只将下面验证码输入验证码提示框即可：<h3 style='color:red;'>" + code + "</h3><br/>";
		return html;
	}
}
