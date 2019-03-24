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
		// 初始化SmartUpload对象
		su.initialize(this.getServletConfig(), request, response);
		com.jspsmart.upload.File file = null;
		// 允许上传类型
		String allowed = "gif,jpg,doc,rar";
		// 不许上传类型
		String denied = "jsp,asp,php,aspx,html,htm,exe,bat";
		com.jspsmart.upload.Request req = null;
		// 设置上传文件大小
		int file_size = 2 * 1024 * 1024; //2mb
		String exceptionMsg = null;
		int i = 0;
		try {
			// 定义允许上传文件类型
			su.setAllowedFilesList(allowed);
			// 不允许上传文件类型
			su.setDeniedFilesList(denied);
			// 单个文件最大限制
			su.setMaxFileSize(file_size);
			su.setCharset("GBK");
			// 执行上传
			su.upload();
			// 得到单个上传文件的信息
			file = su.getFiles().getFile(0);
			String filepath = null;
			if (!file.isMissing()) {
				// 设置文件在服务器的保存位置
				filepath = "upload\\";
				filename = file.getFileName();
				filepath += file.getFileName();
				// 文件另存为
				file.setCharset("gbk");
				file.saveAs(filepath, SmartUpload.SAVE_VIRTUAL);
			}

			// 获取smartupload封装的request
			req = su.getRequest();
			// 获取添加新闻页面传递过来的参数
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