package com.intuit.cron;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.intuit.marketplace.database.BuyerDatabase;
import com.intuit.marketplace.database.ProjectDatabase;
import com.intuit.marketplace.model.Buyer;
import com.intuit.marketplace.model.Project;

/**
 * 
 * @author Prashant
 *
 */
public class CheckStatus implements org.quartz.Job {

	public CheckStatus() {
    }

	/**
	 * 
	 */
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("Updating status");
        updateStatus();
    }
    
    /**
     * Update project status
     * Add project to minimum bid buyer list
     */
    public void updateStatus(){
    	
    	Map<Long, Buyer> buyers = BuyerDatabase.getBuyers();
    	Map<Long, Project> projects = ProjectDatabase.getProjects();
    	for(Project project : projects.values()){
    		
    		 if (project.getStatus().equalsIgnoreCase("Active") && project.getLastBidDate().compareTo(new Date()) < 0){
    			project.setStatus("Inactive");
    			long buyer = project.buyerId;
    			ArrayList<Long> projectList = null;
    			if(buyers.containsKey(buyer)){
    				projectList = buyers.get(buyer).getProjectList();
    				ArrayList<Long> newList = new ArrayList<Long>();
        			if(projectList == null){
        				newList.add(project.getId());
        				projectList = newList;
        			}else{
        				projectList.add(project.getId());
        			}
    			}
    						
    			BuyerDatabase.getBuyers().get(buyer).setProjectList(projectList);	
    			
    		}
    	}
    }
}
