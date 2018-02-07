package com.intuit.marketplace.database;

import java.util.HashMap;
import java.util.Map;

import com.intuit.marketplace.model.Seller;

public class SellerDatabase {
	private static Map<Long, Seller> sellers = new HashMap<Long, Seller>(); // seller database
	
	public static Map<Long,Seller> getSellers(){
		return sellers;
	}
	
}
