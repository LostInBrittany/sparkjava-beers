package org.lostinbrittany.sparkjava.beers;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import org.lostinbrittany.sparkjava.beers.dao.BeerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeersAPI {

	public static Logger logger = LoggerFactory.getLogger(BeersAPI.class);
	
	public static void main(String[] args) {

		staticFileLocation("/static"); // Static files

		get("/BeerList", (request, response) -> {
			return BeerDAO.getBeerList();
		});
		
		get("/Beer/:beerId",(request, response) -> {
			return BeerDAO.getBeer(request.params(":beerId"));
		});

	}
}