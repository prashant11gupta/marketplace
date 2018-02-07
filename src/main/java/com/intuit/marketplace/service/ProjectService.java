package com.intuit.marketplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.intuit.marketplace.database.ProjectDatabase;
import com.intuit.marketplace.model.Project;

/**
 * 
 * @author Prashant
 *
 */
public class ProjectService {

	private Map<Long, Project> projects;  // Project database
	
	public ProjectService() {
		projects = ProjectDatabase.getProjects();
	}

	/**
	 * List of all projects
	 * @return
	 */
	public List<Project> getAllProjects() {
		return new ArrayList<Project>(projects.values());
	}

	/**
	 * Get project by id
	 * @param projectId
	 * @return
	 */
	public Project getProject(long projectId) {
		return projects.get(projectId);
	}

	/**
	 * get all active projects
	 * @return
	 */
	public List<Project> getActiveProjects() {
		ArrayList<Project> activeProjects = new ArrayList<Project>();

		for (Project project : projects.values()) {
			if (project.getStatus().equalsIgnoreCase("Active")) {
				activeProjects.add(project);
			}
		}

		return activeProjects;
	}
}
