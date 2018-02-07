package com.intuit.marketplace.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.intuit.marketplace.model.Project;
import com.intuit.marketplace.model.Seller;
import com.intuit.marketplace.service.SellerService;

/**
 * 
 * @author Prashant
 *
 */
@Path("/sellers")
public class SellerResource {
	SellerService sellerService = new SellerService();
		
	/**
	 * Add seller
	 * @param seller
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Seller addSeller(Seller seller) {
		return sellerService.addSeller(seller);	
	}
	
	/**
	 * Add project
	 * @param sellerId
	 * @param project
	 * @return
	 */
	@POST
	@Path("/{sellerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Project addProject(@PathParam("sellerId") long sellerId,Project project) {
		return sellerService.addProject(project,sellerId);	
	}
	
	/**
	 * Get seller by ID
	 * @param sellerId
	 * @return
	 */
	@GET
	@Path("/{sellerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Seller getSeller(@PathParam("sellerId") long sellerId) {
		return sellerService.getSeller(sellerId);
	}
}
