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
 
	// �����˵����������
	public static String emailAccount = "aiguonews@foxmail.com";
	// �������������루�е�����Ȩ�룩
	public static String emailPassword = "htjajxghbomybdeh";
	// ��������������ַ
	public static String emailSMTPHost = "smtp.qq.com";
	//  �ռ�������
	public static String receiveMailAccount = "";
	/**
	 *  ����һ���ʼ�(�����ˡ��ռ��ˡ��ʼ�����)
	 * @param session
	 * @param sendMail
	 * @param receiveMail
	 * @param html
	 * @return
	 * @throws MessagingException
	 * @throws IOException 
	 * cc:����
	 * Bcc:����
	 * To:����
	 */
	public static  MimeMessage creatMimeMessage(Session session,String sendMail,String receiveMail,String html) throws MessagingException, IOException {
		// 1������һ���ʼ�����
		MimeMessage message = new MimeMessage(session);
		// 2��From��������
		message.setFrom(new InternetAddress(sendMail, "����News", "UTF-8"));
		// 3��To:�ռ��ˣ��������Ӷ���ռ��ˣ����ͻ������ͣ�
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));
		// 4��Subject:�ʼ�����
		message.setSubject("������֤","UTF-8");
		// 5��Content:�ʼ����ģ�����ʹ��Html��ǩ��
		message.setContent(html,"text/html;charset=UTF-8");
		// 6�����÷���ʱ��
		message.setSentDate(new Date());
		// 7����������
		message.saveChanges();
		
		
		return message;
	} 
}
