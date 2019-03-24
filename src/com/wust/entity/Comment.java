package com.wust.entity;

import java.sql.Date;

import com.wust.util.DateUtil;

public class Comment {
	private int commentId;
	private int userId;
	private int newsId;
	private String ipaddr;
	private String content;
	private Date date;

	
	public Comment(int commentId, int userId, int newsId,
			String ipaddr, String content, Date date) {
		super();
		this.commentId = commentId;
		this.userId= userId;
		this.newsId = newsId;
		this.ipaddr = ipaddr;
		this.content = content;
		this.date = date;
		
	}
	
	public Comment(int userId, int newsId,
			String ipaddr, String content) {
		super();

		this.userId= userId;
		this.newsId = newsId;
		this.ipaddr = ipaddr;
		this.content = content;

		
	}
	
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commenId) {
		this.commentId = commenId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
}
