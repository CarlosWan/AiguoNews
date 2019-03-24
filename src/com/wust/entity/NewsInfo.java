package com.wust.entity;



public class NewsInfo {
	private int newsid;
	private String newstitle;
	private int typeid;
	private String newsauthor;
	private String newssummary;
	private String newscontent;
	private String newspic;
	private String newspubdate;
	
public NewsInfo(){
		
	}
	
	public NewsInfo(int selectTypeId,String newstitle,String newsauthor,String newssummary,String newscontent,String newspic){
		super();
		this.typeid = selectTypeId;
		this.newstitle = newstitle;
		this.newsauthor = newsauthor;
		this.newssummary = newssummary;
		this.newscontent = newscontent;
		this.newspic = newspic;
		//this.newspubdate = newspubdate;
		//this.status = status;
	}
	
	
	
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	public String getNewsauthor() {
		return newsauthor;
	}
	public void setNewsauthor(String newsauthor) {
		this.newsauthor = newsauthor;
	}
	public String getNewssummary() {
		return newssummary;
	}
	public void setNewssummary(String newssummary) {
		this.newssummary = newssummary;
	}
	public String getNewscontent() {
		return newscontent;
	}
	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}
	public String getNewspic() {
		return newspic;
	}
	public void setNewspic(String newspic) {
		this.newspic = newspic;
	}
	public String getNewspubdate() {
		return newspubdate;
	}
	public void setNewspubdate(String newspubdate) {
		this.newspubdate = newspubdate;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

}
