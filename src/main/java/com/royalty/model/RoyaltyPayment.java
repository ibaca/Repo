package com.royalty.model;

import java.io.Serializable;

public class RoyaltyPayment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rightsownerId;
	
	private String rightsowner;
	
	private String royaltiy;
	
	private Integer viewings = 0;
	
	public RoyaltyPayment(){}
	
	public RoyaltyPayment(Studio studio){
		this.rightsownerId = studio.getId();
		this.rightsowner = studio.getName();
		this.royaltiy = studio.getPayment().toString();
	}

	/**
	 * @return the rightsownerId
	 */
	public String getRightsownerId() {
		return rightsownerId;
	}

	/**
	 * @param rightsownerId the rightsownerId to set
	 */
	public void setRightsownerId(String rightsownerId) {
		this.rightsownerId = rightsownerId;
	}

	/**
	 * @return the rightsowner
	 */
	public String getRightsowner() {
		return rightsowner;
	}

	/**
	 * @param rightsowner the rightsowner to set
	 */
	public void setRightsowner(String rightsowner) {
		this.rightsowner = rightsowner;
	}

	/**
	 * @return the royaltiy
	 */
	public String getRoyaltiy() {
		return royaltiy!=null?royaltiy + " £":"0 £";
	}

	/**
	 * @param royaltiy the royaltiy to set
	 */
	public void setRoyaltiy(String royaltiy) {
		this.royaltiy = royaltiy;
	}

	/**
	 * @return the viewings
	 */
	public Integer getViewings() {
		return viewings;
	}

	/**
	 * @param viewings the viewings to set
	 */
	public void setViewings(Integer viewings) {
		this.viewings = viewings;
	}
	
	/**
	 * Increment royalty for a Payment
	 */
	public void incrementRoyalty(){		
		this.royaltiy = Double.toString(Double.parseDouble(this.royaltiy) * this.viewings);
		
	}
	
}
