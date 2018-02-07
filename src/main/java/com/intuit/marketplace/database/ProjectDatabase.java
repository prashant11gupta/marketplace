package com.intuit.marketplace.database;

import java.util.HashMap;
import java.util.Map;

import com.intuit.marketplace.model.Project;

public class ProjectDatabase {

	private static Map<Long, Project> projects = new HashMap<Long, Project>(); // Project database
	private static Map<Long, Map<Long, Double>> projectBuyerBidMap = new HashMap<Long, Map<Long, Double>>(); // project buyer bid map
	
	public static Map<Long, Project> getProjects(){
		return projects;
	}
	
	public static Map<Long, Map<Long, Double>> getProjectBuyerBids(){
		return projectBuyerBidMap;
	}
}
