package com.javaex.vo;

public class BlogVo {

	//필드
	private String id;
	private String userName;
	private String blogTitle;
	private String logoFile;

	//생성자
	public BlogVo() {
		
	}
	public BlogVo(String id, String userName, String blogTitle, String logoFile) {
		this.id = id;
		this.userName = userName;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}
	
	//메소드 g/s
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", userName=" + userName + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile
				+ "]";
	}
	

}
