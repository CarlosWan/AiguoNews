package com.wust.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			sendEmail("1120015191@qq.com","����Newsע����֤��", "��л��ע�ᰮ��News��");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
     * �ʼ����ͳ���
     *
     * @param to
     *            ������
     * @param subject
     *            �ʼ�����
     * @param content
     *            �ʼ�����
     * @throws Exception
     * @throws MessagingException
     */
    public static void sendEmail(String to, String subject, String content) throws Exception, MessagingException {
        String host = "smtp.qq.com";
        String address = "aiguonews@foxmail.com";
        String from = "aiguonews@foxmail.com";
        String password = "htjajxghbomybdeh";// ����
        if ("".equals(to) || to == null) {
            to = "1120015191@qq.com";
        }
        String port = "25";
        SendEmaill(host, address, from, password, to, port, subject, content);
    }

    /**
     * �ʼ����ͳ���
     *
     * @param host
     *            �ʼ������� �磺smtp.qq.com
     * @param address
     *            �����ʼ��ĵ�ַ �磺545099227@qq.com
     * @param from
     *            ���ԣ� wsx2miao@qq.com
     * @param password
     *            ������������ htjajxghbomybdeh
     * @param to
     *            ������
     * @param port
     *            �˿ڣ�QQ:25��
     * @param subject
     *            �ʼ�����
     * @param content
     *            �ʼ�����
     * @throws Exception
     */
    public static void SendEmaill(String host, String address, String from, String password, String to, String port, String subject, String content) throws 

Exception {
        Multipart multiPart;
        String finalString = "";

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", address);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
       
        Session session = Session.getDefaultInstance(props, null);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(finalString.getBytes(), "text/plain"));
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setDataHandler(handler);
       

        multiPart = new MimeMultipart();
        InternetAddress toAddress;
        toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
       
        message.setSubject(subject);
        message.setContent(multiPart);
        message.setText(content);

       
        Transport transport = session.getTransport("smtp");
       
        transport.connect(host, address, password);
       
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
       

    }



}
