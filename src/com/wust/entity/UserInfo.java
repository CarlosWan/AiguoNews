package com.wust.entity;
/*用户实体类*/
public class UserInfo {
	private int userId;
	private String username;
	private String password;
	private String realname;
	private String gender;
	private String telephone;
	private String email;
	private int status;//用户状态值 0-普通用户 1-管理员 2-超级管理员
	private int state;//是否激活
	private String code;//激活码
	
	public UserInfo(){
		
	}
	
	
	public UserInfo(String username, String password, String realname,
			String gender, String telephone, String email, int status) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.status = status;
		
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getRealname() {
		return realname;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}




}
