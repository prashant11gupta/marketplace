package com.intuit.marketplace.database;

import java.util.HashMap;
import java.util.Map;

import com.intuit.marketplace.model.Buyer;

/**
 * 
 * @author Prashant
 *
 */
public class BuyerDatabase {
	private static Map<Long, Buyer> buyers = new HashMap<Long, Buyer>(); //Buyer database
	
	public static Map<Long, Buyer> getBuyers(){
		return buyers;
	}
}
