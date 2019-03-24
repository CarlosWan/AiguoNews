package com.wust.entity;

public class NoticeInfo {
	private int noticeId;
	private String noticecontent;
	private String noticedate;
	
	public NoticeInfo(String noticecontent, String noticedate) {
		super();
		this.noticecontent = noticecontent;
		this.noticedate = noticedate;
	    }
	public NoticeInfo(){
		
	}
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	public String getNoticedate() {
		return noticedate;
	}
	public void setNoticedate(String noticedate) {
		this.noticedate = noticedate;
	}

	
}
