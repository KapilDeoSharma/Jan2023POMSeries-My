package com.qa.opencart.pojo;

public class ProductWithMetaData {
	
	private String searchKey;
	private String productName;
	private String brand;
	private String availability;
	private String productCode;
	private String rewardsPoints;
	
	
	public ProductWithMetaData(String searchKey, String productName, String brand, String availability,
			String productCode, String rewardsPoints) {
		super();
		this.searchKey = searchKey;
		this.productName = productName;
		this.brand = brand;
		this.availability = availability;
		this.productCode = productCode;
		this.rewardsPoints = rewardsPoints;
	}


	public String getSearchKey() {
		return searchKey;
	}


	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getRewardsPoints() {
		return rewardsPoints;
	}


	public void setRewardsPoints(String rewardsPoints) {
		this.rewardsPoints = rewardsPoints;
	}


	@Override
	public String toString() {
		return "ProductwithMetaData [searchKey=" + searchKey + ", productName=" + productName + ", brand=" + brand
				+ ", availability=" + availability + ", productCode=" + productCode + ", rewardsPoints=" + rewardsPoints
				+ "]";
	}
	
	

}
