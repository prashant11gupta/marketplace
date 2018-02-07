package com.intuit.marketplace.service;

import java.util.HashMap;
import java.util.Map;

import com.intuit.marketplace.database.BuyerDatabase;
import com.intuit.marketplace.database.ProjectDatabase;
import com.intuit.marketplace.model.Bid;
import com.intuit.marketplace.model.Buyer;
import com.intuit.marketplace.model.Project;

/**
 * 
 * @author Prashant
 *
 */
public class BuyerService {

	private Map<Long, Buyer> buyers;                        // Buyer Database
	private Map<Long, Project> projects;                    // Project Database 
	private Map<Long, Map<Long, Double>> projectBuyerBidMap;// Project buyer bid map

	public BuyerService() {
		buyers = BuyerDatabase.getBuyers();
		projects = ProjectDatabase.getProjects();
		projectBuyerBidMap = ProjectDatabase.getProjectBuyerBids();
	}

	/**
	 * get buyer by Id
	 * @param buyerId
	 * @return
	 */
	public Buyer getBuyer(long buyerId) {
		return buyers.get(buyerId);
	}

	/**
	 * Add new buyer
	 * @param buyer
	 * @return
	 */
	public Buyer addBuyer(Buyer buyer) {
		buyer.setId(buyers.size() + 1);
		buyers.put(buyer.getId(), buyer);
		return buyer;
	}

	/**
	 * Add bid by a buyer for a project
	 * @param bid
	 */
	public void addBid(Bid bid) {
		if (BuyerDatabase.getBuyers().containsKey(bid.getBuyerId())
				&& projects.containsKey(bid.getProjectId())
				&& projects.get(bid.getProjectId()).getStatus()
						.equalsIgnoreCase("Active")) {

			if (projectBuyerBidMap.containsKey(bid.getProjectId())) {

				projectBuyerBidMap.get(bid.getProjectId()).put(
						bid.getBuyerId(), bid.getBidAmnt());

				if (projects.get(bid.getProjectId()).lastMinBid > bid
						.getBidAmnt()) {
					projects.get(bid.getProjectId()).lastMinBid = bid
							.getBidAmnt();
					projects.get(bid.getProjectId()).buyerId = bid.getBuyerId();
				}
			} else {
				HashMap<Long, Double> map = new HashMap<Long, Double>();
				map.put(bid.getBuyerId(), bid.getBidAmnt());
				projectBuyerBidMap.put(bid.getProjectId(), map);
				projects.get(bid.getProjectId()).buyerId = bid.getBuyerId();
				projects.get(bid.getProjectId()).lastMinBid = bid.getBidAmnt();
			}
		}
	}
}
