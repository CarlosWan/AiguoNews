package com.wust.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;
 
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class JavaMailUtil {
 
	// 发件人的邮箱和密码
	public static String emailAccount = "aiguonews@foxmail.com";
	// 发件人邮箱密码（有的是授权码）
	public static String emailPassword = "htjajxghbomybdeh";
	// 发件人邮箱服务地址
	public static String emailSMTPHost = "smtp.qq.com";
	//  收件人邮箱
	public static String receiveMailAccount = "";
	/**
	 *  创建一封邮件(发件人、收件人、邮件内容)
	 * @param session
	 * @param sendMail
	 * @param receiveMail
	 * @param html
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * cc:抄送
	 * Bcc:密送
	 * To:发送
	 */
	public static  MimeMessage creatMimeMessage(Session session,String sendMail,String receiveMail,String html) throws MessagingException, IOException {
		// 1、创建一封邮件对象
		MimeMessage message = new MimeMessage(session);
		// 2、From：发件人
		message.setFrom(new InternetAddress(sendMail, "爱国News", "UTF-8"));
		// 3、To:收件人（可以增加多个收件人：抄送或者密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));
		// 4、Subject:邮件主题
		message.setSubject("邮箱验证","UTF-8");
		// 5、Content:邮件正文（可以使用Html标签）
		message.setContent(html,"text/html;charset=UTF-8");
		// 6、设置发送时间
		message.setSentDate(new Date());
		// 7、保存设置
		message.saveChanges();
		
		
		return message;
	} 
}
