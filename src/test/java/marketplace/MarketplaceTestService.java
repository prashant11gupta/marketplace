package marketplace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.intuit.cron.CronScheduler;
import com.intuit.marketplace.model.Bid;
import com.intuit.marketplace.model.Buyer;
import com.intuit.marketplace.model.Project;
import com.intuit.marketplace.model.Seller;

/**
 * 
 * @author Prashant Gupta
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MarketplaceTestService {

	public static final String BASE_URI = "http://localhost:9998/marketplace/";
	private HttpServer server;
	Client client = ClientBuilder.newClient();

	@Before
	public void setUp() throws Exception {
		final ResourceConfig rc = new ResourceConfig();
		rc.packages("com.intuit");
		server = GrizzlyHttpServerFactory.createHttpServer(
				URI.create(BASE_URI), rc);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testOrder01() {

		System.out.println("Adding Seller...");
		Seller seller = new Seller("Prashant", "Gupta");

		Response target = client.target(BASE_URI).path("sellers").request()
				.post(Entity.entity(seller, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 200", 200, target.getStatus());
		assertNotNull("Should return project", target.getEntity());
		System.out.println("Status returned:" + target.getStatus());
		System.out
				.println("Json response : " + target.readEntity(String.class));

	}

	@Test
	public void testOrder02() {
		
		System.out.println("Adding Project with lastBidTime after 20 seconds from now...");
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date lastBidDate = new Date(t + (20000)); // lastBidTime is after 20 seconds

		Project project = new Project("New Project", "Project Description",
				300.00, lastBidDate, 100.00, "Active");

		Response output = client.target(BASE_URI).path("sellers/1").request()
				.post(Entity.entity(project, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return project", output.getEntity());
		System.out.println("Status returned:" + output.getStatus());
		System.out
				.println("Json response : " + output.readEntity(String.class));

	}

	@Test
	public void testOrder03() {

		System.out.println("Fetch seller by id...");
		Response output = client.target(BASE_URI).path("sellers/1").request()
				.get();

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
		System.out
				.println("Json response : " + output.readEntity(String.class));

	}

	@Test
	public void testOrder04() {

		System.out.println("Adding first buyer...");
		Buyer buyer = new Buyer("Monika", "Shrivastav");

		Response target = client.target(BASE_URI).path("buyers").request()
				.post(Entity.entity(buyer, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 200", 200, target.getStatus());
		assertNotNull("Should return project", target.getEntity());
		System.out.println("Status returned:" + target.getStatus());
		System.out
				.println("Json response : " + target.readEntity(String.class));

	}

	@Test
	public void testOrder05() {

		System.out.println("Adding second buyer...");
		Buyer buyer = new Buyer("Carlos", "Zolezzi");

		Response target = client.target(BASE_URI).path("buyers").request()
				.post(Entity.entity(buyer, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 200", 200, target.getStatus());
		assertNotNull("Should return project", target.getEntity());
		System.out.println("Status returned:" + target.getStatus());
		System.out
				.println("Json response : " + target.readEntity(String.class));

	}

	@Test
	public void testOrder06() {

		System.out.println("Buyer 1 bidding for Project 1 for $200.00");
		Bid bid = new Bid(200.00);
		Response target = client.target(BASE_URI).path("buyers/1/1").request()
				.post(Entity.entity(bid, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 204", 204, target.getStatus());
		assertNotNull("Should return project", target.getEntity());
		System.out.println("Status returned:" + target.getStatus());
	}
	
	@Test
	public void testOrder07() {

		System.out.println("Buyer 2 bidding for Project 1 for $100.00");
		Bid bid = new Bid(100.00);
		Response target = client.target(BASE_URI).path("buyers/2/1").request()
				.post(Entity.entity(bid, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 204", 204, target.getStatus());
		assertNotNull("Should return project", target.getEntity());
		
		System.out.println("Status returned:" + target.getStatus());
	}

	@Test
	public void testOrder08() throws Exception {
		
		try {
			System.out.println("Scheduler starts to update project status and allot project to minimum bid buyer");
			System.out.println("Scheduler runs every minute, sleeping for 1 min....");
			new CronScheduler(); // Running scheduler to check lastBidTime and allot project to buyer with minBid
			Thread.sleep(60100); // Waiting for scheduler to check lastBidTime, Scheduler runs every minute
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Response output = client.target(BASE_URI).path("buyers/2").request()
				.get();

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
		System.out.println("Since buyer 2 bidded for lesser amount, he got project");
		System.out
				.println("Json response : " + output.readEntity(String.class));
		
		Response output2 = client.target(BASE_URI).path("buyers/1").request()
				.get();

		assertEquals("Should return status 200", 200, output2.getStatus());
		assertNotNull("Should return notification", output2.getEntity());
		System.out.println("Since buyer 1 bidded for higher amount, she didn't get project:");
		System.out
				.println("Json response : " + output2.readEntity(String.class));

	}
	
	@Test
	public void testOrder09(){
		System.out.println("Adding Second Project...");
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date lastBidDate = new Date(t + (120000)); // lastBidTime is after 2 minutes

		Project project = new Project("Second Project", "Second Project Description",
				600.00, lastBidDate, 200.00, "Active");

		Response output = client.target(BASE_URI).path("sellers/1").request()
				.post(Entity.entity(project, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return project", output.getEntity());
		System.out.println("Status returned:" + output.getStatus());
		System.out
				.println("Json response : " + output.readEntity(String.class));
	}
	
	@Test
	public void testOrder10(){
		System.out.println("Fetch all projects");
		
		Response output = client.target(BASE_URI).path("projects").request()
				.get();

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
		System.out
				.println("Json response : " + output.readEntity(String.class));

	}
	
	@Test
	public void testOrder11(){
		System.out.println("Fetch all active projects");
		
		Response output = client.target(BASE_URI).path("projects/active").request()
				.get();

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
		System.out
				.println("Json response : " + output.readEntity(String.class));

	}
	
	@Test
	public void testOrder12(){
		System.out.println("Fetch project by id...");
		
		Response output = client.target(BASE_URI).path("projects/2").request()
				.get();

		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
		System.out
				.println("Json response : " + output.readEntity(String.class));

	}
}
