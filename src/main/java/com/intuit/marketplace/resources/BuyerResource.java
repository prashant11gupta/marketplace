package com.intuit.marketplace.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.intuit.marketplace.model.Bid;
import com.intuit.marketplace.model.Buyer;
import com.intuit.marketplace.service.BuyerService;

/**
 * 
 * @author Prashant
 *
 */
@Path("/buyers")
public class BuyerResource {

	BuyerService buyerService = new BuyerService();

	/**
	 * Add buyer
	 * @param buyer
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Buyer addBuyer(Buyer buyer) {
		return buyerService.addBuyer(buyer);
	}

	/**
	 * Get buyer by ID
	 * @param buyerId
	 * @return
	 */
	@GET
	@Path("/{buyerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Buyer getBuyer(@PathParam("buyerId") long buyerId) {
		return buyerService.getBuyer(buyerId);
	}
	
	/**
	 * A buyer bid for a project
	 * @param buyerId
	 * @param projectId
	 * @param bid
	 */
	@POST
	@Path("/{buyerId}/{projectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addBid(@PathParam("buyerId") long buyerId,@PathParam("projectId") long projectId, Bid bid) {
		bid.setBuyerId(buyerId);
		bid.setProjectId(projectId);
		buyerService.addBid(bid);	
	}
}
