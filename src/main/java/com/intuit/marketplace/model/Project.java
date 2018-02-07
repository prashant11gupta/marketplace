package com.intuit.marketplace.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author Prashant
 *
 */
@XmlRootElement
public class Project {

	private long id;
	private String title;
	private String description;
	private double budget;
	private Date lastBidDate;
	private double minBid;
	public  Long buyerId;
	private String status;
	private Date created;
	public Double lastMinBid;
	
	/**
	 * 
	 */
	public Project() {

	}

	/**
	 * 
	 * @param id
	 * @param title
	 * @param desc
	 * @param budget
	 * @param lastBidDate
	 * @param minBid
	 * @param status
	 */
	public Project(String title, String desc, Double budget,
			Date lastBidDate, Double minBid, String status) {
		this.title = title;
		this.description = desc;
		this.budget = budget;
		this.minBid = minBid;
		this.lastBidDate = lastBidDate;
		this.status =status;
		this.created = new Date();
	}

	/**
	 * 
	 * @return
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * 
	 * @param created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastBidDate() {
		return lastBidDate;
	}

	/**
	 * 
	 * @param lastBidDate
	 */
	public void setLastBidDate(Date lastBidDate) {
		this.lastBidDate = lastBidDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public Double getMinBid() {
		return minBid;
	}

	/**
	 * 
	 * @param minBid
	 */
	public void setMinBid(Double minBid) {
		this.minBid = minBid;
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
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public Double getBudget() {
		return budget;
	}

	/**
	 * 
	 * @param budget
	 */
	public void setBudget(Double budget) {
		this.budget = budget;
	}

}
