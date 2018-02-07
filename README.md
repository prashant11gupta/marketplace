# marketplace
Marketplace is bidding place to buy and sell projects

Instructions to launch project to local machine:

1) Complete a Readme that gives instructions to anyone from intuit; allowing them to easily launch the exercise
on their local machine.

- Git clone this repository to local directory using command:
	
	git clone https://github.com/prashant11gupta/marketplace.git
	
- Launch eclipe and import it as a maven project.
- I have created 12 Junit test cases which can be run directly from eclipse.
- You can also test services from postman after running project in local apache tomcat server.

2) Include the following in readme:
	
	6-7 hrs
	Moderate
	8
	It's a good way of checking overall command in language, data structure and algorithmic ability of a candidate
	NA

	
What all services does this project offers :

Seller services:

- You can create sellers using url : http://localhost:8080/marketplace/sellers
	json :
	{
	 "firstName":"Prashant",
	 "lastName":"Gupta"
	}
	
- Seller can create project using url: http://localhost:8080/marketplace/sellers/{sellerId}
	json:
	{
        "budget": 300,
        "description": "Develop API for Marketplace using JAVA and JAX-RS",
        "lastBidDate": "2018-02-29T00:00:00",
        "minBid": 100,
        "title": "MarketPlace Back-end Development-2"
    }
    
- Fetch seller by id using url: http://localhost:8080/marketplace/sellers/{sellerId}

Buyer services:
- you can create buyers using url : http://localhost:8080/marketplace/buyers
	json :
	{
	 "firstName":"Prashant",
	 "lastName":"Gupta"
	}
- fetch buyer using url : http://localhost:8080/marketplace/buyers/{buyerId}
- Buyer can bid for a project using url : http://localhost:8080/marketplace/buyers/{buyerId}/{projectId}

	{
		"bidAmnt" : 200
	}

Project services:
- You can get project by id using url : http://localhost:8080/marketplace/projects/1
- you can get list of all projects using url : http://localhost:8080/marketplace/projects
- You can get active projects using url: http://localhost:8080/marketplace/projects/active

- There is a cron job running every minute which checks lastBidDate for projects and changes their status to Inactive if it's expired and assign it to 
minimum bid buyer.

- I have done few validations :
	-> You have to create a seller if you want to create a new project.
	-> You have to create a buyer and project to make a bid for a project.



