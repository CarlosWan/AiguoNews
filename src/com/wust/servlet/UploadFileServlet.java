package com.wust.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class UploadFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filename = "";
		
		SmartUpload su = new SmartUpload();
		// ��ʼ��SmartUpload����
		su.initialize(this.getServletConfig(), request, response);
		com.jspsmart.upload.File file = null;
		// �����ϴ�����
		String allowed = "gif,jpg,doc,rar";
		// �����ϴ�����
		String denied = "jsp,asp,php,aspx,html,htm,exe,bat";
		com.jspsmart.upload.Request req = null;
		// �����ϴ��ļ���С
		int file_size = 2 * 1024 * 1024; //2mb
		String exceptionMsg = null;
		int i = 0;
		try {
			// ���������ϴ��ļ�����
			su.setAllowedFilesList(allowed);
			// �������ϴ��ļ�����
			su.setDeniedFilesList(denied);
			// �����ļ��������
			su.setMaxFileSize(file_size);
			su.setCharset("GBK");
			// ִ���ϴ�
			su.upload();
			// �õ������ϴ��ļ�����Ϣ
			file = su.getFiles().getFile(0);
			String filepath = null;
			if (!file.isMissing()) {
				// �����ļ��ڷ������ı���λ��
				filepath = "upload\\";
				filename = file.getFileName();
				filepath += file.getFileName();
				// �ļ����Ϊ
				file.setCharset("gbk");
				file.saveAs(filepath, SmartUpload.SAVE_VIRTUAL);
			}

			// ��ȡsmartupload��װ��request
			req = su.getRequest();
			// ��ȡ�������ҳ�洫�ݹ����Ĳ���
			String ntitle = req.getParameter("ntitle");
			String nauthor = req.getParameter("nauthor");
			String nsummary = req.getParameter("nsummary");
			String ncontent = req.getParameter("ncontent");
			String filePath = filepath;

			/*
			Map news = new HashMap();
			news.put("ntitle", ntitle);
			news.put("nauthor", nauthor);
			news.put("filePath", filePath);
			news.put("ncontent", ncontent);
			news.put("nsummary", nsummary);
			news.put("filename", filename);
			
			request.setAttribute("news", news);
			*/
		} catch (Exception e) {
			exceptionMsg = e.getMessage();
			e.printStackTrace();
		}

		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

}