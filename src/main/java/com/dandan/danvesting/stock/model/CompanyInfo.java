package com.dandan.danvesting.stock.model;

import java.util.List;

import com.dandan.danvesting.stock.comment.model.StockComment;
import com.dandan.danvesting.stock.comment.model.StockCommentDetail;

public class CompanyInfo {
	private String ticker; //종목티커
	private String name; //종목이름
	private String cik; //증권거래소 상 종목id 
	private boolean active; //거래가능 여부
	private String listDate; //상장 날짜
	private String homepageUrl; //종목 홈페이지
	private String brandLogoUrl; //APIKEY 첨부 종목 로고 url
	private String logoUrl; //APIKEY 첨부X 종목 로고 url
	private String brandIconUrl; //APIKEY 첨부 종목 아이콘 url
	private String iconUrl; //APIKEY 첨부X 종목 아이콘 url
	private String state; //종목 본사 주소(state)
	private String city; //종목 본사 주소(city)
	private String address1; //종목 본사 주소(세부주소)
	private String postalCode; //종목 본사 주소(우편번호)
	private String phoneNumber; //전화번호
	private String description; //종목설명
	private String status; //검색 상태
	
	private List<StockCommentDetail> stockCommentDetails;// 종목 댓글들 + 좋아요/싫어요
	
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCik() {
		return cik;
	}
	public void setCik(String cik) {
		this.cik = cik;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getListDate() {
		return listDate;
	}
	public void setListDate(String listDate) {
		this.listDate = listDate;
	}
	public String getHomepageUrl() {
		return homepageUrl;
	}
	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}
	public String getBrandLogoUrl() {
		return brandLogoUrl;
	}
	public void setBrandLogoUrl(String brandLogoUrl) {
		this.brandLogoUrl = brandLogoUrl;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getBrandIconUrl() {
		return brandIconUrl;
	}
	public void setBrandIconUrl(String brandIconUrl) {
		this.brandIconUrl = brandIconUrl;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<StockCommentDetail> getStockCommentDetails() {
		return stockCommentDetails;
	}
	public void setStockCommentDetails(List<StockCommentDetail> stockCommentDetails) {
		this.stockCommentDetails = stockCommentDetails;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
