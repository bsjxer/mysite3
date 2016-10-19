package com.bit2016.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String reg_date;
	private Integer hit;
	private Integer group_no;
	private Integer order_no;
	private Integer depth;
	private String userName;
	private Long user_no;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getGroup_no() {
		return group_no;
	}

	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}

	public Integer getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUser_no() {
		return user_no;
	}

	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date + ", hit="
				+ hit + ", group_no=" + group_no + ", order_no=" + order_no + ", depth=" + depth + ", userName="
				+ userName + ", user_no=" + user_no + "]";
	}
}
