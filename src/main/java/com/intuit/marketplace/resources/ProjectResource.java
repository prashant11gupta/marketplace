package com.intuit.marketplace.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.intuit.marketplace.model.Project;
import com.intuit.marketplace.service.ProjectService;

/**
 * 
 * @author Prashant
 *
 */
@Path("/projects")
public class ProjectResource {

	ProjectService projectService = new ProjectService();

	/**
	 * Get list of all projects
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getProjects() {
		return projectService.getAllProjects();
	}
	
	/**
	 * Get project by ID
	 * @param projectId
	 * @return
	 */
	@GET
	@Path("/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project getProject(@PathParam("projectId") long projectId) {
		return projectService.getProject(projectId);
	}
	
	/**
	 * Get list of all active projects
	 * @return
	 */
	@GET
	@Path("/active")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getActiveProjects() {
		return projectService.getActiveProjects();
	}

}
