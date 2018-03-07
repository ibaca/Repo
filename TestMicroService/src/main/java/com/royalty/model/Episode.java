package com.royalty.model;

import java.io.Serializable;

public class Episode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String rightsowner;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

}
