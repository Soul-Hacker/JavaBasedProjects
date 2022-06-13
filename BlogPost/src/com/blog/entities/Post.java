package com.blog.entities;

import java.sql.Timestamp;

public class Post {
	private int pid;
	private String ptitle;
	private String pcontent;
	private String pcode;
	private String ppic;
	
	private int catid;
	private int userId;
	private Timestamp pdate;
	public Post() {
	
	}
	public Post(int pid, String ptitle, String pcontent, String pcode, String ppic, int catid,int userId,Timestamp pdate) {
		super();
		this.pid = pid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pcode = pcode;
		this.ppic=ppic;
		
		this.catid = catid;
		this.userId=userId;
		this.pdate = pdate;
	}
	
	public Post(String ptitle, String pcontent, String pcode,int userId,int catid,String ppic,Timestamp pdate) {
// 		super();
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pcode = pcode;
		this.ppic=ppic;
//		,int catid
		this.catid = catid;
		this.userId=userId;
//		,Timestamp pdate
		this.pdate = pdate;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public Timestamp getPdate() {
		return pdate;
	}
	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPpic() {
		return ppic;
	}
	public void setPpic(String ppic) {
		this.ppic = ppic;
	}
	

}
