package com.intuit.marketplace.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.intuit.marketplace.database.ProjectDatabase;
import com.intuit.marketplace.database.SellerDatabase;
import com.intuit.marketplace.model.Project;
import com.intuit.marketplace.model.Seller;

/**
 * 
 * @author Prashant
 *
 */
public class SellerService {

	private Map<Long, Seller> sellers; // seller database
	private Map<Long, Project> projects; // project database

	public SellerService() {
		sellers = SellerDatabase.getSellers();
		projects = ProjectDatabase.getProjects();
	}

	/**
	 * Add new Seller
	 * 
	 * @param seller
	 * @return
	 */
	public Seller addSeller(Seller seller) {
		seller.setId(sellers.size() + 1);
		sellers.put(seller.getId(), seller);
		return seller;
	}

	/**
	 * Get seller by Id
	 * 
	 * @param sellerId
	 * @return
	 */
	public Seller getSeller(long sellerId) {
		return sellers.get(sellerId);

	}

	/**
	 * Add new Project
	 * 
	 * @param project
	 * @param sellerId
	 * @return
	 */
	public Project addProject(Project project, long sellerId) {
		if (sellers.containsKey(sellerId)) {
			project.setId(projects.size() + 1);
			project.setStatus("Active");
			project.setCreated(new Date());
			projects.put(project.getId(), project);
			ArrayList<Long> projectList = sellers.get(sellerId)
					.getProjectList();

			ArrayList<Long> newList = new ArrayList<Long>();
			if (projectList == null) {
				newList.add(project.getId());
				projectList = newList;
			} else {
				projectList.add(project.getId());
			}
			sellers.get(sellerId).setProjectList(projectList);

			return project;

		}

		return null;

	}

}
