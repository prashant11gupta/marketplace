package com.intuit.marketplace.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author Prashant
 *
 */
@XmlRootElement
public class Bid {
	private double bidAmnt;
	private long projectId;
	private long buyerId;
	
	public Bid(){
		
	}
	
	/**
	 * 
	 * @param bidAmnt
	 * @param projectId
	 * @param buyerId
	 */
	public Bid(double bidAmnt){
		this.bidAmnt = bidAmnt;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getBidAmnt() {
		return bidAmnt;
	}
	
	/**
	 * 
	 * @param bidAmnt
	 */
	public void setBidAmnt(double bidAmnt) {
		this.bidAmnt = bidAmnt;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getBuyerId() {
		return buyerId;
	}
	
	/**
	 * 
	 * @param buyerId
	 */
	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getProjectId() {
		return projectId;
	}
	
	/**
	 * 
	 * @param projectId
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
}
