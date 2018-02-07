package com.intuit.marketplace.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author Prashant
 *
 */
@XmlRootElement
public class Buyer {
	private long id;
	private String firstName;
	private String lastName;
	private ArrayList<Long> projectList;
	
	/**
	 * 
	 */
	public Buyer(){
		
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Long> getProjectList() {
		return projectList;
	}

	/**
	 * 
	 * @param projectList
	 */
	public void setProjectList(ArrayList<Long> projectList) {
		this.projectList = projectList;
	}

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Buyer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.projectList = null;
	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
