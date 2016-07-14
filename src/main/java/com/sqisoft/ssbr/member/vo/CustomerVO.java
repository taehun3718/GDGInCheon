package com.sqisoft.ssbr.member.vo;

public class CustomerVO {

	private String custId;
	private String custPw;
	private String custSite;
	private String custName;
	private String registDate;
	private String deleteDate;
	private String isDeletedYN;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustPw() {
		return custPw;
	}
	public void setCustPw(String custPw) {
		this.custPw = custPw;
	}
	public String getCustSite() {
		return custSite;
	}
	public void setCustSite(String custSite) {
		this.custSite = custSite;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getIsDeletedYN() {
		return isDeletedYN;
	}
	public void setIsDeletedYN(String isDeletedYN) {
		this.isDeletedYN = isDeletedYN;
	}
	
}
